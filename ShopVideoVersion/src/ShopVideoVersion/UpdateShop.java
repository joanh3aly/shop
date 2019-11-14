//package ShopVideoVersion;
//
//import java.util.ArrayList;
//import java.util.ListIterator;
//
//public class UpdateShop {
//	
////	private String shopProductName;
//	
////	public UpdateShop() {
//	public UpdateShop(Customer c, Shop s) {
////		super();
//		
////		Shop s = new Shop("src/ShopVideoVersion/stock.csv");
////		UserInput c = new UserInput();
//
//		
//		ListIterator<ProductStock> customerIterator = c.getShoppingList().listIterator(); 
//		ListIterator<ProductStock> shopIterator = s.getStock().listIterator(); 
//		
//
//		
//		while (customerIterator.hasNext()) { 
//            while (shopIterator.hasNext()) { 
////            	ProductStock customerList = customerIterator.next();
////            	ProductStock shopList = shopIterator.next();
//            	ProductStock customerList = customerIterator.next();
//            	ProductStock shopList = shopIterator.next();
//                if (customerList.getProduct().getName().equals(shopList.getProduct().getName())) {
//                	int customerQuantity = customerList.getQuantity();
//                	int shopQuantity = shopList.getQuantity();
//                	double price = shopList.getProduct().getPrice();
//                	double totalPricePerProduct = price * customerQuantity;
//                	System.out.println(totalPricePerProduct);
//                	double customerBudget = c.getBudget();
//                	System.out.println(customerBudget);
//                	
//                	if (shopQuantity == 0) 
//            			{
//            				System.out.println(customerList.getProduct().getName());
//            				System.out.println("Out of stock");
//            			}
//                	else 
//        				{
//        				if (totalPricePerProduct > c.getBudget())
//        				{
//        					System.out.println(customerList.getProduct().getName());
//        					System.out.println("this is over budget");
//        				}
//        				else 
////        					{
//        					if (customerQuantity > shopQuantity)
//        					{	
//        						System.out.println(customerQuantity);
//        						System.out.println(customerList.getProduct().getName());
//        						System.out.println("Number of items in shopping list exceeds stock capacity.");
//        						double totalPerProduct = price * shopQuantity;
//        						System.out.println("Total of remaining items is ");
//        						System.out.println(totalPerProduct);
//        						double shopCash = s.getCash();
//        						shopCash += totalPerProduct;
//        						System.out.println("Cash in shop is now ");
//        						System.out.println(shopCash);
//        						customerBudget -= totalPerProduct;
//        						System.out.println("Customer budget is now ");
//        						System.out.println(customerBudget);
//        						shopQuantity -= customerQuantity;
//        						System.out.println("Quantity of items in shop is now ");
//        						System.out.println(shopQuantity);
//        						// struct Product shopProductUpdate = { product.name, product.price };
//        						// struct ProductStock stockItem = { shopProductUpdate, shopProductQuantity };
//        						// s.stock[s.index++] = stockItem;
//        					}
//        			}
//                }
//            }	
//		
//		}
//	}
//	
//	public static void main(String[] args) {
//		Shop shop = new Shop("src/ShopVideoVersion/stock.csv");
//		
//		Customer james = new Customer("src/ShopVideoVersion/customer.csv");
//		System.out.println(james);
//		UpdateShop update = new UpdateShop(james, shop);
//		
////		UserInput SLuser = new UserInput();
////		System.out.println(SLuser.getName());
////		UpdateShop update = new UpdateShop(SLuser, shop);
////		UpdateShop update = new UpdateShop();
//			
//	
//	}
//
//}
//
