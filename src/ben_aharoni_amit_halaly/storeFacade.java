package ben_aharoni_amit_halaly;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class storeFacade {
	private final int DOLLAR_RATE = 4;
	private int Sid;
	private String storeName;
	private static storeFacade instance = null;

	private storeFacade(int sid, String StoreName) {
		setSid(sid);
		setStoreName(StoreName);

	}

	public static storeFacade getInstance() {
		if (instance == null) {
			instance = new storeFacade(1, "GAB");
		}
		return instance;
	}

	void addProduct(products product) {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO productsTable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
					+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
					+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		if (product instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "INSERT INTO soldthroughwebsiteproductstable (pid, productname, costprice,sellingprice, weight, stock,destcountry) VALUES ('"
						+ product.pid + "' ,'" + product.product_name + " '," + product.cost_price + " ,"
						+ product.selling_price + " ," + product.weight + "," + product.stock + ", '"
						+ ((soldThroughWebsite) product).getDest_country() + "');";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (product instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO soldinstoreproductstable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
							+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
							+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "INSERT INTO soldtowholesellersproductstable (pid, productname, costprice,sellingprice, weight, stock) VALUES ('"
							+ product.pid + "' ,'" + product.product_name + "' ," + product.cost_price + " ,"
							+ product.selling_price + " ," + product.weight + "," + product.stock + ");";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO storeproducttable (pid, sid)  VALUES ('" + product.pid + "' , " + this.Sid + ");";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public void deleteProduct(products foundObject) {
		Connection conn = null;
		if (foundObject instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "DELETE FROM soldthroughwebsiteproductstable WHERE pid='" + foundObject.pid + "'; ";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (foundObject instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "DELETE FROM soldinstoreproductstable WHERE pid='" + foundObject.pid + "';";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "DELETE FROM soldtowholesellersproductstable WHERE pid='" + foundObject.pid + "';";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}

		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM storeproducttable WHERE pid='" + foundObject.pid + "';";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "DELETE FROM productsTable WHERE pid='" + foundObject.pid + "';";
			stmt.executeQuery(sql);
			stmt.closeOnCompletion();
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
	}

	public void updateStock(products foundObject, int amount) {
		Connection conn = null;
		if (foundObject instanceof soldThroughWebsite) {
			try {
				Class.forName("org.postgresql.Driver");
				String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
				conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
				Statement stmt = conn.createStatement();
				String sql = "UPDATE productstable " + "SET stock= " + amount + " WHERE pid='" + foundObject.pid + "';"
						+ " UPDATE soldthroughwebsiteproductstable" + " SET stock= " + amount + " WHERE pid='"
						+ foundObject.pid + "'; ";
				stmt.executeQuery(sql);
				stmt.closeOnCompletion();
			} catch (Exception ex) {
				System.out.println("SQLException: " + ex.getMessage());
			}
		} else {
			if (foundObject instanceof soldinStore) {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "UPDATE productstable\r\n" + "SET stock= " + amount + "\r\n" + "WHERE pid='"
							+ foundObject.pid + "';\r\n " + "UPDATE soldinstoreproductstable\r\n" + "SET stock= "
							+ amount + "\r\n" + "WHERE pid='" + foundObject.pid + "'; ";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			} else {
				try {
					Class.forName("org.postgresql.Driver");
					String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
					conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
					Statement stmt = conn.createStatement();
					String sql = "UPDATE productstable\r\n" + "SET stock= " + amount + "\r\n" + "WHERE pid='"
							+ foundObject.pid + "';\r\n " + "UPDATE soldtowholesellersproductstable\r\n" + "SET stock= "
							+ amount + "\r\n" + "WHERE pid='" + foundObject.pid + "'; ";
					stmt.executeQuery(sql);
					stmt.closeOnCompletion();
				} catch (Exception ex) {
					System.out.println("SQLException: " + ex.getMessage());
				}
			}
		}
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getSid() {
		return Sid;
	}

	public void setSid(int sid) {
		Sid = sid;
	}

	public products findProducts(String pid) {
		Connection conn = null;
		int flag = 0;
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldinstoreproductstable WHERE pid = '" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				flag = 1;
			if (flag == 1) {
				stmt.closeOnCompletion();
				soldinStoreFactory sf = new soldinStoreFactory();
				return sf.createsoldinstore(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("pid"), rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldtowholesellersproductstable WHERE pid= '" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				flag = 1;
			if (flag == 1) {
				stmt.closeOnCompletion();
				SoldToWholesellersFactory sf = new SoldToWholesellersFactory();
				return sf.createSoldToWholesellers(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("pid"), rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		try {
			Class.forName("org.postgresql.Driver");
			String dbUrl = "jdbc:postgresql://localhost:5432/storeSQL";
			conn = DriverManager.getConnection(dbUrl, "postgres", "159632");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM soldthroughwebsiteproductstable WHERE pid = '" + pid + "';";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next())
				flag = 1;
			if (flag == 1) {
				stmt.closeOnCompletion();
				soldthroughwebsiteFactory sf = new soldthroughwebsiteFactory();
				return sf.createsoldthroughwebsite(rs.getString("productname"), rs.getInt("costprice"),
						rs.getInt("sellingprice"), rs.getInt("stock"), rs.getString("destcountry"), rs.getString("pid"),
						rs.getDouble("weight"));
			}
		} catch (Exception ex) {
			System.out.println("SQLException: " + ex.getMessage());
		}
		return null;
	}
}
