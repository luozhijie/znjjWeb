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
import lzj.DAO.MessageBoardDao;
import lzj.DAO.PlanDao;
import lzj.DAO.ProfileDao;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.DeviceTypeDaoImpl;
import lzj.DaoImpl.FamilyGroupDaoImpl;
import lzj.DaoImpl.MessageBoradDaoImpl;
import lzj.DaoImpl.PlanDaoImpl;
import lzj.DaoImpl.ProfileDaoImpl;
import lzj.entity.Device;
import lzj.entity.DeviceType;
import lzj.entity.FamilyGroup;
import lzj.entity.MessageBoard;
import lzj.entity.Plan;
import lzj.entity.Profile;
import lzj.entity.User;
import lzj.entity.Warning;
import lzj.tools.ProfileTools;
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
			// 警告确认界面
			url = "warningCheck.jsp";
		}
		if (stat.equals("myCenter")) {
			// 个人中心界面
			url = "myCenter.jsp";
		}
		if (stat.equals("planAdd")) {
			// 计划添加界面
			request.setAttribute("deviceList", user.getDeviceList());
			url = "addPlan.jsp";
		}
		if (stat.equals("planDel")) {
			// 计划删除界面
			PlanDao planDao = new PlanDaoImpl();
			List<String> idList = new ArrayList<>();
			for (Device deivce : user.getDeviceList()) {
				idList.add("1," + deivce.getDeviceId());
			}
			List<Plan> planList = planDao.findPlanByDeviceIdList(idList);
			request.setAttribute("planList", planList);
			url = "planDel.jsp";
		}
		if (stat.equals("planEdit")) {
			// 计划任务修改
			PlanDao planDao = new PlanDaoImpl();
			List<String> idList = new ArrayList<>();
			for (Device deivce : user.getDeviceList()) {
				idList.add("1," + deivce.getDeviceId());
			}
			List<Plan> planList = planDao.findPlanByDeviceIdList(idList);
			request.setAttribute("planList", planList);
			request.setAttribute("deviceList", user.getDeviceList());
			url = "planEdit.jsp";
		}
		if (stat.equals("planOnOff")) {
			PlanDao planDao = new PlanDaoImpl();
			List<String> idList = new ArrayList<>();
			for (Device deivce : user.getDeviceList()) {
				idList.add("1," + deivce.getDeviceId());
			}
			List<Plan> planList = planDao.findPlanByDeviceIdList(idList);
			request.setAttribute("planList", planList);
			request.setAttribute("deviceList", user.getDeviceList());
			url = "planOnOff.jsp";

		}
		if (stat.equals("setting")) {
			List<FamilyGroup> familyGroupList = new FamilyGroupDaoImpl().findFamilyGroupByUid(user.getUserId());
			request.setAttribute("familyGroupList", familyGroupList);
			url = "setting.jsp";
		}
		if (stat.equals("messageBoard")) {
			MessageBoardDao messageBoardDao = new MessageBoradDaoImpl();
			List<MessageBoard> messageBoardList = messageBoardDao.findMessageBoardByUid(user.getUserId());
			System.out.println(messageBoardList.toString());
			request.setAttribute("messageBoardList", messageBoardList);
			url = "messageBorad.jsp";
		}
		if (stat.equals("profilesAdd")) {
			ProfileDao profileDao = new ProfileDaoImpl();
			List<Profile> profileList = profileDao.findProfileByUid(user.getUserId());
			request.setAttribute("profileList", profileList);
			url = "profilesAdd.jsp";
		}
		if (stat.equals("profileActive")) {
			ProfileDao profileDao = new ProfileDaoImpl();
			List<Profile> profileList = profileDao.findProfileByUid(user.getUserId());
			request.setAttribute("profileList", profileList);
			url = "profileActive.jsp";
		}
		if(stat.equals("profileDel")){
			ProfileDao profileDao = new ProfileDaoImpl();
			List<Profile> profileList = profileDao.findProfileByUid(user.getUserId());
			request.setAttribute("profileList", profileList);
			url = "profileDel.jsp";
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
