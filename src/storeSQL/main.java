package ben_aharoni_amit_halaly;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Iterator;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class main {

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner input = new Scanner(System.in);
		storeFacade s = storeFacade.getInstance();
		String choose = "0";
		do {
			System.out.println("press:\n " + "1) to add a new product  \n" + " 2) to remove a product \n "
					+ "3) to update a product's amount \n" + " 4) to add an order for a product \n"
					+ " 5) to review a prodact's details \n" + " 6) to review all of the products \n"
					+ " 7) to print a product's orders \n" + " e\\E) to exit  \n ");
			choose = input.nextLine();
			switch (choose) {
			case "1":
				addNewProduct(s);

				break;

			case "2":
				removeproduct(s);

				break;

			case "3":
				updateProductAmount(s);

				break;

			case "4":
				addOrderToproduct(s);

				break;
			case "5":
				printAllDetailsForSingleProduct(s);

				break;

			case "6":
				printAllProducts(s);

				break;

			case "7":
				printSingleProduct(s);

				break;
			default:
				if (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E")) {
					System.out.println("invalid choice try again");
				}
			}
		} while (!choose.equalsIgnoreCase("e") && !choose.equalsIgnoreCase("E"));
		System.out.println("bye");
	}

	public static void addNewProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		int choose = 0;
		String product_name;
		String serialnum;
		int cost_price;
		int selling_price;
		int stock;
		double weight;
		do {
			System.out.println("choose a product type\n");
			System.out.println("press:\n 1) for sold in Store  \n " + "2) for sold through website \n"
					+ " 3) for sold to wholesellers\n ");
			choose = input.nextInt();
			if (choose < 1 || choose > 3) {
				System.out.println("invalid option");
				return;
			}
		} while (choose == 0);
		System.out.println("please enter product name : ");
		product_name = input.next();
		System.out.println("please enter PID : ");
		serialnum = input.next();
		System.out.println("please enter product cost price : ");
		cost_price = input.nextInt();
		System.out.println("please enter product selling price : ");
		selling_price = input.nextInt();
		System.out.println("please enter product weight : ");
		weight = input.nextDouble();
		System.out.println("please enter the amount of products : ");
		stock = input.nextInt();
		switch (choose) {
		case 1:
			soldinStoreFactory sisfactory = new soldinStoreFactory();
			s.addProduct(
					sisfactory.createsoldinstore(product_name, cost_price, selling_price, stock, serialnum, weight));
			break;

		case 2:
			System.out.println("please enter the dest country : ");
			String dest_country = input.next();
			soldthroughwebsiteFactory stwfactory = new soldthroughwebsiteFactory();
			s.addProduct(stwfactory.createsoldthroughwebsite(product_name, cost_price, selling_price, stock,
					dest_country, serialnum, weight));
			break;

		case 3:
			SoldToWholesellersFactory stfactory = new SoldToWholesellersFactory();
			s.addProduct(stfactory.createSoldToWholesellers(product_name, cost_price, selling_price, stock, serialnum,
					weight));
			break;

		}
	}

	public static void removeproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		while (foundObject == null) {
			System.out.println("\nEnter the PIDof the product you want to change :\n");
			String serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		s.deleteProduct(foundObject);
	}

	public static void updateProductAmount(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		while (foundObject == null) {
			System.out.println("\nEnter the PID of the product you want to change :\n");
			String serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		System.out.println("\nEnter the new amount :\n");
		s.updateStock(foundObject, input.nextInt());
	}

	public static void addOrderToproduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		int amount, i = 1, cid, oid, choose = 0;
		String serial, name, mobile;
		while (foundObject == null) {
			System.out.println("\nEnter the PID number of the product you want to make an order:\n");
			serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		System.out.println("please enter the customer's name:");
		name = input.next();
		System.out.println("please enter the customer's id");
		cid = input.nextInt();
		System.out.println("please enter the mobile:");
		mobile = input.next();
		System.out.println("please enter the order id number:");
		oid = input.nextInt();
		System.out.println("please enter the amount:");
		amount = input.nextInt();
		while (choose <= 0 || choose > 3) {
			System.out.println("press: ");
			for (eShipmentType type : eShipmentType.values()) {
				System.out.println("(" + (i++) + ") for " + type);
			}
			if (i == 3)
				System.out.println("(" + (i++) + ") for without shipment");
			choose = input.nextInt();
		}

		switch (choose) {
		case 1:
			foundObject.addOrder(new customer(name, mobile, cid), oid, amount, eShipmentType.EXPRESS);
			System.out.println("order has been created");
			break;

		case 2:
			foundObject.addOrder(new customer(name, mobile, cid), oid, amount, eShipmentType.STANDARD);
			System.out.println("order has been created");
			break;
		case 3:
			foundObject.addOrderWithoutShipment(new customer(name, mobile, cid), oid, amount);
			break;
		default:
			System.out.println("invalid try again");
			break;
		}
		input.nextLine();
	}

	public static void printAllDetailsForSingleProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		Connection conn = null;
		System.out.println("\nEnter the PID code of the product you want to view :\n");
		String serial = input.next();

		try {
			// Load PostgreSQL JDBC Driver
			Class.forName("org.postgresql.Driver");

			// Connect to the database
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();

			// Fetch the product details
			String sql = "SELECT * FROM productstable WHERE pid = '" + serial + "';";
			ResultSet rs = stmt.executeQuery(sql);
			String productType = "";

			if (rs.next()) {
				String PID = rs.getString("pid");
				String product_name = rs.getString("productname");
				int cost_price = rs.getInt("costprice");
				int selling_price = rs.getInt("sellingprice");
				double weight = rs.getFloat("weight");
				int stock = rs.getInt("stock");

				// Determine the product type
				String typeQuery = "SELECT 'Website' AS type FROM SoldThroughWebsiteProductsTable WHERE pid = '"
						+ serial + "' " + "UNION "
						+ "SELECT 'Store' AS type FROM SoldInStoreProductsTable WHERE pid = '" + serial + "' "
						+ "UNION " + "SELECT 'Wholeseller' AS type FROM SoldToWholesellersProductsTable WHERE pid = '"
						+ serial + "'";
				ResultSet typeResult = stmt.executeQuery(typeQuery);

				if (typeResult.next()) {
					productType = typeResult.getString("type");
				}

				// Fetch related orders and customer information
				String ordersQuery = "SELECT ot.OID, ot.quantity, ot.shipmentType, ct.CustomerName, ct.Mobile "
						+ "FROM OrdersTable ot " + "JOIN OrdersProductTable opt ON ot.OID = opt.OID "
						+ "JOIN OrderCustomerTable oct ON ot.OID = oct.OID "
						+ "JOIN CustomerTable ct ON oct.CID = ct.CID " + "WHERE opt.PID = '" + serial + "'";

				ResultSet ordersResult = stmt.executeQuery(ordersQuery);
				int i = 1, totalProfit = 0;
				while (ordersResult.next()) {
					int quantity = ordersResult.getInt("quantity");
					String shipmentType = ordersResult.getString("shipmentType");
					String customerName = ordersResult.getString("CustomerName");
					String mobile = ordersResult.getString("Mobile");

					double profit = (selling_price - cost_price) * quantity;

					if (productType.equals("Website")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: $" + profit);
					} else if (productType.equals("Store")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: ₪" + profit * 4);

						// Invoice formatting for store sales
						System.out.println("Store invoice formatted for accountant and customer.");
					} else if (productType.equals("Wholeseller")) {
						System.out.println("\n" + (i++) + ") Order ID: " + ordersResult.getInt("OID") + " | Quantity: "
								+ quantity + " | Shipment Type: " + shipmentType + " | Customer: " + customerName + " ("
								+ mobile + ")" + " | Profit: $" + profit);

						// Invoice formatting for wholeseller sales
						System.out.println("Wholeseller invoice formatted for accountant.");
					}

					totalProfit += profit;
				}

				System.out.println(
						"\nTotal Profit for Product '" + product_name + "' (ID: " + serial + "): $" + totalProfit);
			} else {
				System.out.println("Product not found with the provided serial code.");
			}

			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void printAllProducts(storeFacade s) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM productstable;";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				System.out.println("PID: " + rs.getString("pid") + "\nproduct name: " + rs.getString("productname")
						+ "\ncost price: " + rs.getInt("costprice") + "\nselling price: " + rs.getInt("sellingprice")
						+ "\nwighet: " + rs.getFloat("weight") + "\nstock: " + rs.getInt("stock") + "\n\n");
			}
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public static void printSingleProduct(storeFacade s) {
		Scanner input = new Scanner(System.in);
		products foundObject = null;
		while (foundObject == null) {
			System.out.println("\nEnter the PID code of the product you want to view :\n");
			String serial = input.next();
			foundObject = s.findProducts(serial);
			if (foundObject == null)
				System.out.println("Wrong Serial number");
		}
		System.out.println(foundObject);
	}
}
