package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lzj.DAO.BaseDao;
import lzj.DAO.DeviceDao;
import lzj.DAO.TeleControlDao;
import lzj.DAO.UserDao;
import lzj.entity.Device;
import lzj.entity.TeleControl;
import lzj.entity.TeleControlType;
import lzj.entity.User;
import lzj.entity.UserType;

public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public int addUser(User user) {
		String sql = "INSERT INTO `znjj`.`user_list` (`user_name`, `user_password`, `user_type`) VALUES (?, ?, ?);";
		Object[] o = new Object[] { user.getUserName(), user.getUserPassWord(), user.getUserType().getUserTypeId() };
		return this.exceuteUpdate(sql, o);
	}

	@Override
	public int delUserById(int id) {
		String sql = "DELETE FROM `znjj`.`user_list` WHERE `user_id`=?;";
		return this.exceuteUpdate(sql, new Object[] { id });
	}

	@Override
	public int updateUser(User user) {
		String sql = "UPDATE `znjj`.`user_list` SET `user_name`=?, `user_password`=?, `user_type`=? WHERE `user_id`=?;";
		return this.exceuteUpdate(sql, new Object[] { user.getUserName(), user.getUserPassWord(),
				user.getUserType().getUserTypeId(), user.getUserId() });
	}

	@Override
	public User findUserById(int id) {
		DeviceDao deviceDao = new DeviceDaoImpl();
		TeleControlDao teleControlDao = new TeleControlDaoImpl();
		User user = null;
		String sql = "SELECT * FROM znjj.view_user_list where user_id=?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { id });
		HashMap<String, ArrayList<TeleControl>> teleControlMap = new HashMap<>();
		try {
			if (rs.next()) {
				ArrayList<Device> deviceList = deviceDao.findDeviceByUserId(rs.getInt("user_id"));
				for (Device device : deviceList) {
					if (device.getDeviceType().getDeviceTypeId() == 1) {
						ArrayList<TeleControl> teleControls = teleControlDao
								.findTeleControlByDeviceId(device.getDeviceId());
						teleControlMap.put(device.getDeviceName(), teleControls);
					}
				}
			}
			user = new User();
			user.setDeviceList(deviceDao.findDeviceByUserId(rs.getInt("user_id")));
			user.setIconName(rs.getString("user_icon") == null ? "default.jpg" : rs.getString("user_icon"));
			user.setTeleControlMap(teleControlMap);
			user.setUserId(rs.getInt("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setUserPassWord(rs.getString("user_password"));
			user.setUserType(new UserType(rs.getInt("user_type"), rs.getString("user_type_name")));

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return user;
	}

	@Override
	public ArrayList<User> findAllUser() {
		DeviceDao deviceDao = new DeviceDaoImpl();
		TeleControlDao teleControlDao = new TeleControlDaoImpl();
		String sql = "SELECT * FROM znjj.view_user_list;";
		ResultSet rs = this.execeuteQuary(sql, null);
		HashMap<String, ArrayList<TeleControl>> teleControlMap = new HashMap<>();
		ArrayList<User> userList = new ArrayList<>();
		try {
			while (rs.next()) {
				ArrayList<Device> deviceList = deviceDao.findDeviceByUserId(rs.getInt("user_id"));
				for (Device device : deviceList) {
					if (device.getDeviceType().getDeviceTypeId() == 1) {
						ArrayList<TeleControl> teleControls = teleControlDao
								.findTeleControlByDeviceId(device.getDeviceId());
						teleControlMap.put(device.getDeviceName(), teleControls);
					}
				}
				User user = new User();
				user.setDeviceList(deviceDao.findDeviceByUserId(rs.getInt("user_id")));
				user.setIconName(rs.getString("user_icon") == null ? "default.jpg" : rs.getString("user_icon"));
				user.setTeleControlMap(teleControlMap);
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassWord(rs.getString("user_password"));
				user.setUserType(new UserType(rs.getInt("user_type"), rs.getString("user_type_name")));
				userList.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return userList;
	}

	@Override
	public ArrayList<User> findUserByPage(int page) {
		return null;
	}

	@Override
	public User findUserByUserNameAndPassWord(String username, String password) {
		DeviceDao deviceDao = new DeviceDaoImpl();
		TeleControlDao teleControlDao = new TeleControlDaoImpl();
		User user = null;
		String sql = "SELECT * FROM znjj.view_user_list where user_name=? and user_password=?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { username, password });
		HashMap<String, ArrayList<TeleControl>> teleControlMap = new HashMap<>();
		ArrayList<User> userList = new ArrayList<>();
		try {
			while (rs.next()) {
				ArrayList<Device> deviceList = deviceDao.findDeviceByUserId(rs.getInt("user_id"));
				for (Device device : deviceList) {
					if (device.getDeviceType().getDeviceTypeId() == 1) {
						ArrayList<TeleControl> teleControls = teleControlDao
								.findTeleControlByDeviceId(device.getDeviceId());
						teleControlMap.put(device.getDeviceName(), teleControls);
					}
				}
				user = new User();
				user.setDeviceList(deviceDao.findDeviceByUserId(rs.getInt("user_id")));
				user.setIconName(rs.getString("user_icon") == null ? "default.jpg" : rs.getString("user_icon"));
				user.setTeleControlMap(teleControlMap);
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassWord(rs.getString("user_password"));
				user.setUserType(new UserType(rs.getInt("user_type"), rs.getString("user_type_name")));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return user;
	}

	@Override
	public User findUserByUserName(String username) {
		DeviceDao deviceDao = new DeviceDaoImpl();
		TeleControlDao teleControlDao = new TeleControlDaoImpl();
		User user = null;
		String sql = "SELECT * FROM znjj.view_user_list where user_name=?";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { username });
		HashMap<String, ArrayList<TeleControl>> teleControlMap = new HashMap<>();
		ArrayList<User> userList = new ArrayList<>();
		try {
			if (rs.next()) {
				ArrayList<Device> deviceList = deviceDao.findDeviceByUserId(rs.getInt("user_id"));
				for (Device device : deviceList) {
					if (device.getDeviceType().getDeviceTypeId() == 1) {
						ArrayList<TeleControl> teleControls = teleControlDao
								.findTeleControlByDeviceId(device.getDeviceId());
						teleControlMap.put(device.getDeviceName(), teleControls);
					}
				}
				user = new User();
				user.setDeviceList(deviceDao.findDeviceByUserId(rs.getInt("user_id")));
				user.setIconName(rs.getString("user_icon") == null ? "default.jpg" : rs.getString("user_icon"));
				user.setTeleControlMap(teleControlMap);
				user.setUserId(rs.getInt("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserPassWord(rs.getString("user_password"));
				user.setUserType(new UserType(rs.getInt("user_type"), rs.getString("user_type_name")));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}
		return user;
	}

}
