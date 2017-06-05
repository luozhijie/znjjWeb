package lzj.DaoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import lzj.DAO.BaseDao;
import lzj.DAO.FamilyGroupDao;
import lzj.entity.FamilyGroup;

public class FamilyGroupDaoImpl extends BaseDao implements FamilyGroupDao {

	@Override
	public int addFamiryGroup(FamilyGroup familyGroup) {
		String sql = "INSERT INTO `znjj`.`family_group` (`family_name`, `family_person_id1`, `family_person_id2`, `family_person_id3`, `family_person_id4`, `family_person_id5`) VALUES (?, ?, ?, ?, ?, ?);";
		return this.exceuteUpdate(sql,
				new Object[] { familyGroup.getFimaryName(), familyGroup.getFamilyPersonId1(),
						familyGroup.getFamilyPersonId2(), familyGroup.getFamilyPersonId3(),
						familyGroup.getFamilyPersonId4(), familyGroup.getFamilyPersonId5() });
	}

	@Override
	public int delFamiryGroup(int gid) {
		return this.exceuteUpdate("DELETE FROM `znjj`.`family_group` WHERE `fid`=?;", new Object[] { gid });
	}

	@Override
	public int updateFamiryGroup(FamilyGroup familyGroup) {
		String sql = "UPDATE `znjj`.`family_group` SET `family_name`=?, `family_person_id1`=?, `family_person_id2`=?, `family_person_id3`=?, `family_person_id4`=?, `family_person_id5`=? WHERE `fid`=?;";
		return this.exceuteUpdate(sql,
				new Object[] { familyGroup.getFimaryName(), familyGroup.getFamilyPersonId1(),
						familyGroup.getFamilyPersonId2(), familyGroup.getFamilyPersonId3(),
						familyGroup.getFamilyPersonId4(), familyGroup.getFamilyPersonId5(), familyGroup.getFid() });
	}

	@Override
	public FamilyGroup findFamilyGroupByFid(int fid) {
		String sql = "SELECT * FROM znjj.family_group where fid = ?;";
		ResultSet rs = this.execeuteQuary(sql, new Object[] { fid });
		FamilyGroup familyGroup = null;
		try {
			if (rs.next()) {
				familyGroup = new FamilyGroup();
				familyGroup.setFid(rs.getInt("fid"));
				familyGroup.setFimaryName(rs.getString("family_name"));
				familyGroup.setFamilyPersonId1(rs.getInt("family_person_id1"));
				familyGroup.setFamilyPersonId2(rs.getInt("family_person_id2"));
				familyGroup.setFamilyPersonId3(rs.getInt("family_person_id3"));
				familyGroup.setFamilyPersonId4(rs.getInt("family_person_id4"));
				familyGroup.setFamilyPersonId5(rs.getInt("family_person_id5"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return familyGroup;
	}

}