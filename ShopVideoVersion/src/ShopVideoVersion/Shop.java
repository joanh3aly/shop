package ShopVideoVersion;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Shop {

	private double cash;
	private ArrayList<ProductStock> stock;
	private ArrayList<ProductStock> shoppingList;
	

	public Shop(String fileName) {
		stock = new ArrayList<>();
		List<String> lines = Collections.emptyList();
		try {
			lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
			System.out.println(lines.get(0));
			cash = Double.parseDouble(lines.get(0));
			// i am removing at index 0 as it is the only one treated differently
			lines.remove(0);
			for (String line : lines) {
				String[] arr = line.split(",");
				String name = arr[0];
				double price = Double.parseDouble(arr[1]);
				int quantity = Integer.parseInt(arr[2].trim());
				Product p = new Product(name, price);
				ProductStock s = new ProductStock(p, quantity);
				stock.add(s);
			}
			
		}

		catch (IOException e) {

			// do something
			e.printStackTrace();
		}
	}

	public double getCash() {
		return cash;
	}
	

	public void setCash(double cash) {
		this.cash = cash;
	}

	public ArrayList<ProductStock> getStock() {
		return stock;
	}

	@Override
	public String toString() {
		return "Shop [cash=" + cash + ", stock=" + stock + "]";
	}
	
	private double findPrice(String name) {
		
		for (ProductStock productStock : stock) {
			Product p =productStock.getProduct();
			if (p.getName().equals(name)) {
				return p.getPrice();
			}
		}
		
		return -1;
	}
	
	private void compareLists( String inputName, int customerQuantity, double customerBudget, Customer c) {
//		Customer c = new Customer("src/ShopVideoVersion/customer.csv");
//		
		for (ProductStock productStock : stock) {
			Product p = productStock.getProduct();
			
			if (p.getName().equals(inputName)) {
				System.out.println(".............................................\n");
				System.out.println("Product name requested : "+ inputName);
				System.out.println("Quantity of product requested : "+ customerQuantity);
            	double totalPricePerProduct = p.getPrice() * customerQuantity;
            	System.out.println("Total price for "+ p.getName() +" is "+ totalPricePerProduct);
            	if (productStock.getQuantity() == 0) {
            		System.out.println("Quantity of items of "+ p.getName() +" in shop is "+ productStock.getQuantity());
    				System.out.println(p.getName() +" is out of stock");
    			} else if (totalPricePerProduct > customerBudget) {
    				System.out.println("Customer budget is now "+ c.getBudget());
					System.out.println(p.getName() +" is over budget");
					break;
				} else if (customerQuantity > productStock.getQuantity()) {	
					System.out.println("Number of "+ p.getName() +" in shopping list exceeds stock capacity.");
					double totalPerProduct = p.getPrice() * productStock.getQuantity();
					System.out.println("Total cost of remaining items in stock is "+ totalPerProduct);
					double shopCash = this.getCash();
					System.out.println("Cash in shop was "+ shopCash);
					shopCash += totalPerProduct;
					this.setCash(shopCash);
					System.out.println("Cash in shop is now "+ this.getCash());
					System.out.println("Customer budget was "+ customerBudget);
					customerBudget -= totalPerProduct;
					c.setBudget(customerBudget);
					System.out.println("Customer budget is now "+ c.getBudget());
					System.out.println("Quantity of items of "+ p.getName() +" in shop was "+ productStock.getQuantity());
					int shopQuantity = productStock.getQuantity() - customerQuantity;
					if (shopQuantity < 0 ) {
						productStock.setQuantity(0);
						System.out.println("Quantity of items of "+ p.getName() +" in shop is now "+ productStock.getQuantity());
					} else {
						productStock.setQuantity(shopQuantity);
						System.out.println("Quantity of items of "+ p.getName() +" in shop is now "+ productStock.getQuantity());
					}			
				} else {
					double totalPerProduct = p.getPrice() * customerQuantity;
					System.out.println("Total of requested items is "+ totalPerProduct);
					double shopCash = this.getCash();
					System.out.println("Cash in shop was "+ shopCash);
					shopCash += totalPerProduct;
					this.setCash(shopCash);
					System.out.println("Cash in shop is now "+ this.getCash());
					System.out.println("Customer budget was "+ customerBudget);
					customerBudget -= totalPerProduct;
					c.setBudget(customerBudget);
					System.out.println("Customer budget is now "+ c.getBudget());
					int shopQuantity = productStock.getQuantity() - customerQuantity;
					productStock.setQuantity(shopQuantity);
					System.out.println("Quantity of items of "+ p.getName() +" in shop is now "+ productStock.getQuantity());
				}
			}						
		}
	}
	
	public void processInputOrder(Customer c) {
		shoppingList = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		System.out.println("What product do you want to buy?");
		String productName = scan.nextLine();

		System.out.println("How many " + productName + " do you want?");
		int amount = scan.nextInt();

		System.out.println("How much money do you have?");
		double customerBudget = scan.nextDouble();

		System.out.println("You want to buy " + amount + " " + productName + " and you have " + customerBudget + ".");
		scan.close();
		
		Product product = new Product(productName, 0);
		ProductStock ps = new ProductStock(product, amount);
		shoppingList.add(ps);
		
		compareLists(productName, amount, customerBudget, c);
	}
	
	public void updateShopFromCsv(Customer c, Shop s) {		
		ListIterator<ProductStock> customerIterator = c.getShoppingList().listIterator(); 
		ListIterator<ProductStock> shopIterator = s.getStock().listIterator(); 
		
		while (customerIterator.hasNext()) { 
        	ProductStock customerList = customerIterator.next();
        	String customerProductName = customerList.getProduct().getName();
        	int customerQuantity = customerList.getQuantity();
        	double customerBudget = c.getBudget();
        	compareLists(customerProductName, customerQuantity, customerBudget, c);
        }
    }	

	
	public static void main(String[] args) {
		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
		Customer c = new Customer("src/ShopVideoVersion/customer.csv");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("For live mode press 1, for bulk mode press 2");
		int mode = scan.nextInt();
		System.out.println("Mode chosen: "+ mode);
		if (c.getBudget() > 0) {
			if (mode == 1) {
				shop.processInputOrder(c);
			} else if (mode == 2) {
				shop.updateShopFromCsv(c, shop);
			}	
		} else {
			System.out.println("You are out of budget");	
		}
	}
	
}
