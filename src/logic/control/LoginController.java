package logic.control;

import logic.bean.AbstractUserBean;
import logic.dao.AbstractUserDao;
import logic.enumeration.UserType;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserType loginUser(AbstractUserBean bean) {
		String user = bean.getUsername();
		String passwd = bean.getPassword();

		return AbstractUserDao.getInstance().findUserByUsernameAndPassword(user, passwd);	
	}

}
