package lzj.tools;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lzj.DAO.DeviceDao;
import lzj.DAO.UserDao;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.FamilyGroupDaoImpl;
import lzj.DaoImpl.UserDaoImpl;
import lzj.entity.User;
import lzj.entity.Warning;

public class UserTools {
	public static void flashUser(HttpServletRequest request) {
		
		UserDao userDao = new UserDaoImpl();
		User user = (User) request.getSession().getAttribute("userObj");
		user = userDao.findUserById(user.getUserId());
		List<Integer> mainUserId = new FamilyGroupDaoImpl().findMainUserIdByUid(user.getUserId());
		DeviceDao deviceDao = new DeviceDaoImpl();
		for (Integer uid : mainUserId) {
			user.getDeviceList().addAll(deviceDao.findDeviceByUserId(uid));
		}
		List<Warning> warningList = WarningInfoSearch.search(user);
		request.getSession().setAttribute("warningList", warningList);
		request.getSession().setAttribute("userObj", user);
	}
}
