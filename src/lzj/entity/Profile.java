package lzj.entity;

import java.util.List;

public class Profile {
	private int pid;
	private String pName;
	private int uid;
	private List<ProfileAction> profileActionList;

	public Profile() {
		super();
	}

	public Profile(int pid, String pName, int uid, List<ProfileAction> profileActionList) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.uid = uid;
		this.profileActionList = profileActionList;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public List<ProfileAction> getProfileActionList() {
		return profileActionList;
	}

	public void setProfileActionList(List<ProfileAction> profileActionList) {
		this.profileActionList = profileActionList;
	}

	@Override
	public String toString() {
		return "Profile [pid=" + pid + ", pName=" + pName + ", uid=" + uid + ", profileActionList=" + profileActionList
				+ "]";
	}


}
