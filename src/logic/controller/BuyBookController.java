package logic.controller;

public class BuyBookController {
	
	private ViewBookByCategoryController vbbcController;
	
	public BuyBookController(ViewBookByCategoryController c) {
		this.vbbcController = c;		
	}

	public ViewBookByCategoryController getVbbcController() {
		return vbbcController;
	}
}
