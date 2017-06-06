package lzj.DAO;

import java.util.List;

import lzj.entity.FamilyGroup;

public interface FamilyGroupDao {
	public int addFamiryGroup(FamilyGroup familyGroup);

	public int delFamiryGroup(int gid);

	public int updateFamiryGroup(FamilyGroup familyGroup);

	public FamilyGroup findFamilyGroupByFid(int fid);
	
	public List<FamilyGroup> findFamilyGroupByUid(int uid);
	
	public List<Integer> findMainUserIdByUid(int uid);
}
