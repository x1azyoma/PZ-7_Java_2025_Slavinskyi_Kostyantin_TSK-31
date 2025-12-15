public class StoreItem {
    private Product product;
    private double price;

    public StoreItem(Product product, double price) {
        this.product = product;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return product.getName() + " - " + price;
    }
}
