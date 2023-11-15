/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore.service.impl;

import mystore.exception.ProductNotFoundException;
import mystore.model.Product;
import mystore.model.ProductStockPair;
import mystore.service.ProductStockContainer;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart implements ProductStockContainer {
    private List<ProductStockPair> contents = new ArrayList<>();
    private int cartID;
    private int total = 0;

    public ShoppingCart(int cartID) {
        this.cartID = cartID;
    }

    public List<ProductStockPair> getContents() {
        return contents;
    }

    public void clear() {
        contents.clear();
    }

    public int getCartID() {
        return cartID;
    }

    @Override
    public int getProductQuantity(Product p) {
    	
        return 0;
    }

    @Override
    public void addProductQuantity(Product p, int amount) throws ProductNotFoundException {
        try {

            if (amount < 1)
                return;

            total += p.getPrice() * amount;
            for (int i = 0; i < contents.size(); i++) {
                ProductStockPair item = contents.get(i);
                if (item.getProduct().getId()== p.getId()) {
                    contents.set(i, new ProductStockPair(item.getProduct(), item.getStock() + amount));
                    return;
                }
            }
            contents.add(new ProductStockPair(p, amount));
        } catch (Exception e) {
            throw new ProductNotFoundException("Product is not Available");
        }

    }

    @Override
    public boolean removeProductQuantity(Product p, int amount) throws ProductNotFoundException {
        try {

            if (amount < 1)
                return false;

            for (int i = 0; i < contents.size(); i++) {
                ProductStockPair item = contents.get(i);
                if (item.getProduct().getId() == p.getId()) {
                    if (item.getStock() < amount) {
                        return false;
                    } else if (item.getStock() == amount) {
                        contents.remove(i); // delete
                    } else {
                        contents.set(i, new ProductStockPair(item.getProduct(), item.getStock() - amount));
                    }
                    total -= p.getPrice() * amount;
                    return true;
                }
            }
        } catch (Exception e) {
           throw new ProductNotFoundException("Product is not Available");
        }
        // didnt find it
        return false;
    }

    @Override
    public int getNumOfProducts() {
    	// Bijaya - it should return the tootal number of product
        return total;
    }
}
