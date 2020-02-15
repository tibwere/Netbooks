package logic.db;

/**
 * Classe che rappresenta un enumerato di possibili query che � possibile
 * chiamare sulla base di dati tramite la classe {@link DBOperation}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Query {
	
	public static final String FIND_READER_SP = "call netbooks.find_reader(?, ?)";
	public static final String FIND_RETAILER_SP = "call netbooks.find_retailer(?, ?)";
	public static final String GET_BOOKS_FOR_HP_SP = "call netbooks.get_not_owned_books(?)";
	public static final String EVAL_BOOK_SP = "call netbooks.eval_book(?, ?, ?, ?, ?)";
	public static final String GET_EVALUATION_SP ="call netbooks.get_evaluation(?, ?)";
	public static final String GET_BOOK_AVG_STARS_SP = "call netbooks.get_book_avg_stars(?)";
	public static final String GET_REVIEWS_SP = "call netbooks.get_reviews(?)";
	public static final String GET_READER_SP = "call netbooks.get_reader(?)";
	public static final String INSERT_NEW_BOOK_TO_OWNEDLIST = "call netbooks.insert_new_book_to_ownedlist(?, ?)";
	public static final String GET_SEARCHED_BOOK_SP = "call netbooks.get_searched_book(?)";
	public static final String GET_ALL_BOOKS_SP = "call netbooks.get_all_books()";
	public static final String INSERT_NEW_READER = "call netbooks.insert_new_reader(?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_NEW_RETAILER = "call netbooks.insert_new_retailer(?, ?, ?, ?, ?, ?, ?)";
	public static final String CHECK_IF_OWNED = "call netbooks.check_if_owned(?, ?)";
	
	private Query() {
		/* non instanziabile */
	}
}
