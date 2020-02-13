package logic.controller;

import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.exception.NoUserFoundException;
import logic.exception.PersistencyException;
import logic.util.Session;
import logic.util.enumeration.UserTypes;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserTypes loginUser(UserBean bean) throws NoUserFoundException, PersistencyException {
		String user = bean.getUsername();
		String passwd = bean.getPassword();
		
		UserTypes type = UserDao.findUserByUsernameAndPassword(user, passwd);
		Session.getSession().setCurrUser(user);

		return type;
	}

}