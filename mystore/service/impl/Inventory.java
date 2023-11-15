/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore.service.impl;

import mystore.model.Product;
import mystore.model.ProductStockPair;
import mystore.service.ProductStockContainer;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements ProductStockContainer {

    // they could've used something else here
    // a single ArrayList with Product-stock Pairs for example
    // could've used a HashMap or other
    private final List<ProductStockPair> productCatalog = new ArrayList<>();

    public Inventory() {
        initialize();
    }

    /**
     * Returns the stock of the specified Product (given by its id).
     *
     * @param pid int : the id of the Product.
     * @return int : -1 if id is invalid, or stock of the specified Product.
     */
    public int getStock(int pid) {
        for (ProductStockPair pair : productCatalog) {
            if (pair.getProduct().getId() == pid) {
                return pair.getStock();
            }
        }
        return -1;
    }


    /**
     * Get the Product from the Inventory corresponding to the id.
     *
     * @param pid int : the id of the Product.
     * @return Product : null if id is invalid, or the Product corresponding to the id.
     */
    public Product getProductInfo(int pid) {
        for (ProductStockPair pair : productCatalog) {
            if (pair.getProduct().getId() == pid) {
                return pair.getProduct();
            }
        }
        return null;
    }

    public List<ProductStockPair> getProductCatalog() {
        return productCatalog;
    }

    private void initialize() {
        // can add some default inventory stock here
        productCatalog.add(new ProductStockPair(new Product(100.0, "SYSC2004", 0), 76));
        productCatalog.add(new ProductStockPair(new Product(55.0, "SYSC4906", 1), 0));
        productCatalog.add(new ProductStockPair(new Product(45.0, "SYSC2006", 2), 32));
        productCatalog.add(new ProductStockPair(new Product(35.0, "MUSI1001", 3), 3));
        productCatalog.add(new ProductStockPair(new Product(0.01, "CRCJ1000", 4), 12));
        productCatalog.add(new ProductStockPair(new Product(25.0, "ELEC4705", 5), 132));
        productCatalog.add(new ProductStockPair(new Product(145.0, "SYSC4907", 6), 322));
    }

    @Override
    public int getProductQuantity(Product product) {
        return 0;
    }

    @Override
    public void addProductQuantity(Product product, int quantity) {
        if (product.getId() < 0) return;
        if (quantity < 1) return;

        for (int i = 0; i < productCatalog.size(); i++) {
            ProductStockPair pair = productCatalog.get(i);
            if (pair.getProduct().getId() == product.getId()) {
                productCatalog.set(i, new ProductStockPair(product, pair.getStock() + quantity));
                return;
            }
        }
        productCatalog.add(new ProductStockPair(product, quantity));

    }

    @Override
    public boolean removeProductQuantity(Product product, int qty) {
        if (qty < 1) return false;

        for (int i = 0; i < productCatalog.size(); i++) {
            ProductStockPair pair = productCatalog.get(i);
            if (pair.getProduct().getId() == product.getId()) {
                if (pair.getStock() >= qty) {
                    productCatalog.set(i, new ProductStockPair(pair.getProduct(), pair.getStock() - qty));
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    @Override
    public int getNumOfProducts() {
        return 0;
    }


}
