package lzj.DAO;

import java.util.List;

import lzj.entity.Profile;

public interface ProfileDao {
	public int addProfile(Profile profile);
	
	public int delProfileByPid(int pid);
	
	public int updateProfile(Profile profile);
	
	public List<Profile> findProfileByUid(int uid);
}
