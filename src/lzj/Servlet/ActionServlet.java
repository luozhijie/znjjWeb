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
		request.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String url = "";
		String stat = request.getParameter("stat");
		System.out.println(stat);
		if (stat.equals("login")) {
			// 登录处理动作
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDao.findUserByUserNameAndPassWord(username, password);

			if (user != null) {
				// url = "Index.jsp";
				request.getSession().setAttribute("userObj", user);
				List<Warning> warningList = WarningInfoSearch.search(user);
				request.getSession().setAttribute("warningList", warningList);
				List<Temp> tempList = TempTools.getTemp(user);
				request.getSession().setAttribute("tempList", tempList);
				response.getWriter().print("OK");
			} else {
				// url = "login.jsp";
				response.getWriter().println("用户名或密码不正确");
			}
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
			switch (isoff) {
			case 1:
				deviceDao.statChange(isoff + "", deviceId);
				break;

			default:
				deviceDao.statChange("0", deviceId);
				break;
			}
			// 刷新用户信息
			this.flash(request, response);
			url = "Index.jsp";
		}
		if (stat.equals("looktemp")) {
			int deviceId = Integer.valueOf(request.getParameter("deviceId"));
			TempDao tempDao = new TempDaoImpl();
			List<Temp> tempList = tempDao.findTempByDeviceIdAndLimit(deviceId, 10);
			request.setAttribute("tempList", tempList);
			url = "LookTmpDry.jsp";

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
		if (stat.equals("addDevice")) {
			// 添加设备
			DeviceTypeDao deviceTypeDao = new DeviceTypeDaoImpl();
			List<DeviceType> deviceTypeList = deviceTypeDao.findAllDevicetype();
			request.setAttribute("deviceTypeList", deviceTypeList);
			url = "AddDevice.jsp";
		}
		if (stat.equals("addDeviceAction")) {
			// 添加设备动作
			String deviceName = request.getParameter("deviceName");
			int deviceTypeId = Integer.valueOf(request.getParameter("deviceTypeId"));
			int gpio = Integer.valueOf(request.getParameter("gpio"));
			Device device = new Device();
			device.setDeviceName(deviceName);
			device.setDeviceType(new DeviceType(deviceTypeId, null));
			device.setDevice_gpio(gpio);
			device.setUserId(((User) request.getSession().getAttribute("userObj")).getUserId());
			DeviceDao deviceDao = new DeviceDaoImpl();
			deviceDao.addDevice(device);
			// response.sendRedirect("Index.jsp");
			this.flash(request, response);
			url = "Index.jsp";
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
