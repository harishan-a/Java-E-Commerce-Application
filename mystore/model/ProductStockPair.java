/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore.model;

public class ProductStockPair {

   private Product product;
    private int stock;

    public ProductStockPair(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
