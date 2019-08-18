package momenso;

import java.util.function.Predicate;

public class CountCategory extends SingleCategory {

  private CountCategory(Predicate<Transaction> condition) {
    super(condition);
  }

  public static CountCategory of(Predicate<Transaction> condition) {
    return new CountCategory(condition);
  }

  @Override
  public Number compute(Number current, Transaction transaction) {
      return current.longValue() + 1;
  }
}
