package lzj.tools;

import java.util.List;
import lzj.DAO.DeviceDao;
import lzj.DAO.ProfileActionDao;
import lzj.DaoImpl.DeviceDaoImpl;
import lzj.DaoImpl.ProfileActionDaoImpl;
import lzj.entity.Device;
import lzj.entity.ProfileAction;

public class ProfileTools {
	public int activeProfileByPid(int pid) {
		ProfileActionDao profileActionDao = new ProfileActionDaoImpl();
		DeviceDao deviceDao = new DeviceDaoImpl();
		List<ProfileAction> profileActionList = profileActionDao.findProfileActionByPid(pid);
		int tag = 0;
		for (ProfileAction profileAction : profileActionList) {
			deviceDao.statChange(profileAction.getA_action() + "", profileAction.getaDeviceId());
			tag++;
		}
		return tag;
	}

	public List<Device> findLessDeviceByPid(int pid, int uid) {
		ProfileActionDao profileActionDao = new ProfileActionDaoImpl();
		DeviceDao deviceDao = new DeviceDaoImpl();
		List<Device> deviceList = deviceDao.findDeviceByUserId(uid);
		List<ProfileAction> profileActionList = profileActionDao.findProfileActionByPid(pid);
		
		for (int i = 0; i < profileActionList.size(); i++) {
			for (int j = 0; j < deviceList.size(); j++) {
				if (deviceList.get(j).getDeviceId() == profileActionList.get(i).getaDeviceId()) {
					deviceList.remove(j);
				}
			}
		}

		return deviceList;
	}
}
