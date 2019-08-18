package momenso;

import java.util.Objects;

public class Transaction {

  private String value;
  private String currencyCode;
  private String type;

  public Transaction(String value, String currencyCode, String type) {
    this.value = value;
    this.currencyCode = currencyCode;
    this.type = type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return value.equals(that.value) &&
        currencyCode.equals(that.currencyCode) &&
        type.equals(that.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, currencyCode, type);
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getCurrencyCode() {
    return currencyCode;
  }

  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
