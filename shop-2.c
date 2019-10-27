#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Product {
	char* name;
	double price;
};

struct ProductStock {
	struct Product product;
	int quantity;
};

struct Shop {
	double cash;
	struct ProductStock stock[20];
	int index;
};

struct Customer {
	char* name;
	double budget;
	struct ProductStock shoppingList[10];
	int index;
};

void printProduct(struct Product p)
{
	printf("PRODUCT NAME: %s \nPRODUCT PRICE: %.2f\n", p.name, p.price);
	// printf("-------------\n");
}

// void printCustomerName(struct Product p)
// {
// 	printf("PRODUCT NAME: %s \nPRODUCT PRICE: %.2f\n", p.name, p.price);
// 	printf("-------------\n");
// }


double findProductPrice(struct Shop s, char *n)
{
	for (int i = 0; i < s.index; i++)
	{
    struct Product product = s.stock[i].product;
		// printProduct(s.stock[i].product);
		char *name = product.name;
    if (strcmp(name,n) == 0)
    {
      return product.price;
    }
	}
  return -1;
}

struct Customer createCustomerFromCsv(struct Shop s)
{
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;
    fp = fopen("customer-2.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

		getline(&line, &len, fp);
		char *n = strtok(line, ",");
		char *b = strtok(NULL, ",");

		char *topLineName = malloc(sizeof(char) * 100);
		double topLinebudget = atof(b);
		strcpy(topLineName, n);
		printf("\n First line name: %s \n First line budget: %.2f \n", topLineName, topLinebudget);
		struct Customer customer = { topLineName, topLinebudget };

    while ((read = getline(&line, &len, fp)) != -1) {
        char *i = strtok(line, ",");
        char *q = strtok(NULL, ",");
        char *item = malloc(sizeof(char) * 100);
        strcpy(item, i);
        int quantity = atoi(q);
				printf("\nItem: %s \n quantity: %d \n", item, quantity);

				double price = findProductPrice(s, item);
    		printf("prod price %.2f\n", price);
				printf("-------------\n");
				
				struct Product SLProduct = { item, price };
				struct ProductStock customerShoppingList = { SLProduct, quantity };

				customer.shoppingList[customer.index++] = customerShoppingList;
    }    

       
    return customer;
};

int customerInput()
{
	char* n;
  char variable;
  char *customerName = &variable;

	char variable2;
  char *customerBudget = &variable2;
  // double customerBudget;
	
	printf("Enter you name:\n");
	gets(customerName);
	printf("Hello %s\n", customerName);

	printf("Enter you budget:\n");
	gets(customerBudget);
	int budget = atoi(customerBudget);
	printf("Hello %d\n", budget);
	
	// if ((strcmp(customerName,"Alice")==0) || (strcmp(customerName,"Bob")==0))
	// {
	// 	printf("Hello %s\n", customerName);	
	// } else 
	// {
	// 	printf("Hello lowly peasant!\n");
	// }
	
	return 0;
}

void printCustomer(struct Customer c)
{
	printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
	printf("-------------\n");
	for(int i = 0; i < c.index; i++)
	{
		printProduct(c.shoppingList[i].product);
		printf("quantity %d \n", c.shoppingList[i].quantity);
	}
}


struct Shop createAndStockShop()
{
	  // struct Shop shop = { 200 };
    FILE * fp;
    char * line = NULL;
    size_t len = 0;
    ssize_t read;

    fp = fopen("stock-2.csv", "r");
    if (fp == NULL)
        exit(EXIT_FAILURE);

    getline(&line, &len, fp);
		double cashInShop = atof(line); // string to float
		struct Shop shop = { cashInShop };

    while ((read = getline(&line, &len, fp)) != -1) {
        // printf("Retrieved line of length %zu:\n", read);
        // printf("%s IS A LINE", line);
        char *n = strtok(line, ",");
        char *p = strtok(NULL, ",");
        char *q = strtok(NULL, ",");
        int quantity = atoi(q);
        double price = atof(p);
        char *name = malloc(sizeof(char) * 50);
        strcpy(name, n);
        // printf("NAME OF PRODUCT %s PRICE %.2f QUANTITY %d\n", name, price, quantity);

        struct Product product = { name, price };
        struct ProductStock stockItem = { product, quantity };
        shop.stock[shop.index++] = stockItem;
    }
	
