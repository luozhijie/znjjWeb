package lzj.Servlet;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lzj.DAO.BodySensorInfoDao;
import lzj.DAO.DeviceDao;
import lzj.DAO.DeviceTypeDao;
import lzj.DAO.GasSensorDao;
import lzj.DAO.TempDao;
import lzj.DAO.UserDao;
import lzj.DaoImpl.BodySensorInfoDaoImpl;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.DeviceTypeDaoImpl;
import lzj.DaoImpl.GasSensorDaoImpl;
import lzj.DaoImpl.TempDaoImpl;
import lzj.DaoImpl.UserDaoImpl;
import lzj.entity.BodySensorInfo;
import lzj.entity.Device;
import lzj.entity.DeviceType;
import lzj.entity.GasSensor;
import lzj.entity.Temp;
import lzj.entity.User;
import lzj.entity.UserType;
import lzj.entity.Warning;
import lzj.tools.TempTools;
import lzj.tools.WarningInfoSearch;

/**
 * Servlet implementation class ActionServlet
 * 
 * @注册
 * @登录
 * @添加设备
 * @刷新用户信息
 * @控制开关
 */
@WebServlet("/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao = new UserDaoImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "";
		String stat = request.getParameter("stat");
		System.out.println(stat);
		if (stat.equals("login")) {
			// 登录处理动作
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDao.findUserByUserNameAndPassWord(username, password);
			response.getWriter().flush();
			if (user != null) {
				// url = "Index.jsp";
				request.getSession().setAttribute("userObj", user);
				List<Warning> warningList = WarningInfoSearch.search(user);
				request.getSession().setAttribute("warningList", warningList);
				response.getWriter().write("OK");
			} else {
				// url = "login.jsp";
				response.getWriter().write("用户名或密码不正确");
			}
			response.getWriter().close();
			return;
		}
		if (stat.equals("flash")) {
			// 刷新用户信息
			this.flash(request, response);
			url = "Index.jsp";
		}
		if (stat.equals("onoff")) {
			// 开关控制
			int isoff = Integer.valueOf(request.getParameter("isoff"));
			int deviceId = Integer.valueOf(request.getParameter("deviceId"));
			System.out.println(isoff + "," + deviceId);
			DeviceDao deviceDao = new DeviceDaoImpl();
			int isok = 0;
			switch (isoff) {
			case 1:
				isok = deviceDao.statChange(isoff + "", deviceId);
				break;

			default:
				isok = deviceDao.statChange("0", deviceId);
				break;
			}
			// 刷新用户信息
			this.flash(request, response);
			response.getWriter().println(isok);
			return;
		}
		if (stat.equals("register")) {
			// 注册处理动作
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDao.findUserByUserName(username);
			if (user == null) {
				user = new User();
				user.setUserName(username);
				user.setUserPassWord(password);
				user.setUserType(new UserType(2, "用户"));
				userDao.addUser(user);
				request.setAttribute("userObj", user);
				if (user != null) {
					// response.sendRedirect("Index.jsp");
					url = "Index.jsp";
				}
			} else {
				response.getWriter().println("用户名已注册");
				return;
			}
		}
		if (stat.equals("deviceAdd")) {
			// 添加设备
			User user = (User) request.getSession().getAttribute("userObj");
			String deviceName = request.getParameter("deviceName");
			int deviceType = Integer.valueOf(request.getParameter("deviceType"));
			int GPIO = Integer.valueOf(request.getParameter("gpio"));
			DeviceDao deviceDao = new DeviceDaoImpl();
			int i = deviceDao.addDevice(
					new Device(0, user.getUserId(), deviceName, null, new DeviceType(deviceType, null), null, GPIO));
			if (i > 0) {
				response.getWriter().print("添加成功");
				flash(request, response);
			} else {
				response.getWriter().print("添加失败");
			}
			return;
		}
		if (stat.equals("deviceDel")) {
			// 删除设备
			User user = (User) request.getSession().getAttribute("userObj");
			int did = Integer.valueOf(request.getParameter("did"));
			List<Device> deviceList = user.getDeviceList();
			int tag1 = 0;
			for (Device device : deviceList) {
				if (device.getDeviceId() == did) {
					tag1++;
					break;
				}
			}
			if (tag1 == 0) {
				response.getWriter().print("非法操作");
				return;
			}

			DeviceDao deviceDao = new DeviceDaoImpl();
			int tag2 = deviceDao.delDeviceById(did);
			if (tag2 > 0) {
				response.getWriter().print("删除成功");
				flash(request, response);
			} else {
				response.getWriter().print("删除失败，请稍后再试");
			}
			return;
		}
		if (stat.equals("deviceEdit")) {
			User user = (User) request.getSession().getAttribute("userObj");
			String deviceName = request.getParameter("deviceName");
			System.out.println(deviceName);
			int deviceType = Integer.valueOf(request.getParameter("deviceType"));
			int GPIO = Integer.valueOf(request.getParameter("gpio"));
			int did = Integer.valueOf(request.getParameter("did"));
			DeviceDao deviceDao = new DeviceDaoImpl();
			Device device = deviceDao.findDeviceBydid(did);
			if (device == null || user == null) {
				response.getWriter().print("非法操作");
				return;
			}
			device.setDeviceName(deviceName);
			device.setDeviceType(new DeviceType(deviceType, null));
			device.setDevice_gpio(GPIO);
			System.out.println(device.toString());
			int i = deviceDao.updateDevice(device);
			if (i > 0) {
				response.getWriter().print("更改成功");
				flash(request, response);
			} else {
				response.getWriter().print("更改失败");
			}
			return;
		}
		if (stat.equals("warningCheck")) {
			int wid = Integer.valueOf(request.getParameter("wid"));
			int type = Integer.valueOf(request.getParameter("type"));
			GasSensorDao gasSensorDao = new GasSensorDaoImpl();
			BodySensorInfoDao bodySensorInfoDao = new BodySensorInfoDaoImpl();
			int tag = 0 ;
			switch (type) {
			case 4:
				tag = gasSensorDao.updateGasSensorIsCheckByGid(wid);
				break;
			case 6:
				tag = bodySensorInfoDao.updateBodySensorInfoCheckBybid(wid);
				break;

			default:
				break;
			}
			if(tag>0){
				response.getWriter().print("确认成功");
			}else{
				response.getWriter().print("确认失败");
			}
		}

		if (stat.equals("gas")) {
			// 可燃性传感器 页面
			int deviceId = Integer.valueOf(request.getParameter("deviceId"));
			GasSensorDao gasSensorDao = new GasSensorDaoImpl();
			List<GasSensor> gasSensorList = gasSensorDao.findGasSensorByDeviceId(deviceId);
			request.setAttribute("gasSensorList", gasSensorList);
			request.getSession().setAttribute("gasDeviceId", deviceId);
			url = "GasSensorInfo.jsp";
		}
		if (stat.equals("gasCheck")) {
			// 可燃性传感器确认
			int gid = Integer.valueOf(request.getParameter("gid"));
			GasSensorDao gasSensorDao = new GasSensorDaoImpl();
			gasSensorDao.updateGasSensorIsCheckByGid(gid);
			int deviceId = (Integer) request.getSession().getAttribute("gasDeviceId");
			List<GasSensor> gasSensorList = gasSensorDao.findGasSensorByDeviceId(deviceId);
			request.setAttribute("gasSensorList", gasSensorList);
			url = "GasSensorInfo.jsp";
		}

		if (stat.equals("bodySensorOnOff")) {
			// 人体传感器开关设置
			String isoff = request.getParameter("isoff");
			int deviceId = Integer.valueOf(request.getParameter("deviceId"));
			DeviceDao deviceDao = new DeviceDaoImpl();
			deviceDao.statChange(isoff, deviceId);
			this.flash(request, response);
			url = "Index.jsp";
		}

		if (stat.equals("bodySensorInfo")) {
			// 人体传感器页面
			int deviceId = Integer.valueOf(request.getParameter("deviceId"));
			BodySensorInfoDao bodySensorInfoDao = new BodySensorInfoDaoImpl();
			List<BodySensorInfo> bodySensorInfoList = null;
			bodySensorInfoList = bodySensorInfoDao.findAllBodySensorInfoByDeviceId(deviceId);
			request.setAttribute("bodySensorInfoList", bodySensorInfoList);
			request.getSession().setAttribute("bodySensorDeviceId", deviceId);
			url = "BodySensorInfo.jsp";
		}

		if (stat.equals("bodySensorCheck")) {
			// 人体传感器确认
			int deviceId = (int) request.getSession().getAttribute("bodySensorDeviceId");
			int bid = Integer.valueOf(request.getParameter("bid"));
			BodySensorInfoDao bodySensorInfoDao = new BodySensorInfoDaoImpl();
			bodySensorInfoDao.updateBodySensorInfoCheckBybid(bid);
			List<BodySensorInfo> bodySensorInfoList = null;
			bodySensorInfoList = bodySensorInfoDao.findAllBodySensorInfoByDeviceId(deviceId);
			request.setAttribute("bodySensorInfoList", bodySensorInfoList);
			url = "BodySensorInfo.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		String url = "";
		String stat = request.getParameter("stat");

		// request.getRequestDispatcher(url).forward(request, response);
	}

	@SuppressWarnings("unused")
	private void flash(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("userObj");
		user = userDao.findUserById(user.getUserId());
		request.getSession().setAttribute("userObj", user);
	}
}
