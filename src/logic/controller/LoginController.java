package logic.controller;

import logic.bean.AbstractUserBean;
import logic.dao.AbstractUserDao;
import logic.util.enumeration.UserType;
import logic.util.enumeration.Views;

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
		
		switch(type) {
		case READER: 
			Session.getSession().setCurrView(Views.HOME);
			Session.getSession().setCurrUser(user);
			break;
		case RETAILER:
			Session.getSession().setCurrView(Views.KBSAS);
			Session.getSession().setCurrUser(user);
			break;
		
		default:
			break;
		}

		return type;
	}

}