	return shop;
}

void printShop(struct Shop s)
{
	printf("Shop has %.2f in cash\n", s.cash);
	for (int i = 0; i < s.index; i++)
	{
		printProduct(s.stock[i].product);
		printf("The shop has %d of the above\n", s.stock[i].quantity);
		printf("-------------\n");
	}
}


double updateShop(struct Shop s, struct Customer c)
{
	printf("Shop has %.2f in cash\n", s.cash);

	// struct Shop {
	// 	double cash;
	// 	struct ProductStock stock[20];
	// 	int index;


	printf("CUSTOMER NAME: %s \nCUSTOMER BUDGET: %.2f\n", c.name, c.budget);
	printf("-------------\n");
	
	for (int i = 0; i < s.index; i++)
	{
    struct Product product = s.stock[i].product;
		// printProduct(s.stock[i].product);
		char *shopProductname = product.name;

		struct ProductStock shopProductStock = s.stock[i];
		int shopProductQuantity = shopProductStock.quantity;

		for (int j = 0; j < c.index; j++)
		{
			struct Product SLProduct = c.shoppingList[j].product;
			char *SLProductName = SLProduct.name;

			// printf("%s's budget %.2f \n", c.name, c.budget);

			struct ProductStock SLProductStock = c.shoppingList[j];
			int SLProductQuantity = SLProductStock.quantity;
			// printf("shopProductname %s \n SLProductName %s \n", shopProductname,SLProductName);

			if (strcmp(shopProductname,SLProductName) == 0)
			{
				printf("%s price is €%.2f \n", shopProductname, product.price);
				printf("%s quantity on shopping list %d \n", shopProductname, SLProductQuantity);
				int totalPerProduct = product.price * SLProductQuantity;
				// s.cash += product.price * SLProductQuantity;
				printf("%s total €%d \n", shopProductname, totalPerProduct);
				
	
				if (shopProductQuantity == 0) 
				{
					printf("%s is out of stock.\n", SLProductName);
				}
				else 
					{
					if (totalPerProduct > c.budget)
					{
						printf("Total price of %d€ for %s exceeds %s's budget.\n", totalPerProduct, SLProductName, c.name);
					}
					else 
					{
						if (SLProductQuantity > shopProductQuantity)
						{
							printf("Total items (%d) on shopping list of %s exceeds stock of %d.\n", SLProductQuantity, SLProductName, shopProductQuantity);
							totalPerProduct = product.price * shopProductQuantity;
							printf("Total of remaining %d %s is €%d.\n", shopProductQuantity, SLProductName, totalPerProduct);
							s.cash += totalPerProduct;
							printf("Shop cash updated €%.2f \n", s.cash);
							c.budget -= totalPerProduct;
							shopProductQuantity -= shopProductQuantity;
							printf("%s stock quantity updated to %d \n", shopProductname, shopProductQuantity);
							printf("%s's budget balance is €%.2f \n", c.name, c.budget);

							// struct Product shopProductUpdate = { product.name, product.price };
							// struct ProductStock stockItem = { shopProductUpdate, shopProductQuantity };
							// s.stock[s.index++] = stockItem;
						}
						else
						{
							s.cash += totalPerProduct;
							printf("Shop cash updated €%.2f \n", s.cash);
							c.budget -= totalPerProduct;
							shopProductQuantity -= SLProductQuantity;
							printf("%s stock quantity updated %d \n", shopProductname, shopProductQuantity);
							printf("%s's budget balance is €%.2f \n", c.name, c.budget);

							// struct Product shopProductUpdate = { product.name, product.price };
							// struct ProductStock stockItem = { shopProductUpdate, shopProductQuantity };
							// s.stock[s.index++] = stockItem;
						}
					}
				}
				printf("-------------\n");
			}
		}
		printf("-------------\n");

	}
  return -1;
}




int main(void) 
{
	
    struct Shop shop = createAndStockShop();
    printShop(shop);

    customerInput();

    struct Customer customer = createCustomerFromCsv(shop);
		printCustomer(customer);


		updateShop(shop, customer);

	
    return 0;
}
