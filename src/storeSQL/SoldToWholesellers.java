package ben_aharoni_amit_halaly;

public class SoldToWholesellers extends products implements Comparable<products>, invoiceToAcountant {

	public SoldToWholesellers(String product_name, int cost_price, int selling_price, int stock, String serialnum,
			double weight) {
		super(product_name, cost_price, selling_price, stock, serialnum, weight);
	}

	@Override
	public void invoiceFormatToAcountant(products product, int quantity) {
		int totalCost = product.getCost_price() * quantity;
		int totalRevenue = product.getSelling_price() * quantity;
		int profit = totalRevenue - totalCost;
		System.out.println("INVOICE TO ACOUNTENT:\nProduct: " + product.getProduct_name() + "\namount Sold: " + quantity
				+ "\nprofit: " + totalRevenue + "₪\nTotal Cost: " + totalCost + "₪\nTotal Profit: " + profit + "₪");
	}

	@Override
	public int compareTo(products p) {
		return this.getPid().compareTo(p.getPid());
	}
}
