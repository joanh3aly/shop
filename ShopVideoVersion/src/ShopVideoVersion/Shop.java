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
	
	private void compareLists(String name, String inputName, int customerQuantity, double customerBudget) {
//		System.out.println(inputName);
		
		for (ProductStock productStock : stock) {
			Product p = productStock.getProduct();
//			System.out.println("p.getName() "+ p.getName());
			if (p.getName().equals(inputName)) {
				System.out.println("inputName "+ inputName);
            	double totalPricePerProduct = p.getPrice() * customerQuantity;
            	System.out.println("Total price for "+ p.getName() +" is "+ totalPricePerProduct);
            	if (productStock.getQuantity() == 0) {
//            		int shopQuantity = productStock.getQuantity();
    				System.out.println(p.getName() +" is out of stock");
    			} else if (totalPricePerProduct > customerBudget) {
					System.out.println(p.getName() +" is over budget");
					break;
				} else if (customerQuantity > productStock.getQuantity()) {	
					System.out.println("Number of "+ p.getName() +" in shopping list exceeds stock capacity.");
					double totalPerProduct = p.getPrice() * productStock.getQuantity();
					System.out.println("Total of remaining items is "+ totalPerProduct);
					double shopCash = this.getCash();
					System.out.println("Cash in shop was "+ shopCash);
					shopCash += totalPerProduct;
					System.out.println("Cash in shop is now "+ shopCash);
					customerBudget -= totalPerProduct;
					System.out.println("Customer budget is now "+ customerBudget);
					int shopQuantity = productStock.getQuantity() - customerQuantity;
					System.out.println("Quantity of items of "+ p.getName() +" in shop is now "+ shopQuantity);
				} else {
					double totalPerProduct = p.getPrice() * customerQuantity;
					System.out.println("Total of remaining items is "+ totalPerProduct);
					double shopCash = this.getCash();
					System.out.println("Cash in shop was "+ shopCash);
					shopCash += totalPerProduct;
					System.out.println("Cash in shop is now "+ shopCash);
					customerBudget -= totalPerProduct;
					System.out.println("Customer budget is now "+ customerBudget);
					int shopQuantity = productStock.getQuantity() - customerQuantity;
					System.out.println("Quantity of items of "+ p.getName() +" in shop is now "+ shopQuantity);
				}
			}
    	}
	}
	
	public void processOrder(Customer c) {
		for (ProductStock productStock : stock) {
			Product p = productStock.getProduct();
			int quantity = productStock.getQuantity();
			double price = findPrice(p.getName());
			System.out.println(p.getName() + " costs " + price);
			
//			p.setPrice(price);
		}
	}
	
	public void processInputOrder() {
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
		
		for (ProductStock productStock : stock) {
			Product p = productStock.getProduct();
			int quantity = productStock.getQuantity();
//			double price = findPrice(p.getName());
			compareLists(p.getName(), productName, amount, customerBudget);
//			System.out.println(p.getName() + " costs " + price);
//			
//			p.setPrice(price);
		}
	}
	
	public void updateShopFromCsv(Customer c, Shop s) {
		
		ListIterator<ProductStock> customerIterator = c.getShoppingList().listIterator(); 
		ListIterator<ProductStock> shopIterator = s.getStock().listIterator(); 
		
		while (customerIterator.hasNext()) { 
            while (shopIterator.hasNext()) { 
            	ProductStock customerList = customerIterator.next();
            	ProductStock shopList = shopIterator.next();
                if (customerList.getProduct().getName().equals(shopList.getProduct().getName())) {
                	int customerQuantity = customerList.getQuantity();
                	int shopQuantity = shopList.getQuantity();
                	double price = shopList.getProduct().getPrice();
                	double totalPricePerProduct = price * customerQuantity;
                	System.out.println(totalPricePerProduct);
                	double customerBudget = c.getBudget();
                	System.out.println(customerBudget);
                	
                	if (shopQuantity == 0) 
            			{
            				System.out.println(customerList.getProduct().getName());
            				System.out.println("Out of stock");
            			}
                	else 
        				{
        				if (totalPricePerProduct > c.getBudget())
        				{
        					System.out.println(customerList.getProduct().getName());
        					System.out.println("this is over budget");
        				}
        				else 
//        					{
        					if (customerQuantity > shopQuantity)
        					{	
        						System.out.println(customerQuantity);
        						System.out.println(customerList.getProduct().getName());
        						System.out.println("Number of items in shopping list exceeds stock capacity.");
        						double totalPerProduct = price * shopQuantity;
        						System.out.println("Total of remaining items is ");
        						System.out.println(totalPerProduct);
        						double shopCash = s.getCash();
        						shopCash += totalPerProduct;
        						System.out.println("Cash in shop is now ");
        						System.out.println(shopCash);
        						customerBudget -= totalPerProduct;
        						System.out.println("Customer budget is now ");
        						System.out.println(customerBudget);
        						shopQuantity -= customerQuantity;
        						System.out.println("Quantity of items in shop is now ");
        						System.out.println(shopQuantity);
        						// struct Product shopProductUpdate = { product.name, product.price };
        						// struct ProductStock stockItem = { shopProductUpdate, shopProductQuantity };
        						// s.stock[s.index++] = stockItem;
        					}
        			}
                }
            }	
		
		}
	}
	
	public static void main(String[] args) {
		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
		Customer c = new Customer("src/ShopVideoVersion/customer.csv");
		
		Scanner scan = new Scanner(System.in);
		System.out.println("For live mode press 1, for bulk mode press 2");
		int mode = scan.nextInt();
		System.out.println("Mode chosen: "+ mode);
		if (mode == 1) {
			shop.processInputOrder();
		} else if (mode == 2) {
			shop.updateShopFromCsv(c, shop);
		}		
	
	}
	
	

}
