package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lzj.DAO.BaseDao;
import lzj.DAO.BodySensorInfoDao;
import lzj.entity.BodySensorInfo;

public class BodySensorInfoDaoImpl extends BaseDao implements BodySensorInfoDao {

	@Override
	public int addBodySensorInfo(BodySensorInfo bodySensorInfo) {
		return this.addBodySensorInfoByDeviceId(bodySensorInfo.getDeviceId());
	}

	@Override
	public int delBodySensorInfoByBid(int bid) {
		String sql = "DELETE FROM `znjj`.`body_sensor_info` WHERE `bid`=?;";
		return this.exceuteUpdate(sql, new Object[] { bid });
	}

	@Override
	public int addBodySensorInfoByDeviceId(int deviceId) {
		String sql = "INSERT INTO `znjj`.`body_sensor_info` (`deviceId`) VALUES (?);";
		return this.exceuteUpdate(sql, new Object[] { deviceId });
	}

	@Override
	public int updateBodySensorInfo(BodySensorInfo bodySensorInfo) {
		String sql = "UPDATE `znjj`.`body_sensor_info` SET `deviceId`=?, `time`=?, `isCheck`=? WHERE `bid`=?;";
		return this.exceuteUpdate(sql, new Object[] { bodySensorInfo.getDeviceId(), bodySensorInfo.getTime(),
				bodySensorInfo.getIsCheck(), bodySensorInfo.getBid() });
	}

	@Override
	public int updateBodySensorInfoCheckBybid(int bid) {
		return this.exceuteUpdate("UPDATE `znjj`.`body_sensor_info` SET `isCheck`=1 WHERE `bid`=?;",
				new Object[] { bid });
	}

	@Override
	public List<BodySensorInfo> findAllBodySensorInfoByDeviceId(int deviceId) {
		String sql = "SELECT * FROM znjj.body_sensor_info where deviceId=?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { deviceId });
		List<BodySensorInfo> bodySensorInfoList = new ArrayList<>();
		try {
			while (rs.next()) {
				BodySensorInfo bodySensorInfo = new BodySensorInfo(rs.getInt("bid"), rs.getInt("deviceId"),
						rs.getString("time"), rs.getInt("isCheck"));
				bodySensorInfoList.add(bodySensorInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bodySensorInfoList.isEmpty() ? null : bodySensorInfoList;
	}

	@Override
	public List<BodySensorInfo> findWarningBodySensorByDeviceId(int deviceId) {
		String sql = "SELECT * FROM znjj.body_sensor_info where deviceId=? and isCheck = 0;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { deviceId });
		List<BodySensorInfo> bodySensorInfoList = new ArrayList<>();
		try {
			while (rs.next()) {
				BodySensorInfo bodySensorInfo = new BodySensorInfo(rs.getInt("bid"), rs.getInt("deviceId"),
						rs.getString("time"), rs.getInt("isCheck"));
				bodySensorInfoList.add(bodySensorInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bodySensorInfoList.isEmpty() ? null : bodySensorInfoList;
	}

}
