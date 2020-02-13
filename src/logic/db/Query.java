package logic.db;

/**
 * Classe che rappresenta un insieme di possibili query che è possibile
 * chiamare sulla base di dati tramite la classe {@link DBOperation}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Query {
	
	public static final String LOGIN_SP = "call netbooks.login(?, ?)";
	public static final String GET_BOOKS_FOR_HP_SP = "call netbooks.get_boook_for_hp(?)";
	public static final String EVAL_BOOK_SP = "call netbooks.eval_book(?, ?, ?, ?, ?)";
	public static final String GET_EVALUATION_SP ="call netbooks.get_evaluation(?, ?)";
	public static final String GET_BOOK_AVG_STARS_SP = "call netbooks.get_book_avg_stars(?)";
	public static final String GET_REVIEWS_SP = "call netbooks.get_reviews(?)";
	
	private Query() {
		/* non instanziabile */
	}
}
