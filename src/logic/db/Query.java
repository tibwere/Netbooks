package logic.db;

/**
 * Classe che rappresenta un insieme di possibili query che è possibile
 * chiamare sulla base di dati tramite la classe {@link DBOperation}
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class Query {
	
	private Query() {
		/* non instanziabile */
	}
	
	public static final String LOGIN_SP = "call netbooks.login(?, ?)";

}
