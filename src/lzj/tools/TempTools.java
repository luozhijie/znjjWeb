package lzj.tools;

import java.util.List;
import lzj.DAO.TempDao;
import lzj.DaoImpl.TempDaoImpl;
import lzj.entity.Device;
import lzj.entity.Temp;
import lzj.entity.User;

public class TempTools {

	public TempTools() {
	}

	public static List<Temp> getTemp(User user) {
		List<Temp> tempList = null;
		TempDao tempDao = new TempDaoImpl();
		List<Device> deviceList = user.getDeviceList();
		for (Device device : deviceList) {
			if (device.getDeviceType().getDeviceTypeId() == 3) {
				tempList = tempDao.findTempByDeviceIdAndLimit(device.getDeviceId(), 20);
			}
		}
		return tempList;
	}

}
