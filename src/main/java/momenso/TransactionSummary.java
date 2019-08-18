package momenso;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Predicate;

public class TransactionSummary extends Summary {

  private static final Predicate<Transaction> ALL_TRANSACTIONS = t -> true;
  private static final Predicate<Transaction> CREDIT_TRANSACTION = t ->
      "credit".equals(t.getType());
  private static final Predicate<Transaction> DEBIT_TRANSACTION = t ->
      "debit".equals(t.getType());
  private static final Predicate<Transaction> BRL_CURRENCY = t ->
      "BRL".equals(t.getCurrencyCode());
  private static final Predicate<Transaction> USD_CURRENCY = t ->
      "USD".equals(t.getCurrencyCode());

  static final Category NUMBER_OF_TRANSACTIONS =
      CountCategory.of(ALL_TRANSACTIONS);
  static final Category NUMBER_OF_CREDIT_TRANSACTIONS =
      CountCategory.of(CREDIT_TRANSACTION);
  static final Category NUMBER_OF_DEBIT_TRANSACTIONS =
      CountCategory.of(DEBIT_TRANSACTION);
  static final Category NUMBER_OF_BRL_DEBIT_TRANSACTIONS =
      CountCategory.of(BRL_CURRENCY.and(DEBIT_TRANSACTION));
  static final Category NUMBER_OF_BRL_CREDIT_TRANSACTIONS =
      CountCategory.of(BRL_CURRENCY.and(CREDIT_TRANSACTION));
  static final Category NUMBER_OF_USD_TRANSACTIONS =
      CountCategory.of(USD_CURRENCY);
  static final Category TOTAL_AMOUNT =
      SumCategory.of(ALL_TRANSACTIONS);
  static final Category TOTAL_BRL_DEBIT_AMOUNT =
      SumCategory.of(BRL_CURRENCY.and(DEBIT_TRANSACTION));

  @Override
  protected Collection<Category> getCategories() {
    return Arrays.asList(
        NUMBER_OF_TRANSACTIONS,
        NUMBER_OF_CREDIT_TRANSACTIONS,
        NUMBER_OF_DEBIT_TRANSACTIONS,
        NUMBER_OF_BRL_DEBIT_TRANSACTIONS,
        NUMBER_OF_BRL_CREDIT_TRANSACTIONS,
        NUMBER_OF_USD_TRANSACTIONS,
        TOTAL_AMOUNT,
        TOTAL_BRL_DEBIT_AMOUNT
    );
  }
}
