package logic.util.enumeration;

/**
 * Enumerato che permette alla bean del libro di discriminare 
 * la taglia dell'immagine da restituire al richiedente.
 * Di seguito il mapping delle dimensioni:
 * <ul>
 * 		<li> SMALL:		40px x 60px 	</li>
 * 		<li> MEDIUM:	100px x 150px	</li>
 * 		<li> LARGE:		300px x 450px	</li>
 * </ul>
 * @author Simone Tiberi (M. 0252795)
 * 
 */
public enum ImageSize {
	SMALL, MEDIUM, LARGE
}
