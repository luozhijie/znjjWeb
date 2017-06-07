package lzj.Servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lzj.DAO.TempDao;
import lzj.DaoImpl.TempDaoImpl;
import lzj.entity.Device;
import lzj.entity.Temp;
import lzj.entity.User;
import lzj.tools.ProfileTools;

/**
 * Servlet implementation class GetListServlet
 */
@WebServlet("/GetInfoServlet")
public class GetInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetInfoServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String stat = request.getParameter("stat");
		if (stat.equals("temp")) {
			StringBuffer sb = new StringBuffer();
			User user = (User) request.getSession().getAttribute("userObj");
			TempDao tempDao = new TempDaoImpl();
			List<Device> deviceList = user.getDeviceList();
			for (Device device : deviceList) {
				if (device.getDeviceType().getDeviceTypeId() == 3) {
					List<Temp> tempList = tempDao.findTempByDeviceIdAndLimit(device.getDeviceId(), 30);
					if (tempList == null) {
						break;
					}
					sb.append("<div class='charts-grids states-mdl'>" + "" + "<h4 class='title'>"
							+ device.getDeviceName() + "</h4>" + "<canvas id='" + device.getDeviceName()
							+ "' height='300' width='800'></canvas>" + "</div>" + "<div class='clearfix'></div>"
							+ "<script>" + "var lineChartData = {" + "labels : [");
					for (Temp temp : tempList) {
						sb.append("'" + temp.getTime() + "',");
					}
					sb.delete(sb.length() - 1, sb.length());
					sb.append("]," + "datasets : [" + "{" + "fillColor : 'rgba(242, 179, 63, 1)',"
							+ "strokeColor : '#F2B33F'," + "pointColor : 'rgba(242, 179, 63, 1)',"
							+ "pointStrokeColor : '#fff'," + "data : [");
					for (Temp temp : tempList) {
						sb.append("'" + temp.getHumidity() + "',");
					}
					sb.delete(sb.length() - 1, sb.length());
					sb.append("]" + "}," + "{" + "fillColor : 'rgba(97, 100, 193, 1)'," + "strokeColor : '#6164C1',"
							+ "pointColor : 'rgba(97, 100, 193,1)'," + "pointStrokeColor : '#9358ac'," + "data : [");
					for (Temp temp : tempList) {
						sb.append("'" + temp.getTemperature() + "',");
					}
					sb.delete(sb.length() - 1, sb.length());

					sb.append("]" + "}]" + "};" + "new Chart(document.getElementById('" + device.getDeviceName()
							+ "').getContext('2d')).Line(lineChartData);" + "</script>'");
				}
			}
			response.getWriter().println(sb);
		}
		if (stat.equals("lessProfileDevice")) {
			int uid = ((User) request.getSession().getAttribute("userObj")).getUserId();
			int pid = Integer.valueOf(request.getParameter("pid"));
			ProfileTools profileTools = new ProfileTools();
			List<Device> deviceLessList = profileTools.findLessDeviceByPid(pid, uid);

			StringBuffer sb = new StringBuffer();
			for (Device device : deviceLessList) {
				sb.append("<option value=" + device.getDeviceId() +">" + device.getDeviceName() + "</option>");
			}
			response.getWriter().print(sb.toString());
		}

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
