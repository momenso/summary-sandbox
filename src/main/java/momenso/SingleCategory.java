package momenso;

import java.util.function.Predicate;

public abstract class SingleCategory implements Category {

  private final Predicate<Transaction> predicate;

  protected SingleCategory(Predicate<Transaction> predicate) {
    this.predicate = predicate;
  }

  @Override
  public Number take(Number current, Transaction t) {
    if (predicate.test(t)) {
      return compute(current, t);
    }
    return current;
  }

  protected abstract Number compute(Number current, Transaction transaction);
}
