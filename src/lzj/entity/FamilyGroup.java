package lzj.entity;

public class FamilyGroup {
	private int fid;
	private String fimaryName;
	private int familyPersonId1;
	private int familyPersonId2;
	private int familyPersonId3;
	private int familyPersonId4;
	private int familyPersonId5;

	public FamilyGroup() {
		super();
	}

	public FamilyGroup(int fid, String fimaryName, int familyPersonId1, int familyPersonId2, int familyPersonId3,
			int familyPersonId4, int familyPersonId5) {
		super();
		this.fid = fid;
		this.fimaryName = fimaryName;
		this.familyPersonId1 = familyPersonId1;
		this.familyPersonId2 = familyPersonId2;
		this.familyPersonId3 = familyPersonId3;
		this.familyPersonId4 = familyPersonId4;
		this.familyPersonId5 = familyPersonId5;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFimaryName() {
		return fimaryName;
	}

	public void setFimaryName(String fimaryName) {
		this.fimaryName = fimaryName;
	}

	public int getFamilyPersonId1() {
		return familyPersonId1;
	}

	public void setFamilyPersonId1(int familyPersonId1) {
		this.familyPersonId1 = familyPersonId1;
	}

	public int getFamilyPersonId2() {
		return familyPersonId2;
	}

	public void setFamilyPersonId2(int familyPersonId2) {
		this.familyPersonId2 = familyPersonId2;
	}

	public int getFamilyPersonId3() {
		return familyPersonId3;
	}

	public void setFamilyPersonId3(int familyPersonId3) {
		this.familyPersonId3 = familyPersonId3;
	}

	public int getFamilyPersonId4() {
		return familyPersonId4;
	}

	public void setFamilyPersonId4(int familyPersonId4) {
		this.familyPersonId4 = familyPersonId4;
	}

	public int getFamilyPersonId5() {
		return familyPersonId5;
	}

	public void setFamilyPersonId5(int familyPersonId5) {
		this.familyPersonId5 = familyPersonId5;
	}

	@Override
	public String toString() {
		return "FamilyGroup [fid=" + fid + ", fimaryName=" + fimaryName + ", familyPersonId1=" + familyPersonId1
				+ ", familyPersonId2=" + familyPersonId2 + ", familyPersonId3=" + familyPersonId3 + ", familyPersonId4="
				+ familyPersonId4 + ", familyPersonId5=" + familyPersonId5 + "]";
	}

}
