package com.techtest.mobilab.helper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.techtest.mobilab.datamodel.TransactionDocument;
import com.techtest.mobilab.services.model.Transaction;
import com.techtest.mobilab.services.model.Transaction.CurrencyEnum;

public class Helper {

	public static String convertRates(String base, String toExchange, String amount) {

		float floatAmount = Float.parseFloat(amount);

		// http://api.exchangeratesapi.io/v1/latest?access_key=9419d8bd6713e0c23ce2c26f52950e1c

		try {

			URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=9419d8bd6713e0c23ce2c26f52950e1c");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();
			int responsecode = conn.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			} else {

				String inline = "";
				Scanner scanner = new Scanner(url.openStream());

				while (scanner.hasNext()) {
					inline += scanner.nextLine();
				}

				// Close the scanner
				scanner.close();

				// Using the JSON simple library parse the string into a json object
				JSONParser parse = new JSONParser();
				JSONObject dataObject = (JSONObject) parse.parse(inline);

				// Get the required object from the above created object
				String baseApi = (String) dataObject.get("base");

				JSONObject rates = (JSONObject) dataObject.get("rates");
				String rateUSD = (String) rates.get("USD").toString();

				if (baseApi.equalsIgnoreCase(base)) {
					// EUR => USD
					floatAmount = floatAmount * Float.parseFloat(rateUSD);
				} else {
					// USD => EUR
					floatAmount = floatAmount / Float.parseFloat(rateUSD);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return String.valueOf(floatAmount);
	}

	public static Transaction convertDataToModel(TransactionDocument transactionDocument) {

		Transaction transaction = new Transaction();

		transaction.setAmountOfTransaction(transactionDocument.getAmountOfTransaction());
		transaction.setCreditorAccountNumber(transactionDocument.getCreditorAccountNumber());
		transaction.setCurrency(CurrencyEnum.fromValue(transactionDocument.getCurrency()));
		transaction.setDate(transactionDocument.getDate());
		transaction.setDebtorAccountNumber(transactionDocument.getDebtorAccountNumber());
		transaction.setOtherDetails(transactionDocument.getOtherDetails());

		return transaction;
	}

}
