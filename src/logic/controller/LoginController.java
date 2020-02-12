package logic.controller;

import java.sql.SQLException;

import logic.bean.UserBean;
import logic.dao.UserDao;
import logic.exception.NoUserFoundException;
import logic.util.Session;
import logic.util.enumeration.UserType;

/**
 * Controller del caso d'uso "Login"
 * @author Simone Tiberi (M. 0252795)
 *
 */
public class LoginController {

	public UserType loginUser(UserBean bean) throws SQLException, NoUserFoundException, ClassNotFoundException {
		String user = bean.getUsername();
		String passwd = bean.getPassword();
		
		UserType type = UserDao.findUserByUsernameAndPassword(user, passwd);
		Session.getSession().setCurrUser(user);

		return type;
	}

}