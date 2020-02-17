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
	public static final String GET_EXCHANGEABLE_BOOKS_SP = "call netbooks.get_exchangeable_books(?)";
	public static final String GET_OWNERS_SP = "call netbooks.get_owners(?)";
	public static final String FIND_OPEN_PROPOSALS_SP = "call netbooks.find_open_proposals(?, ?)";
	public static final String INSERT_NEW_PROPOSAL_SP = "call netbooks.insert_new_proposal(?, ?, ?)";
	public static final String GET_EMAIL_AND_GENRE_SP = "call netbooks.get_reader_details(?)";
	public static final String UPDATE_PROPOSAL_STATUS_SP = "call netbooks.update_proposal_status(?, ?)";
	public static final String INSERT_NEW_NOTIF_FOR_USER_SP = "call netbooks.insert_new_notif_for_user(?, ?, ?, ?, ?)";
	public static final String GET_USER_NOTIFICATIONS_SP = "call netbooks.get_user_notifications(?)";
	public static final String GET_BOOK_SP = "call netbooks.get_book(?)";
	public static final String GET_PROPOSAL_SP = "call netbooks.get_proposal(?)";
	public static final String CHECK_OWNERSHIP_SP = "call netbooks.check_ownership(?, ?)";
	public static final String SWAP_OWNERSHIP_SP = "call netbooks.swap_ownership(?, ?, ?, ?)";
	public static final String DELETE_NOTIF_FOR_USER_SP = "call netbooks.delete_notif_for_user(?, ?, ?)";
	public static final String FIND_UNREAD_NOTIFICATIONS_SP = "call netbooks.find_unread_notifications(?)";
	
	private Query() {
		/* non instanziabile */
	}
}
