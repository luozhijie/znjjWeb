package lzj.DAO;

import lzj.entity.FamilyGroup;

public interface FamilyGroupDao {
	public int addFamiryGroup(FamilyGroup familyGroup);

	public int delFamiryGroup(int gid);

	public int updateFamiryGroup(FamilyGroup familyGroup);

	public FamilyGroup findFamilyGroupByFid(int fid);
}
