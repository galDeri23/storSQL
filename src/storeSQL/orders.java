package ben_aharoni_amit_halaly;

public class orders {
	private customer customer;
	private products product;
	private int quantity;
	private int oid;
	private eShipmentType type;

	public orders(customer customer, products product, int oid, int quantity, eShipmentType type) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.type = type;
		this.oid = oid;
	}

	public orders(customer customer, products product, int oid, int quantity, String serial) {
		this.customer = customer;
		this.product = product;
		this.quantity = quantity;
		this.oid = oid;
	}

	@Override
	public String toString() {
		return "customer: " + customer + "\nquantity: " + quantity + "\nshipment type: " + type + "\n";
	}

	public String toStringNoShipment() {
		return "customer: " + customer + "\nquantity: " + quantity + "\n";
	}

	public eShipmentType getType() {
		return type;
	}

	public customer getCustomer() {
		return customer;
	}

	public products getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public orders oclone() {
		try {
			return (orders) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}
}
