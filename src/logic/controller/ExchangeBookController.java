package logic.controller;

import java.util.List;

import javafx.scene.image.Image;
import logic.dao.BookDao;

/**
 * Controller del caso d'uso "Exchange Book"
 * @author Cristiano Cuffaro (M. 0258093)
 *
 */

public class ExchangeBookController {

	public List<Image> getExchangeableBooks() {	
		return BookDao.getInstance().getExchangeableBooks();
	}
}
