package ben_aharoni_amit_halaly;

public class soldthroughwebsiteFactory {

	public products createsoldthroughwebsite(String product_name, int cost_price, int selling_price, int stock,
			String dest_country, String serialnum, double weight) {

		return new soldThroughWebsite(product_name, cost_price, selling_price, stock, dest_country, serialnum, weight);
	}
}
