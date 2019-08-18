package momenso;

import java.util.function.Predicate;

public class SumCategory extends SingleCategory {

  private SumCategory(Predicate<Transaction> predicate) {
    super(predicate);
  }

  public static SumCategory of(Predicate<Transaction> condition) {
    return new SumCategory(condition);
  }

  @Override
  public Number compute(Number current, Transaction t) {
    return current.longValue() + Long.parseLong(t.getValue());
  }
}
