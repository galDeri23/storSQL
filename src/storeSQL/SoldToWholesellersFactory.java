package ben_aharoni_amit_halaly;

public class SoldToWholesellersFactory {

	public products createSoldToWholesellers(String product_name, int cost_price, int selling_price, int stock,
			String serialnum, double weight) {

		return new SoldToWholesellers(product_name, cost_price, selling_price, stock, serialnum, weight);

	}
}
