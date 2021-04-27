package com.techtest.mobilab.services.api;

import com.techtest.mobilab.datamodel.BankAccountDocument;
import com.techtest.mobilab.datamodel.TransactionDocument;
import com.techtest.mobilab.helper.Helper;
import com.techtest.mobilab.repository.BankAccountRepository;
import com.techtest.mobilab.repository.TransactionRepository;
import com.techtest.mobilab.services.model.ModelApiResponse;
import com.techtest.mobilab.services.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-06T09:18:58.057Z")

@Controller
public class TransactionApiController implements TransactionApi {

	private static final Logger log = LoggerFactory.getLogger(TransactionApiController.class);
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public TransactionApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ModelApiResponse> makeTransaction(
			@ApiParam(value = "", required = true) @Valid @RequestBody Transaction body) {

		String creditorAccountNumber = body.getCreditorAccountNumber();
		String debtorAccountNumber = body.getDebtorAccountNumber();

		// who will get money in his bank account
		BankAccountDocument creditorBankAccountDocument = bankAccountRepository
				.findByAccountNumber(creditorAccountNumber);
		// the sender of the amount of money
		BankAccountDocument debtorBankAccountDocument = bankAccountRepository.findByAccountNumber(debtorAccountNumber);

		if (creditorBankAccountDocument != null && debtorBankAccountDocument != null) {

			try {
				float amount = Float.parseFloat(body.getAmountOfTransaction());
				if (amount <= 0) {
					ModelApiResponse modelApiResponse = new ModelApiResponse();
					modelApiResponse.setCode(400);
					modelApiResponse.setType("Create Transaction");
					modelApiResponse.setMessage("Invalid amount of transaction inserted!");
					return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.BAD_REQUEST);
				}
			} catch (NumberFormatException e) {
				ModelApiResponse modelApiResponse = new ModelApiResponse();
				modelApiResponse.setCode(400);
				modelApiResponse.setType("Create Transaction");
				modelApiResponse.setMessage("Invalid amount of transaction inserted!");
				return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.BAD_REQUEST);
			}

			String creditorCurrency = creditorBankAccountDocument.getCurrency();
			String debtorCurrency = debtorBankAccountDocument.getCurrency();
			if (creditorCurrency.equalsIgnoreCase(debtorCurrency)) {
				// insert transaction data
				TransactionDocument transactionDocument = new TransactionDocument();
				transactionDocument.setAmountOfTransaction(body.getAmountOfTransaction());
				transactionDocument.setCreditorAccountNumber(creditorAccountNumber);
				transactionDocument.setDebtorAccountNumber(debtorAccountNumber);
				transactionDocument.setOtherDetails(body.getOtherDetails());
				transactionDocument.setCurrency(creditorCurrency);

				Instant instant = Instant.now();
				long timeStampMillis = instant.toEpochMilli();
				transactionDocument.setDate(String.valueOf(timeStampMillis));

				transactionRepository.save(transactionDocument);

			} else {
				// exchange rates & insert data
				String exchangedAmount = Helper.convertRates(debtorCurrency, creditorCurrency,
						body.getAmountOfTransaction());

				TransactionDocument transactionDocument = new TransactionDocument();
				transactionDocument.setAmountOfTransaction(exchangedAmount);
				transactionDocument.setCreditorAccountNumber(creditorAccountNumber);
				transactionDocument.setDebtorAccountNumber(debtorAccountNumber);
				transactionDocument.setOtherDetails(body.getOtherDetails());
				transactionDocument.setCurrency(creditorCurrency);

				Instant instant = Instant.now();
				long timeStampMillis = instant.toEpochMilli();
				transactionDocument.setDate(String.valueOf(timeStampMillis));

				transactionRepository.save(transactionDocument);
			}

			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(200);
			modelApiResponse.setType("Create Transaction");
			modelApiResponse.setMessage("Success transaction!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.OK);
		} else {
			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(404);
			modelApiResponse.setType("Create Transaction");
			modelApiResponse
					.setMessage("Either debtor or creditor account number does not match an existing BankAccount!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Transaction>> getTransactions(
			@ApiParam(value = "the start date from where to start the search") @Valid @RequestParam(value = "start", required = false) String start,
			@ApiParam(value = "the end date of the search") @Valid @RequestParam(value = "end", required = false) String end)
			throws ParseException {

		List<TransactionDocument> ListTransactionDocument = transactionRepository.findAll();

		if (ListTransactionDocument.isEmpty())
			return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_FOUND);

		List<Transaction> listTransaction = new ArrayList<>();
		if (start == null && end == null) {
			// get all transactions
			for (TransactionDocument data : ListTransactionDocument) {
				listTransaction.add(Helper.convertDataToModel(data));
			}
		} else if (start == null) {
			// end not null : fetch transactions before end date : date format dd/MM/yyyy
			Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
			long tsEnd = endDate.getTime();

			for (TransactionDocument data : ListTransactionDocument) {
				if (Long.parseLong(data.getDate()) <= tsEnd)
					listTransaction.add(Helper.convertDataToModel(data));
			}
		} else if (end == null) {
			// start not null: fetch transactions from start date
			Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
			long tsStart = startDate.getTime();

			for (TransactionDocument data : ListTransactionDocument) {
				if (Long.parseLong(data.getDate()) >= tsStart)
					listTransaction.add(Helper.convertDataToModel(data));
			}

		} else {
			// get transactions between start and end dates
			Date startDate = new SimpleDateFormat("dd/MM/yyyy").parse(start);
			Date endDate = new SimpleDateFormat("dd/MM/yyyy").parse(end);
			long tsStart = startDate.getTime();
			long tsEnd = endDate.getTime();

			for (TransactionDocument data : ListTransactionDocument) {
				if (Long.parseLong(data.getDate()) >= tsStart && Long.parseLong(data.getDate()) <= tsEnd)
					listTransaction.add(Helper.convertDataToModel(data));
			}
		}
		return new ResponseEntity<List<Transaction>>(listTransaction, HttpStatus.OK);
	}

}
