/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore.service;

import mystore.model.Product;
import mystore.model.ProductStockPair;
import mystore.service.impl.Inventory;
import mystore.service.impl.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class StoreManager {
	private Inventory storeInventory = new Inventory();
	private List<ShoppingCart> shoppingCarts = new ArrayList<>();
	private int shoppingCartCounter = 0;

	public int getProductStock(int pid) {
		return storeInventory.getStock(pid);
	}

	// makes a new cart and returns the cartid
	public int assignNewCartID() {
		int id;
		try {
			id = shoppingCartCounter++;
			shoppingCarts.add(new ShoppingCart(id));
		} catch (Exception e) {
			System.out.println("Error Occurred");
			return -1;
		}
		return id;
	}

	public boolean addToCart(int cartID, Product p, int amount) {
		try {
			if (storeInventory.removeProductQuantity(p, amount)) {
				for (ShoppingCart cart : shoppingCarts) {
					if (cart.getCartID() == cartID) {
						cart.addProductQuantity(p, amount);
						return true;
					}
				}
				// put items back
				storeInventory.addProductQuantity(p, amount);
			}
		} catch (Exception e) {
			System.out.println("Error Occurrred");
		}
		return false;
	}

	public boolean removeFromCart(int cartID, Product p, int amount) {
		try {
			for (ShoppingCart cart : shoppingCarts) {
				if (cart.getCartID() == cartID) {
					if (cart.removeProductQuantity(p, amount)) {
						storeInventory.addProductQuantity(p, amount);
						return true;
					}
					return false;
				}
			}
		} catch (Exception e) {
			System.out.println("Error Occurred");
		}
		return false;
	}

	public List<ProductStockPair> getAvailableProducts() {
		return storeInventory.getProductCatalog();
	}

	public List<ProductStockPair> getCartContents(int cartID) {
		try {
			for (ShoppingCart cart : shoppingCarts) {
				if (cart.getCartID() == cartID) {
					return cart.getContents();
				}
			}
		} catch (Exception e) {
			System.out.println("Error Occurred");
		}
		return new ArrayList<>(); // just give an empty ArrayList if not found
	}

	// on quit
	public void clearCartContents(int cartID) {
		try {
			for (ShoppingCart cart : shoppingCarts) {
				if (cart.getCartID() == cartID) {
					List<ProductStockPair> stuffToPutBack = cart.getContents();
					for (ProductStockPair pair : stuffToPutBack) {
						storeInventory.addProductQuantity(pair.getProduct(), pair.getStock());
					}
					// delete cart from shoppingCarts
					shoppingCarts.remove(cart);
					return;
				}
			}
		} catch (Exception e) {
			System.out.println("Error Occurred");
		}
	}

	public double getCartTotal(int cartID) {
		for (ShoppingCart cart : shoppingCarts) {
			if (cart.getCartID() == cartID) {
				return cart.getNumOfProducts();
			}
		}
		return -1.0;
	}
}
