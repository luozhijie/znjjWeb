package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lzj.DAO.BaseDao;
import lzj.DAO.ProfileActionDao;
import lzj.DAO.ProfileDao;
import lzj.entity.Profile;
import lzj.entity.ProfileAction;

public class ProfileDaoImpl extends BaseDao implements ProfileDao {

	@Override
	public int addProfile(Profile profile) {
		String sql = "INSERT INTO `znjj`.`profile_list` (`pname`, `puid`) VALUES (?, ?);";
		return this.exceuteUpdate(sql, new Object[] { profile.getpName(), profile.getUid() });
	}

	@Override
	public int delProfileByPid(int pid) {
		String sql = "DELETE FROM `znjj`.`profile_list` WHERE `pid`=?;";
		int tag = 0;
		tag += this.exceuteUpdate(sql, new Object[] { pid });
		tag += new ProfileActionDaoImpl().delProfileActionByPid(pid);
		return tag;
	}

	@Override
	public int updateProfile(Profile profile) {
		ProfileActionDao profileActionDao = new ProfileActionDaoImpl();
		String sql = "UPDATE `znjj`.`profile_list` SET `pname`=?, `puid`=? WHERE `pid`=?;";
		int tag = 0;
		List<ProfileAction> profileActionList = profileActionDao.findProfileActionByPid(profile.getPid());
		for (ProfileAction profileAction : profileActionList) {
			profileActionDao.updateProfileAction(profileAction);
			tag++;
		}
		tag += this.exceuteUpdate(sql, new Object[] { profile.getpName(), profile.getUid(), profile.getPid() });
		return tag;
	}

	@Override
	public List<Profile> findProfileByUid(int uid) {
		List<Profile> profileList = new ArrayList<>();
		String sql = "SELECT * FROM znjj.profile_list where puid = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { uid });
		ProfileActionDao profileActionDao = new ProfileActionDaoImpl();
		try {
			while (rs.next()) {
				Profile profile = new Profile();
				profile.setPid(rs.getInt("pid"));
				profile.setpName(rs.getString("pname"));
				profile.setUid(rs.getInt("puid"));
				List<ProfileAction> profileActionList = profileActionDao.findProfileActionByPid(profile.getPid());
				profile.setProfileActionList(profileActionList);
				profileList.add(profile);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return profileList;
	}

}
