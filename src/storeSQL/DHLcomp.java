package ben_aharoni_amit_halaly;

public class DHLcomp extends ShippingCompany {

	private static final double EXPRESS_PRICE = 100.0;
	private static final double EXPRESS_IMPORT_TAX = 20.0;
	private static final double STANDARD_PERCENT = 0.10;
	private static final double STANDARD_MAX_PRICE = 100.0;

	public DHLcomp(String name, String contact, double shippingFee, String whatsup, int scid) {
		super(name, contact, shippingFee, whatsup, scid);
	}

	public double calculateShippingFee(orders order) {
		double cost;
		if (order.getType().equals("EXPRESS")) {
			cost = EXPRESS_PRICE + EXPRESS_IMPORT_TAX;
		} else {
			double priceBasedFee = (order.getProduct().getSelling_price() * order.getQuantity()) * STANDARD_PERCENT;
			cost = Math.min(priceBasedFee, STANDARD_MAX_PRICE);
		}
		return cost;
	}
}
