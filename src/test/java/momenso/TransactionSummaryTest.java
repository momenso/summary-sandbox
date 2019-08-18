package momenso;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.Before;
import org.junit.Test;

public class TransactionSummaryTest {

  private Summary summary;

  @Before
  public void setUp() {
    summary = new TransactionSummary();
  }

  @Test
  public void rogueCategory() {
    Category rogueCategory = (v, t) -> 42;

    assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() ->
        summary.get(rogueCategory)
    );
  }

  @Test
  public void noTransactions() {
    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("0");
  }

  @Test
  public void singleBRLCreditTransaction() {
    summary.add(new Transaction("1", "BRL", "credit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("0");
  }

  @Test
  public void singleBRLDebitTransaction() {
    summary.add(new Transaction("9", "BRL", "debit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("9");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("9");
  }

  @Test
  public void singleUSDCreditTransaction() {
    summary.add(new Transaction("1", "USD", "credit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("0");
  }

  @Test
  public void singleUSDDebitTransaction() {
    summary.add(new Transaction("1", "USD", "debit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("1");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("0");
  }

  @Test
  public void twoBRLCreditTransaction() {
    summary.add(new Transaction("3", "BRL", "credit"));
    summary.add(new Transaction("4", "BRL", "credit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("7");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("0");
  }

  @Test
  public void twoBRLDebitTransaction() {
    summary.add(new Transaction("3", "BRL", "debit"));
    summary.add(new Transaction("4", "BRL", "debit"));

    assertThat(summary.get(TransactionSummary.NUMBER_OF_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_DEBIT_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_DEBIT_TRANSACTIONS)).isEqualTo("2");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_BRL_CREDIT_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.NUMBER_OF_USD_TRANSACTIONS)).isEqualTo("0");
    assertThat(summary.get(TransactionSummary.TOTAL_AMOUNT)).isEqualTo("7");
    assertThat(summary.get(TransactionSummary.TOTAL_BRL_DEBIT_AMOUNT)).isEqualTo("7");
  }

}