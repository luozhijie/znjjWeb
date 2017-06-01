package lzj.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lzj.DAO.DeviceDao;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.DeviceTypeDaoImpl;
import lzj.entity.Device;
import lzj.entity.DeviceType;
import lzj.entity.User;
import lzj.entity.Warning;
import lzj.tools.UserTools;
import lzj.tools.WarningInfoSearch;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PageServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stat = request.getParameter("stat");
		User user = (User) request.getSession().getAttribute("userObj");
		String url = null;
		if (stat.equals("onoffControl")) {// 开关控制界面
			UserTools.flashUser(request);
			url = "onOffControl.jsp";
		}
		if (stat.equals("deviceAdd")) {// 设备添加界面
			List<DeviceType> deviceTypeList = new DeviceTypeDaoImpl().findAllDevicetype();
			request.setAttribute("deviceTypeList", deviceTypeList);
			DeviceDao deviceDao = new DeviceDaoImpl();
			List<Integer> gpioLessList = deviceDao.gpioLess(user.getUserId());
			request.setAttribute("gpioLessList", gpioLessList);
			url = "deviceAdd.jsp";
		}
		if (stat.equals("deviceDel")) {
			// 删除设备
			request.setAttribute("deviceList", user.getDeviceList());
			url = "deviceDel.jsp";
		}
		if (stat.equals("deviceEdit")) {
			// 修改设备
			request.setAttribute("deviceList", user.getDeviceList());
			List<DeviceType> deviceTypeList = new DeviceTypeDaoImpl().findAllDevicetype();
			request.setAttribute("deviceTypeList", deviceTypeList);
			DeviceDao deviceDao = new DeviceDaoImpl();
			List<Integer> gpioLessList = deviceDao.gpioLess(user.getUserId());
			request.setAttribute("gpioLessList", gpioLessList);
			url = "deviceEdit.jsp";
		}
		if (stat.equals("warningCheck")) {
			url = "warningCheck.jsp";
		}
		
		UserTools.flashUser(request);
		request.getRequestDispatcher(url).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
