package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lzj.DAO.BaseDao;
import lzj.DAO.DeviceDao;
import lzj.entity.Device;
import lzj.entity.DeviceType;

public class DeviceDaoImpl extends BaseDao implements DeviceDao {

	@Override
	public int addDevice(Device device) {
		String sql = "INSERT INTO `znjj`.`device_list` (`user_id`, `device_name`, `device_type_id`, `device_gpio`) VALUES (?, ?, ?, ?);";
		return this.exceuteUpdate(sql, new Object[] { device.getUserId(), device.getDeviceName(),
				device.getDeviceType().getDeviceTypeId(), device.getDevice_gpio() });
	}

	@Override
	public int delDeviceById(int id) {
		return this.exceuteUpdate("DELETE FROM `znjj`.`device_list` WHERE `device_id`=?;", new Object[] { id });
	}

	@Override
	public int updateDevice(Device device) {
		String sql = "UPDATE `znjj`.`device_list` SET `device_name`=?, `device_type_id`=?, `device_gpio`=? WHERE `device_id`=?;";
		return this.exceuteUpdate(sql, new Object[] { device.getDeviceName(),device.getDeviceType().getDeviceTypeId(),device.getDevice_gpio(), device.getDeviceId() });
	}

	@Override
	public ArrayList<Device> findDeviceByUserId(int id) {
		ArrayList<Device> deviceList = new ArrayList<>();
		String sql = "SELECT * FROM znjj.view_device where user_id=?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { id });
		try {
			while (rs.next()) {
				Device device = new Device(rs.getInt("device_id"), rs.getInt("user_id"), rs.getString("device_name"),
						rs.getString("device_stat"),
						new DeviceType(rs.getInt("device_type_id"), rs.getString("device_type_name")),
						rs.getString("device_online"), rs.getInt("device_gpio"));
				deviceList.add(device);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeAll();
		}

		return deviceList;
	}

	@Override
	public int statChange(String stat, int deviceId) {
		String sql = "UPDATE `znjj`.`device_list` SET `device_stat`=? WHERE `device_id`=?;";
		return this.exceuteUpdate(sql, new Object[] { stat, deviceId });
	}

	@Override
	public void flashOnlineTime(ArrayList<Device> deviceList) {
		for (Device device : deviceList) {// 刷新在线时间
			this.exceuteUpdate("UPDATE `znjj`.`device_list` SET `device_online`=now() WHERE `device_id`=?;",
					new Object[] { device.getDeviceId() });
		}
	}

	@Override
	public List<Integer> gpioLess(int uid) {
		List<Integer> gpioList = new ArrayList<>();
		for (int i = 2; i <= 21; i++) {
			gpioList.add(i);
		}
		String sql = "SELECT * FROM znjj.device_list where user_id = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { uid });
		try {
			while (rs.next()) {
				gpioList.remove(Integer.valueOf(rs.getString("device_gpio")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gpioList;
	}

	public static void main(String[] args) {
	}

	@Override
	public Device findDeviceBydid(int did) {
		String sql = "SELECT * FROM znjj.view_device where device_id = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { did });
		Device device = null;
		try {
			if (rs.next()) {
				device = new Device(rs.getInt("device_id"), rs.getInt("user_id"), rs.getString("device_name"),
						rs.getString("device_stat"),
						new DeviceType(rs.getInt("device_type_id"), rs.getString("device_type_name")),
						rs.getString("device_online"), rs.getInt("device_gpio"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return device;
	}

}
