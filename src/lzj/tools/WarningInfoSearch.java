package lzj.tools;

import java.util.ArrayList;
import java.util.List;

import lzj.DAO.BodySensorInfoDao;
import lzj.DAO.GasSensorDao;
import lzj.DaoImpl.BodySensorInfoDaoImpl;
import lzj.DaoImpl.GasSensorDaoImpl;
import lzj.entity.BodySensorInfo;
import lzj.entity.Device;
import lzj.entity.GasSensor;
import lzj.entity.User;
import lzj.entity.Warning;

public class WarningInfoSearch {
	public static List<Warning> search(User user) {
		GasSensorDao gasSensorDao = new GasSensorDaoImpl();
		BodySensorInfoDao bodySensorInfoDao = new BodySensorInfoDaoImpl();
		List<Warning> warningList = new ArrayList<>();
		List<Device> deviceList = user.getDeviceList();
		for (Device device : deviceList) {
			switch (device.getDeviceType().getDeviceTypeId()) {
			case 4:
				List<GasSensor> gasSensorList = gasSensorDao.findGasWarningByDeviceId(device.getDeviceId());
				if (gasSensorList != null) {
					for (GasSensor gasSensor : gasSensorList) {
						warningList.add(
								new Warning(gasSensor.getDeviceId(),gasSensor.getGid(), gasSensor.getTime(), device.getDeviceType(), device.getDeviceName()));
					}
				}
				break;
			case 6:
				List<BodySensorInfo> bodySensorInfoList = bodySensorInfoDao
						.findWarningBodySensorByDeviceId(device.getDeviceId());
				if (bodySensorInfoList != null) {
					for (BodySensorInfo bodySensorInfo : bodySensorInfoList) {
						warningList.add(new Warning(bodySensorInfo.getDeviceId(),bodySensorInfo.getBid(), bodySensorInfo.getTime(), device.getDeviceType(),
								device.getDeviceName()));
					}
				}
				break;
			default:
				break;
			}
		}
		return warningList.isEmpty() ? null : warningList;
	}
}
