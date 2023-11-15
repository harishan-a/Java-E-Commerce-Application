/**
 * Oliver Lu, 101155667
 * Harishan Amutheesan, 101154757
 */
package mystore;

import mystore.model.ProductStockPair;
import mystore.service.StoreManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class StoreView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private StoreManager sm;
	private int cartID;
	private String currentPage;

	public StoreView(StoreManager sm, int cartID) {
		this.sm = sm;
		this.cartID = cartID;
		getContentPane().setLayout(null);
		setTitle("The Course Store");

		JLabel lblNewLabel = new JLabel("Welcome to The Course Store !( Cart ID : " + cartID + ")");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 500, 25);
		getContentPane().add(lblNewLabel);

		JPanel shoppingControlPanel = new JPanel();
		shoppingControlPanel.setBackground(Color.LIGHT_GRAY);
		shoppingControlPanel.setBounds(347, 44, 215, 494);
		setSize(600, 600);
		getContentPane().add(shoppingControlPanel);

		JButton viewCartBtn = new JButton("View Cart");
		viewCartBtn.setBounds(45, 11, 125, 43);
		try {
			viewCartBtn.setIcon(new ImageIcon(ImageIO.read(new File("res/checkout.png"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		viewCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setBackground(Color.white);
				frame.setSize(300, 300);
				JPanel panel = new JPanel();
				panel.setBackground(Color.white);
				JTextArea jLabel = new JTextArea("Cart Content\n---------------\nProduct Name     Qty         Price");
				jLabel.setFont(new Font("comic sans ms", Font.BOLD, 14));

				jLabel.setEditable(false);
				jLabel.setBounds(0, 0, 300, 300);
				if (sm.getCartContents(cartID).size() == 0) {
					jLabel.setText("Nothing added in Cart!");
				} else {
					int items = 0;
					float totalCost = 0;
					int totalNumberOfItem=0; 
					for (ProductStockPair pair : sm.getCartContents(cartID)) {
						items++;
						jLabel.setText(jLabel.getText() + "\n" + pair.getProduct().getName() + "           " + pair.getStock()
								+ "        " + pair.getStock() * pair.getProduct().getPrice());
						totalCost += pair.getStock() * pair.getProduct().getPrice();
						totalNumberOfItem+=pair.getStock();
					}
					jLabel.setText(jLabel.getText() + "\nGrand Total  "+totalNumberOfItem+ "           "+totalCost);

					jLabel.setText(jLabel.getText() + "\nTotal Items " + items);

				}
				panel.add(jLabel);
				frame.setContentPane(panel);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setTitle("Cart Content");
			}
		});
		shoppingControlPanel.setLayout(null);
		shoppingControlPanel.add(viewCartBtn);

		JButton checkOutBtn = new JButton("Checkout");
		checkOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame();
				frame.setBackground(Color.white);
				frame.setSize(600, 600);
				JPanel panel = new JPanel();
				panel.setBackground(Color.white);
				JTextArea jLabel = new JTextArea("Cart Content\n---------------\nProduct Name     Qty         Price");
				jLabel.setFont(new Font("comic sans ms", Font.BOLD, 14));

				jLabel.setEditable(false);
				StringBuilder sb = new StringBuilder("|--------------------THE COURSE STORE--------------------|\n"
						+ "--------------------------CART--------------------------\n" + "Cart: " + cartID + "\n"
						+ "Amount in Cart | Product Name | Unit Price\n");

				for (ProductStockPair item : sm.getCartContents(cartID)) {
					sb.append(item.getStock()).append(" | ").append(item.getProduct().getName()).append(" | $")
							.append(item.getProduct().getPrice()).append('\n');
				}
				sb.append("Your total is: $ ").append(sm.getCartTotal(cartID))
						.append("\nThank you have a great day!\n");
				jLabel.setText(sb.toString());
				panel.add(jLabel);
				frame.setContentPane(panel);
				frame.setVisible(true);
				frame.setResizable(false);
				frame.setTitle("Checkout");

			}
		});
		checkOutBtn.setBounds(65, 57, 89, 31);
		shoppingControlPanel.add(checkOutBtn);

		JButton quitBtn = new JButton("Quit");
		quitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitBtn.setBounds(75, 90, 67, 23);
		shoppingControlPanel.add(quitBtn);

		JPanel panel = new JPanel();
		panel.setBackground(Color.MAGENTA);
		panel.setBounds(10, 185, 195, 298);
		shoppingControlPanel.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("SYSC");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setBounds(38, 9, 46, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("courses in!");
		lblNewLabel_3.setBounds(83, 11, 70, 14);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("All courses marked down at least");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_4.setBounds(10, 48, 175, 52);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(" 15%");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_5.setBounds(10, 84, 46, 14);
		panel.add(lblNewLabel_5);

		JLabel saleImageLabel = new JLabel("");
		saleImageLabel.setBounds(20, 120, 165, 168);
		try {
			BufferedImage myPicture = ImageIO.read(new File("res/sale.png"));
			saleImageLabel.setIcon(new ImageIcon(myPicture));

		} catch (Exception e) {
			e.printStackTrace();
		}

		panel.add(saleImageLabel);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		List<ProductStockPair> products = sm.getAvailableProducts();
		for (ProductStockPair pair : products) {
			JPanel productPanel = new JPanel();
			productPanel.add(new JLabel(pair.getProduct().getName()));

		}

		JScrollPane productPanel = new JScrollPane();
		Border border = BorderFactory.createTitledBorder("Products");
		productPanel.setBorder(border);
		productPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		productPanel.setBackground(Color.LIGHT_GRAY);
		productPanel.setBounds(10, 52, 300, 500);
		getContentPane().add(productPanel);
		int y = 0;
		int x = 0;
		for (ProductStockPair pair : products) {
			JPanel prodPanel = new JPanel();

			if (x >= 100) {
				prodPanel.setBackground(Color.white);
				prodPanel.setBounds(20 + (int) (1.5 * x), 20 + (int) (1.5 * y), 120, 120);
			} else {
				prodPanel.setBackground(Color.white);
				prodPanel.setBounds(20 + x, 20 + (int) (1.5 * y), 120, 120);
			}
			productPanel.add(prodPanel);

			productPanel.add(prodPanel);
			panel.setLayout(null);
			BufferedImage myPicture = null;
			try {
				myPicture = ImageIO.read(new File("res/book.jpg"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			JLabel prodName = new JLabel(pair.getProduct().getName());
			prodName.setBounds(30, 0, 100, 20);
			prodPanel.add(prodName);
			JLabel productImageBG = new JLabel(new ImageIcon(myPicture));
			productImageBG.setFont(new Font("Tahoma", Font.PLAIN, 10));
			productImageBG.setBorder(new BevelBorder(WIDTH, Color.BLACK, Color.white));
			prodPanel.setLayout(null);
			productImageBG.setBounds(10, 15, 100, 70);
			prodPanel.add(productImageBG);
			JButton plus = new JButton("+");
			plus.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println("Adding Product :" + pair.getProduct().getName());
					if (sm.getProductStock(pair.getProduct().getId()) > 0)
						sm.addToCart(cartID, pair.getProduct(), 1);
					else {
						JOptionPane.showMessageDialog(prodPanel,
								"No more stock for " + pair.getProduct().getName() + " available.");

					}
				}
			});
			plus.setFont(new Font("comic sans ms", Font.BOLD, 10));
			plus.setBounds(20, 95, 40, 25);
			shoppingControlPanel.add(checkOutBtn);
			JButton minus = new JButton("-");
			minus.setFont(new Font("comic sans ms", Font.BOLD, 10));
			minus.setBounds(60, 95, 40, 25);
			minus.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					System.out.println("Removing Product :" + pair.getProduct().getName());
					int inCart = 0;
					for (ProductStockPair productPair : sm.getCartContents(cartID)) {
						if (productPair.getProduct().getId() == pair.getProduct().getId()) {
							inCart = productPair.getStock();
						}

					}
					if (inCart > 0)
						sm.removeFromCart(cartID, pair.getProduct(), 1);
					else {
						JOptionPane.showMessageDialog(prodPanel, "No Product " + pair.getProduct().getName() + " exists in Cart");
					}

				}
			});
			prodPanel.add(plus);
			prodPanel.add(minus);

			if (x == 100) {
				x = 0;
				y += 100;
			} else
				x += 100;
			productPanel.add(prodPanel);

		}

		setResizable(false);
		createUI();
	}

	private void createUI() {

	}

	public static void main(String[] args) {
		StoreManager sm = new StoreManager();
		StoreView sv1 = new StoreView(sm, sm.assignNewCartID());
	}
}
