package ben_aharoni_amit_halaly;

public class FDEXcomp extends ShippingCompany {

	private static final double EXPRESS_PER_10KG = 50.0;
	private static final double EXPRESS_IMPORT_TAX = 20.0;
	private static final double STANDARD_PER_10KG = 10.0;

	public FDEXcomp(String name, String contact, double shippingFee, String whatsup, int scid) {
		super(name, contact, shippingFee, whatsup, scid);

	}

	public double calculateShippingFee(orders order) {
		double weight = order.getProduct().getWeight();
		double quantity = order.getQuantity();
		double cost;
		if (order.getType().equals("EXPRESS")) {
			cost = Math.ceil((weight * quantity) / 10.0) * EXPRESS_PER_10KG + EXPRESS_IMPORT_TAX;
		} else {
			cost = Math.ceil((weight * quantity) / 10.0) * STANDARD_PER_10KG;
		}
		return cost;
	}

}
