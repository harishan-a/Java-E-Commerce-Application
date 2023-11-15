/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore.service;

import mystore.exception.ProductNotFoundException;
import mystore.model.Product;

public interface ProductStockContainer {
   public  int getProductQuantity(Product p);
   public void addProductQuantity(Product p,int quantity) throws ProductNotFoundException;
   public boolean removeProductQuantity(Product p,int qty) throws ProductNotFoundException;
   public int getNumOfProducts();
}
