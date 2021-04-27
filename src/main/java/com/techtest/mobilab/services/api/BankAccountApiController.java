package com.techtest.mobilab.services.api;

import com.techtest.mobilab.datamodel.BankAccountDocument;
import com.techtest.mobilab.repository.BankAccountRepository;
import com.techtest.mobilab.services.model.BankAccount;
import com.techtest.mobilab.services.model.BankAccount.CurrencyEnum;
import com.techtest.mobilab.services.model.ModelApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mapping.AccessOptions.SetOptions.SetNulls;
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
import java.time.Instant;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-06T09:18:58.057Z")

@Controller
public class BankAccountApiController implements BankAccountApi {

	private static final Logger log = LoggerFactory.getLogger(BankAccountApiController.class);
	private final ObjectMapper objectMapper;
	private final HttpServletRequest request;

	@Autowired
	BankAccountRepository bankAccountRepository;

	@org.springframework.beans.factory.annotation.Autowired
	public BankAccountApiController(ObjectMapper objectMapper, HttpServletRequest request) {
		this.objectMapper = objectMapper;
		this.request = request;
	}

	public ResponseEntity<ModelApiResponse> addBankAccount(
			@ApiParam(value = "", required = true) @Valid @RequestBody BankAccount body) {

		String accountNumber = body.getAccountNumber();
		BankAccountDocument bankAccountDocumentExists = bankAccountRepository.findByAccountNumber(accountNumber);
		if (bankAccountDocumentExists == null && accountNumber != null) {

			BankAccountDocument bankAccountDocument = new BankAccountDocument();
			bankAccountDocument.setAccountNumber(accountNumber);
			bankAccountDocument.setBankLocation(body.getBankLocation());
			bankAccountDocument.setBankName(body.getBankName());
			bankAccountDocument.setCode(body.getCode());
			bankAccountDocument.setHolderName(body.getHolderName());

			CurrencyEnum currency = body.getCurrency();
			bankAccountDocument.setCurrency(currency.toString());

			Instant instant = Instant.now();
			long timeStampMillis = instant.toEpochMilli();
			String createdAt = String.valueOf(timeStampMillis);
			bankAccountDocument.setCreatedAt(createdAt);

			bankAccountRepository.save(bankAccountDocument);

			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(200);
			modelApiResponse.setType("Create BankAccount");
			modelApiResponse.setMessage("BankAccount created successfully!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.OK);
		} else {
			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(400);
			modelApiResponse.setType("Create BankAccount");
			modelApiResponse.setMessage("BankAccount with the account number '" + accountNumber + "' already exists!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<ModelApiResponse> deleteBankAccount(
			@ApiParam(value = "", required = true) @PathVariable("accountNumber") String accountNumber) {

		BankAccountDocument bankAccountDocumentExists = bankAccountRepository.findByAccountNumber(accountNumber);

		if (bankAccountDocumentExists != null) {
			bankAccountRepository.delete(bankAccountDocumentExists);

			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(200);
			modelApiResponse.setType("Delete BankAccount");
			modelApiResponse.setMessage("BankAccount deleted successfully!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.OK);
		} else {
			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(404);
			modelApiResponse.setType("Delete BankAccount");
			modelApiResponse.setMessage("BankAccount with the given account number was not found!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<BankAccount> getBankByAccountNumber(
			@ApiParam(value = "", required = true) @PathVariable("accountNumber") String accountNumber) {

		BankAccountDocument bankAccountDocumentExists = bankAccountRepository.findByAccountNumber(accountNumber);

		if (bankAccountDocumentExists != null) {
			BankAccount bankAccount = new BankAccount();
			bankAccount.setAccountNumber(accountNumber);
			bankAccount.setBankLocation(bankAccountDocumentExists.getBankLocation());
			bankAccount.setBankName(bankAccountDocumentExists.getBankName());
			bankAccount.setCode(bankAccountDocumentExists.getCode());
			bankAccount.setHolderName(bankAccountDocumentExists.getHolderName());
			bankAccount.setCurrency(CurrencyEnum.fromValue(bankAccountDocumentExists.getCurrency()));
			bankAccount.setCreatedAt(bankAccountDocumentExists.getCreatedAt());

			return new ResponseEntity<BankAccount>(bankAccount, HttpStatus.OK);
		} else {
			return new ResponseEntity<BankAccount>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ModelApiResponse> update(
			@ApiParam(value = "", required = true) @Valid @RequestBody BankAccount body) {

		String accountNumber = body.getAccountNumber();
		BankAccountDocument bankAccountDocumentExists = bankAccountRepository.findByAccountNumber(accountNumber);

		if (bankAccountDocumentExists != null) {

			bankAccountDocumentExists.setBankLocation(body.getBankLocation());
			bankAccountDocumentExists.setBankName(body.getBankName());
			bankAccountDocumentExists.setCode(body.getCode());
			bankAccountDocumentExists.setHolderName(body.getHolderName());
			CurrencyEnum currency = body.getCurrency();
			bankAccountDocumentExists.setCurrency(currency.toString());
			bankAccountRepository.save(bankAccountDocumentExists);

			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(200);
			modelApiResponse.setType("Update BankAccount");
			modelApiResponse.setMessage("BankAccount updated successfully!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.OK);
		} else {
			ModelApiResponse modelApiResponse = new ModelApiResponse();
			modelApiResponse.setCode(404);
			modelApiResponse.setType("Update BankAccount");
			modelApiResponse.setMessage("BankAccount with the given account number was not found!");
			return new ResponseEntity<ModelApiResponse>(modelApiResponse, HttpStatus.NOT_FOUND);
		}
	}

}
