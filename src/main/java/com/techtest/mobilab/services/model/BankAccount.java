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
 * BankAccount
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-04-06T09:18:58.057Z")


public class BankAccount   {
  @JsonProperty("holder_name")
  private String holderName = null;

  @JsonProperty("account_number")
  private String accountNumber = null;

  @JsonProperty("bank_name")
  private String bankName = null;

  @JsonProperty("bank_location")
  private String bankLocation = null;

  @JsonProperty("code")
  private Long code = null;

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

  @JsonProperty("created_at")
  private String createdAt = null;

  public BankAccount holderName(String holderName) {
    this.holderName = holderName;
    return this;
  }

  /**
   * Get holderName
   * @return holderName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getHolderName() {
    return holderName;
  }

  public void setHolderName(String holderName) {
    this.holderName = holderName;
  }

  public BankAccount accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

  /**
   * Get accountNumber
   * @return accountNumber
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public BankAccount bankName(String bankName) {
    this.bankName = bankName;
    return this;
  }

  /**
   * Get bankName
   * @return bankName
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public BankAccount bankLocation(String bankLocation) {
    this.bankLocation = bankLocation;
    return this;
  }

  /**
   * Get bankLocation
   * @return bankLocation
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getBankLocation() {
    return bankLocation;
  }

  public void setBankLocation(String bankLocation) {
    this.bankLocation = bankLocation;
  }

  public BankAccount code(Long code) {
    this.code = code;
    return this;
  }

  /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public BankAccount currency(CurrencyEnum currency) {
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

  public BankAccount createdAt(String createdAt) {
    this.createdAt = createdAt;
    return this;
  }

  /**
   * Get createdAt
   * @return createdAt
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BankAccount bankAccount = (BankAccount) o;
    return Objects.equals(this.holderName, bankAccount.holderName) &&
        Objects.equals(this.accountNumber, bankAccount.accountNumber) &&
        Objects.equals(this.bankName, bankAccount.bankName) &&
        Objects.equals(this.bankLocation, bankAccount.bankLocation) &&
        Objects.equals(this.code, bankAccount.code) &&
        Objects.equals(this.currency, bankAccount.currency) &&
        Objects.equals(this.createdAt, bankAccount.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(holderName, accountNumber, bankName, bankLocation, code, currency, createdAt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BankAccount {\n");
    
    sb.append("    holderName: ").append(toIndentedString(holderName)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    bankName: ").append(toIndentedString(bankName)).append("\n");
    sb.append("    bankLocation: ").append(toIndentedString(bankLocation)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

