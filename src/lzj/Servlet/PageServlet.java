package lzj.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lzj.entity.Device;
import lzj.entity.User;

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
		String url = null;
		if (stat.equals("onoff")) {// 获取开关控制列表
			System.out.println("onoff");
			List<Device> deviceList = new ArrayList<>();
			User user = (User) request.getSession().getAttribute("userObj");
			for (Device device : user.getDeviceList()) {
				if (device.getDeviceType().getDeviceTypeId() == 2) {
					deviceList.add(device);
				}
			}
			request.setAttribute("deviceList", deviceList);
			url = "OnOffControl.jsp";
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
	}

}
