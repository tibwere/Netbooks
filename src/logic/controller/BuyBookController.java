package logic.controller;

/**
 * Controller del caso d'uso "Buy book"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class BuyBookController {
	
	private ViewBookByCategoryController vbbcController;
	
	public BuyBookController(ViewBookByCategoryController c) {
		this.vbbcController = c;		
	}

	public ViewBookByCategoryController getVbbcController() {
		return vbbcController;
	}
}
