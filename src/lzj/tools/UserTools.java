package lzj.tools;

import javax.servlet.http.HttpServletRequest;

import lzj.DAO.UserDao;
import lzj.DaoImpl.UserDaoImpl;
import lzj.entity.User;

public class UserTools {
	public static void flashUser(HttpServletRequest request) {
		UserDao userDao = new UserDaoImpl();
		User user = (User) request.getSession().getAttribute("userObj");
		user = userDao.findUserById(user.getUserId());
		request.getSession().setAttribute("userObj", user);
	}
}
