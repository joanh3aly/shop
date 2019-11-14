//package ShopVideoVersion;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//public class UserInput {
//	
//	private String name;
//	private double budget;
//	private int quantity;
//	private ArrayList<ProductStock> shoppingList;
//	private String line;
//	private String[] arr;
//	private String item;
//	private String product;
//	private int counter = 0;
//	String anotherItem = "N";
//
//	public UserInput() {
//
//		shoppingList = new ArrayList<>();	
//		
//	    Scanner myObj = new Scanner(System.in);
//	    
//	    
//	    System.out.println("Enter product item:");
//	    item = myObj.nextLine();
//
//	    System.out.println("Enter budget:");
////	    name = myObj.nextLine();
////	    System.out.println("Name: " + name);
//	    budget = myObj.nextDouble();
//	  	    
//	    System.out.println("Enter quantity:");
//	    quantity = myObj.nextInt();
//	    
//	    System.out.println("item:"+ item);
//	    System.out.println("quantity:"+ quantity);
//	    System.out.println("budget: " + budget);
//	    
//	    
//	    Product product = new Product(item, 0);
//		ProductStock productStock = new ProductStock(product, quantity);
//		shoppingList.add(productStock);
//	    
//		name = "Joan";
//	    
////	    while(counter < 5){
////	    	System.out.println("Enter item + quantity:");
////	    	String line = myObj.nextLine();
////	    	System.out.println("line: " + line);
////	    	String[] arr = line.split(",");
////		    System.out.println("arr: " + arr);
////		    String item = arr[0];
////		    System.out.println("item: " + item);
////		    
////			quantity = Integer.parseInt(arr[1].trim());
////			Product product = new Product(item, 0);
////			ProductStock productStock = new ProductStock(product, quantity);
////			shoppingList.add(productStock);
////			
////		    System.out.println("Enter another Y/N?");
////		    anotherItem = myObj.nextLine();
////		    		
////		    if (anotherItem.equals("y")){
////			    System.out.println("YES");
//////			   	check = true;
////	    		counter++;
////			} else if (anotherItem.equals("n")){
////				System.out.println("OK");
//////				check = false;
////				break;
////			}
////	    }
//	    	
////	    System.out.println("counter:"+ counter);
////	    System.out.println("nter item + quantity");
////	    line = myObj.nextLine();
////	    System.out.println("line: " + line);
//	    
////	    try {
////	    	while(counter < 2){
////		    	System.out.println("Enter item + quantity:");
////		    	line = myObj.nextLine();
//		    	
////		    	if (!line.isEmpty())
////			    	System.out.println("line: " + line);
////			    	arr = line.split(",");
////				    item = arr[0];
////				    System.out.println("item: " + item);	    
////				    quantity = Integer.parseInt(arr[1].trim());
////					Product product = new Product(item, 0);
////					ProductStock productStock = new ProductStock(product, quantity);
////					shoppingList.add(productStock);
////					
////				    System.out.println("Enter another Y/N?");
////				    anotherItem = myObj.nextLine();			    		
////				    if (anotherItem.equals("y")){		    
////	//				   	check = true;
////			    		counter++;
////			    		System.out.println("counter:" + counter);
////					} else if (anotherItem.equals("n")){
////						System.out.println("OK");
////	//					check = false;
//////						break;
////					}
////		    }
//			
////		} finally {
////			System.out.println("No more room in shopping list");
////		}
//	   
//	}
//
//	public String getName() {
//		return name;
//	}
//
//
//	public double getBudget() {
//		return budget;
//	}
//
//
//	public ArrayList<ProductStock> getShoppingList() {
//		return shoppingList;
//	}
//	
//	@Override
//	public String toString() {
//		String ret = "Customer [name=" + name + ", budget=" + budget + ", shoppingList=\n";
//		for (ProductStock productStock : shoppingList) {
//			ret+= productStock.getProduct().getName() + " Quantity: " + productStock.getQuantity() + "\n";
//		}
//		return ret + "]";
//	}
//	
//	public static void main(String[] args) {
//		UserInput SLuser = new UserInput();
////		System.out.println(SLuser);
//	}
//
//}
