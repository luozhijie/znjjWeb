package lzj.entity;

public class FamilyGroup {
	private int fid;
	private String fimaryName;
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	private User user5;

	public FamilyGroup() {
		super();
	}

	public FamilyGroup(int fid, String fimaryName, User user1, User user2, User user3, User user4, User user5) {
		super();
		this.fid = fid;
		this.fimaryName = fimaryName;
		this.user1 = user1;
		this.user2 = user2;
		this.user3 = user3;
		this.user4 = user4;
		this.user5 = user5;
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

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public User getUser3() {
		return user3;
	}

	public void setUser3(User user3) {
		this.user3 = user3;
	}

	public User getUser4() {
		return user4;
	}

	public void setUser4(User user4) {
		this.user4 = user4;
	}

	public User getUser5() {
		return user5;
	}

	public void setUser5(User user5) {
		this.user5 = user5;
	}

	@Override
	public String toString() {
		return "FamilyGroup [fid=" + fid + ", fimaryName=" + fimaryName + ", user1=" + user1 + ", user2=" + user2
				+ ", user3=" + user3 + ", user4=" + user4 + ", user5=" + user5 + "]";
	}

}