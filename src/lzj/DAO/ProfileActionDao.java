package lzj.DAO;

import java.util.List;

import lzj.entity.ProfileAction;

public interface ProfileActionDao {
	public int addProfileAction(ProfileAction profileAction);
	
	public int delProfileActionByAid(int aid);
	
	public int delProfileActionByPid(int pid);
	
	public int updateProfileAction(ProfileAction profileAction);
	
	public List<ProfileAction> findProfileActionByPid(int pid);
}
