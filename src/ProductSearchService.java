import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductSearchService {
    private List<OnlineStore> stores;

    public ProductSearchService() {
        this.stores = new ArrayList<>();
    }

    public void addStore(OnlineStore store) {
        stores.add(store);
    }

    /**
     * Задача 1: Знайти мінімальну ціну на заданий товар.
     * Вимога: Типізований ітератор (варіант b).
     */
    public double findMinPrice(String productName) {
        double minPrice = Double.MAX_VALUE;
        boolean found = false;

        Iterator<OnlineStore> storeIterator = stores.iterator();
        while (storeIterator.hasNext()) {
            OnlineStore store = storeIterator.next();

            Iterator<StoreItem> itemIterator = store.getItems().iterator();
            while (itemIterator.hasNext()) {
                StoreItem item = itemIterator.next();
                if (item.getProduct().getName().equals(productName)) {
                    if (item.getPrice() < minPrice) {
                        minPrice = item.getPrice();
                        found = true;
                    }
                }
            }
        }
        return found ? minPrice : -1;
    }

    /**
     * Задача 2: Скласти список магазинів з мінімальною ціною на товар.
     * Вимога: Нетипізований ітератор (варіант a).
     */
    public List<OnlineStore> findStoresWithMinPrice(String productName) {
        double minPrice = findMinPrice(productName);
        List<OnlineStore> resultStores = new ArrayList<>();

        if (minPrice == -1) return resultStores;

        // Нетипізований ітератор (Raw Iterator)
        Iterator storeIterator = stores.iterator();
        while (storeIterator.hasNext()) {
            Object storeObj = storeIterator.next();
            if (storeObj instanceof OnlineStore) {
                OnlineStore store = (OnlineStore) storeObj;

                Iterator itemIterator = store.getItems().iterator();
                while (itemIterator.hasNext()) {
                    Object itemObj = itemIterator.next();
                    if (itemObj instanceof StoreItem) {
                        StoreItem item = (StoreItem) itemObj;
                        if (item.getProduct().getName().equals(productName) && item.getPrice() == minPrice) {
                            resultStores.add(store);
                            break;
                        }
                    }
                }
            }
        }
        return resultStores;
    }

    /**
     * Задача 3: Чи є магазин, де всі товари дешевші за рекомендовану ціну.
     * Вимога: Типізований цикл for-each (варіант c).
     */
    public OnlineStore findCheapStore() {
        for (OnlineStore store : stores) {
            boolean allCheaper = true;

            if (store.getItems().isEmpty()) {
                allCheaper = false;
            }

            for (StoreItem item : store.getItems()) {
                if (item.getPrice() >= item.getProduct().getRecommendedPrice()) {
                    allCheaper = false;
                    break;
                }
            }

            if (allCheaper) {
                return store;
            }
        }
        return null;
    }

    // Демонстрація роботи
    public static void main(String[] args) {
        Product p1 = new Product("Laptop", 1000.0);
        Product p2 = new Product("Phone", 500.0);
        Product p3 = new Product("Mouse", 50.0);

        OnlineStore s1 = new OnlineStore("Rozetka");
        s1.addItem(p1, 950.0);
        s1.addItem(p2, 480.0);
        s1.addItem(p3, 45.0);

        OnlineStore s2 = new OnlineStore("Comfy");
        s2.addItem(p1, 920.0);
        s2.addItem(p2, 510.0);

        OnlineStore s3 = new OnlineStore("Foxtrot");
        s3.addItem(p1, 920.0);
        s3.addItem(p3, 48.0);

        ProductSearchService service = new ProductSearchService();
        service.addStore(s1);
        service.addStore(s2);
        service.addStore(s3);

        System.out.println("--- Task 1 (Min Price for Laptop) ---");
        System.out.println("Min Price: " + service.findMinPrice("Laptop"));

        System.out.println("\n--- Task 2 (Stores with Min Price for Laptop) ---");
        List<OnlineStore> cheapStores = service.findStoresWithMinPrice("Laptop");
        for (OnlineStore st : cheapStores) {
            System.out.println(st.getName());
        }

        System.out.println("\n--- Task 3 (Store where all items < recommended price) ---");
        OnlineStore perfectStore = service.findCheapStore();
        if (perfectStore != null) {
            System.out.println("Found store: " + perfectStore.getName());
        } else {
            System.out.println("No such store found.");
        }
    }
}
