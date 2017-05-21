package lzj.DAO;

import java.util.List;

import lzj.entity.BodySensorInfo;

public interface BodySensorInfoDao {
	public int addBodySensorInfo(BodySensorInfo bodySensorInfo);

	public int delBodySensorInfoByBid(int bid);

	public int addBodySensorInfoByDeviceId(int deviceId);

	public int updateBodySensorInfo(BodySensorInfo bodySensorInfo);

	public int updateBodySensorInfoCheckBybid(int bid);

	public List<BodySensorInfo> findAllBodySensorInfoByDeviceId(int deviceId);

}
