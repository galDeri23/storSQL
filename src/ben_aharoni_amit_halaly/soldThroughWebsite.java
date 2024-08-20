package ben_aharoni_amit_halaly;

public class soldThroughWebsite extends products implements Comparable<products> {
	private String dest_country;

	public soldThroughWebsite(String product_name, int cost_price, int selling_price, int stock, String dest_country,
			String serialnum, double weight) {
		super(product_name, cost_price, selling_price, stock, serialnum, weight);
		this.setDest_country(dest_country);
	}

	public String getDest_country() {
		return dest_country;
	}

	public void setDest_country(String dest_country) {
		this.dest_country = dest_country;
	}

	@Override
	public int compareTo(products p) {
		return this.getPid().compareTo(p.getPid());
	}

	@Override
	public String toString() {

		return super.toString() + "\ndest country: " + dest_country;
	}
}
