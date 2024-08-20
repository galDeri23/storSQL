package ben_aharoni_amit_halaly;

public class customer {

	private String customer_name;
	private String mobile;
	private int cid;

	public customer(String name, String mobile, int cid) {
		this.customer_name = name;
		this.mobile = mobile;
		this.cid = cid;
	}

	public String getName() {
		return customer_name;
	}

	public String getMobile() {
		return mobile;
	}

	@Override
	public String toString() {
		return customer_name + " \nmobile: " + mobile;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
}
