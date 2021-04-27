package com.techtest.mobilab.services.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Transaction
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-06T09:18:58.057Z")


public class Transaction   {
  @JsonProperty("date")
  private String date = null;

  @JsonProperty("amount_of_transaction")
  private String amountOfTransaction = null;

  @JsonProperty("other_details")
  private String otherDetails = null;

  @JsonProperty("creditor_account_number")
  private String creditorAccountNumber = null;

  @JsonProperty("debtor_account_number")
  private String debtorAccountNumber = null;

  /**
   * Gets or Sets currency
   */
  public enum CurrencyEnum {
    EUR("EUR"),
    
    USD("USD");

    private String value;

    CurrencyEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CurrencyEnum fromValue(String text) {
      for (CurrencyEnum b : CurrencyEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("currency")
  private CurrencyEnum currency = null;

  public Transaction date(String date) {
    this.date = date;
    return this;
  }

  /**
   * Get date
   * @return date
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Transaction amountOfTransaction(String amountOfTransaction) {
    this.amountOfTransaction = amountOfTransaction;
    return this;
  }

  /**
   * Get amountOfTransaction
   * @return amountOfTransaction
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAmountOfTransaction() {
    return amountOfTransaction;
  }

  public void setAmountOfTransaction(String amountOfTransaction) {
    this.amountOfTransaction = amountOfTransaction;
  }

  public Transaction otherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
    return this;
  }

  /**
   * Get otherDetails
   * @return otherDetails
  **/
  @ApiModelProperty(value = "")


  public String getOtherDetails() {
    return otherDetails;
  }

  public void setOtherDetails(String otherDetails) {
    this.otherDetails = otherDetails;
  }

  public Transaction creditorAccountNumber(String creditorAccountNumber) {
    this.creditorAccountNumber = creditorAccountNumber;
    return this;
  }

  /**
   * Get creditorAccountNumber
   * @return creditorAccountNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCreditorAccountNumber() {
    return creditorAccountNumber;
  }

  public void setCreditorAccountNumber(String creditorAccountNumber) {
    this.creditorAccountNumber = creditorAccountNumber;
  }

  public Transaction debtorAccountNumber(String debtorAccountNumber) {
    this.debtorAccountNumber = debtorAccountNumber;
    return this;
  }

  /**
   * Get debtorAccountNumber
   * @return debtorAccountNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getDebtorAccountNumber() {
    return debtorAccountNumber;
  }

  public void setDebtorAccountNumber(String debtorAccountNumber) {
    this.debtorAccountNumber = debtorAccountNumber;
  }

  public Transaction currency(CurrencyEnum currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   * @return currency
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public CurrencyEnum getCurrency() {
    return currency;
  }

  public void setCurrency(CurrencyEnum currency) {
    this.currency = currency;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction transaction = (Transaction) o;
    return Objects.equals(this.date, transaction.date) &&
        Objects.equals(this.amountOfTransaction, transaction.amountOfTransaction) &&
        Objects.equals(this.otherDetails, transaction.otherDetails) &&
        Objects.equals(this.creditorAccountNumber, transaction.creditorAccountNumber) &&
        Objects.equals(this.debtorAccountNumber, transaction.debtorAccountNumber) &&
        Objects.equals(this.currency, transaction.currency);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, amountOfTransaction, otherDetails, creditorAccountNumber, debtorAccountNumber, currency);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Transaction {\n");
    
    sb.append("    date: ").append(toIndentedString(date)).append("\n");
    sb.append("    amountOfTransaction: ").append(toIndentedString(amountOfTransaction)).append("\n");
    sb.append("    otherDetails: ").append(toIndentedString(otherDetails)).append("\n");
    sb.append("    creditorAccountNumber: ").append(toIndentedString(creditorAccountNumber)).append("\n");
    sb.append("    debtorAccountNumber: ").append(toIndentedString(debtorAccountNumber)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

