import java.util.ArrayList;
import java.util.List;

public class OnlineStore {
    private String name;
    private List<StoreItem> items; // Список товарів та цін

    public OnlineStore(String name) {
        this.name = name;
        this.items = new ArrayList<>();
    }

    public void addItem(Product product, double price) {
        items.add(new StoreItem(product, price));
    }

    public String getName() {
        return name;
    }

    public List<StoreItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Store: " + name;
    }
}
