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
import lzj.DAO.PlanDao;
import lzj.DAO.TempDao;
import lzj.DAO.UserDao;
import lzj.DaoImpl.BodySensorInfoDaoImpl;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.DeviceTypeDaoImpl;
import lzj.DaoImpl.GasSensorDaoImpl;
import lzj.DaoImpl.PlanDaoImpl;
import lzj.DaoImpl.TempDaoImpl;
import lzj.DaoImpl.UserDaoImpl;
import lzj.entity.BodySensorInfo;
import lzj.entity.Device;
import lzj.entity.DeviceType;
import lzj.entity.GasSensor;
import lzj.entity.Plan;
import lzj.entity.Temp;
import lzj.entity.User;
import lzj.entity.UserType;
import lzj.entity.Warning;
import lzj.tools.TempTools;
import lzj.tools.UserTools;
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
		// response.setContentType("text/html; charset=utf-8");
		String stat = request.getParameter("stat");
		System.out.println(stat);
		if (stat.equals("login")) {
			// 登录处理动作
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			User user = userDao.findUserByUserNameAndPassWord(username, password);
			if (user != null) {
				request.getSession().setAttribute("userObj", user);
				List<Warning> warningList = WarningInfoSearch.search(user);
				request.getSession().setAttribute("warningList", warningList);
				response.getWriter().write("OK");
			} else {
				response.getWriter().write("用户名或密码不正确");
			}
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
			}
		}
		if ((request.getSession().getAttribute("userObj")) == null) {
			response.getWriter().print("请登录");
			return;
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
		}
		if (stat.equals("deviceEdit")) {
			// 设备修改
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
		}
		if (stat.equals("warningCheck")) {
			// 确认警告
			int wid = Integer.valueOf(request.getParameter("wid"));
			int type = Integer.valueOf(request.getParameter("type"));
			GasSensorDao gasSensorDao = new GasSensorDaoImpl();
			BodySensorInfoDao bodySensorInfoDao = new BodySensorInfoDaoImpl();
			int tag = 0;
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
			if (tag > 0) {
				response.getWriter().print("确认成功");
				List<Warning> warningList = WarningInfoSearch
						.search((User) request.getSession().getAttribute("userObj"));
				request.getSession().setAttribute("warningList", warningList);
			} else {
				response.getWriter().print("确认失败");
			}
		}
		if (stat.equals("passwordChange")) {
			// 密码修改
			String password = request.getParameter("pwd");
			User user = (User) request.getSession().getAttribute("userObj");
			user.setUserPassWord(password);
			int tag = new UserDaoImpl().updateUser(user);
			if (tag > 0) {
				flash(request, response);
				response.getWriter().print("更改成功");
			} else {
				response.getWriter().print("更改失败");
			}
		}
		if (stat.equals("noLogin")) {
			// 注销登录
			request.getSession().setAttribute("userObj", null);
			response.sendRedirect("login.jsp");
		}
		if (stat.equals("planAdd")) {
			// 计划任务添加
			String planName = request.getParameter("planName");
			int hour = Integer.valueOf(request.getParameter("hour"));
			int min = Integer.valueOf(request.getParameter("min"));
			String deviceIdOrProfile = request.getParameter("deviceIdOrProfile");
			int onoff = Integer.valueOf(request.getParameter("onoff"));
			Plan plan = new Plan(0, planName, hour + ":" + min + ":" + "00", deviceIdOrProfile, onoff, 1);
			int tag = new PlanDaoImpl().addPlan(plan);
			if (tag > 0) {
				response.getWriter().print("添加成功");
			} else {
				response.getWriter().print("添加失败");
			}
		}
		if (stat.equals("planDel")) {
			// 计划任务删除
			int pid = Integer.valueOf(request.getParameter("pid"));
			int tag = new PlanDaoImpl().delPlan(new Plan(pid, null, null, null, 0, 0));
			if (tag > 0) {
				response.getWriter().print("删除成功");
			} else {
				response.getWriter().print("删除失败");
			}
		}
		if (stat.equals("planEdit")) {
			// 计划任务修改
			PlanDao planDao = new PlanDaoImpl();
			int pid = Integer.valueOf(request.getParameter("pid"));
			Plan p = planDao.findPlanByPid(pid);
			String planName = request.getParameter("planName");
			int hour = Integer.valueOf(request.getParameter("hour"));
			int min = Integer.valueOf(request.getParameter("min"));
			String deviceIdOrProfile = request.getParameter("deviceIdOrProfile");
			int pStat = Integer.valueOf(request.getParameter("pStat"));
			p.setpName(planName);
			p.setDeviceIdOrProfile(deviceIdOrProfile);
			p.setpStat(pStat);
			p.setpTime(hour + ":" + min + ":00");
			int tag = planDao.updatePlan(p);
			if (tag > 0) {
				response.getWriter().print("修改成功");
			} else {
				response.getWriter().print("修改成功");
			}
		}
		if (stat.equals("planOnOff")) {
			int tag = Integer.valueOf(request.getParameter("tag"));
			int pid = Integer.valueOf(request.getParameter("pid"));
			PlanDao planDao = new PlanDaoImpl();
			Plan p = planDao.findPlanByPid(pid);
			p.setpIsOpen(tag);
			int tag1 = planDao.updatePlan(p);
			if(tag1 > 0){
				response.getWriter().print("修改成功");
			}else{
				response.getWriter().print("修改失败");
			}
		}

		return;
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
