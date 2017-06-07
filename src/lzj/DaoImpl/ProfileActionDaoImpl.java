package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lzj.DAO.BaseDao;
import lzj.DAO.ProfileActionDao;
import lzj.entity.ProfileAction;

public class ProfileActionDaoImpl extends BaseDao implements ProfileActionDao {

	@Override
	public int addProfileAction(ProfileAction profileAction) {
		String sql = "INSERT INTO `znjj`.`profile_action` (`a_deviceId`, `a_action`, `a_pid`) VALUES (?, ?, ?);";
		return this.exceuteUpdate(sql,
				new Object[] { profileAction.getaDeviceId(), profileAction.getA_action(), profileAction.getPid() });
	}

	@Override
	public int delProfileActionByAid(int aid) {
		String sql = "DELETE FROM `znjj`.`profile_action` WHERE `aid`=?;";
		return this.exceuteUpdate(sql, new Object[] { aid });
	}

	@Override
	public int delProfileActionByPid(int pid) {
		String sql = "DELETE FROM `znjj`.`profile_action` WHERE `a_pid`=?;";
		return this.exceuteUpdate(sql, new Object[] { pid });
	}

	@Override
	public int updateProfileAction(ProfileAction profileAction) {
		String sql = "UPDATE `znjj`.`profile_action` SET `a_deviceId`=?, `a_action`=?, `a_pid`=? WHERE `aid`=?;";
		return this.exceuteUpdate(sql, new Object[] { profileAction.getaDeviceId(), profileAction.getA_action(),
				profileAction.getPid(), profileAction.getAid() });
	}

	@Override
	public List<ProfileAction> findProfileActionByPid(int pid) {
		List<ProfileAction> profileActionList = new ArrayList<>();
		String sql = "SELECT * FROM znjj.profile_action_view where `a_pid`=?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[]{pid});
		try {
			while(rs.next()){
				ProfileAction profileAction = new ProfileAction();
				profileAction.setA_action(rs.getInt("a_action"));
				profileAction.setaDeviceId(rs.getInt("a_deviceId"));
				profileAction.setAid(rs.getInt("aid"));
				profileAction.setPid(rs.getInt("a_pid"));
				profileAction.setpDeviceName(rs.getString("device_name"));
				profileActionList.add(profileAction);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profileActionList;
	}

}
