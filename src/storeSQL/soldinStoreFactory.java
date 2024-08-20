package ben_aharoni_amit_halaly;

public class soldinStoreFactory {

	public products createsoldinstore(String product_name, int cost_price, int selling_price, int stock,
			String serialnum, double weight) {

		return new soldinStore(product_name, cost_price, selling_price, stock, serialnum, weight);

	}
}
