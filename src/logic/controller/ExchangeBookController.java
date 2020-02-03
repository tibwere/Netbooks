package logic.controller;

import java.util.ArrayList;
import javafx.scene.image.Image;
import logic.dao.AbstractBookDao;

/**
 * Controller del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public ArrayList<Image> getExchangeableBooks() {
		
		ArrayList<Image> books = AbstractBookDao.getInstance().getExchangeableBooks();
		
		return books;
	}
}
