package ben_aharoni_amit_halaly;

public class addCommand implements Command {

	private storeFacade store;
	private products product;

	public addCommand(storeFacade store, products product) {
		this.store = store;
		this.product = product;
	}

	@Override
	public void execute() {
		store.addProduct(product);
	}
}
