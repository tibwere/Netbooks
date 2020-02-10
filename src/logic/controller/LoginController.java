package logic.controller;

import logic.bean.AbstractUserBean;
import logic.dao.AbstractUserDao;
import logic.util.Session;
import logic.util.enumeration.UserType;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserType loginUser(AbstractUserBean bean) {
		String user = bean.getUsername();
		String passwd = bean.getPassword();
		
		UserType type = AbstractUserDao.getInstance().findUserByUsernameAndPassword(user, passwd);
		
		if (!type.equals(UserType.INVALID_USER))
			Session.getSession().setCurrUser(user);

		return type;
	}

}