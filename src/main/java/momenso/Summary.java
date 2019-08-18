package momenso;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class Summary {

  protected static class CategorySlot {

    public final Category category;
    public Number value;

    public CategorySlot(Category category) {
      this.category = category;
      this.value = 0;
    }

    public Category getCategory() {
      return category;
    }

    public Number getValue() {
      return value;
    }
  }

  protected abstract Collection<Category> getCategories();

  private final Map<Category, CategorySlot> categories;

  public Summary() {
    this.categories = getCategories().stream()
        .collect(Collectors.toMap(Function.identity(), CategorySlot::new));
  }

  public void add(Transaction transaction) {
    categories.values().forEach(slot ->
        slot.value = slot.getCategory().take(slot.getValue(), transaction)
    );
  }

  public String get(Category category) {
    if (!categories.containsKey(category)) {
      throw new IllegalArgumentException("Summary category not defined: " + category);
    }

    return categories
        .get(category)
        .value.toString();
  }
}
