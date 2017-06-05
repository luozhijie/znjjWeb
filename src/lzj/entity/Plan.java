package lzj.entity;

public class Plan {
	private int pid;
	private String pName;
	private String pTime;
	private String deviceIdOrProfile;
	private int pStat;
	private int pIsOpen;

	public Plan() {
		super();
	}

	public Plan(int pid, String pName, String pTime, String deviceIdOrProfile, int pStat, int pIsOpen) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.pTime = pTime;
		this.deviceIdOrProfile = deviceIdOrProfile;
		this.pStat = pStat;
		this.pIsOpen = pIsOpen;
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

	public String getpTime() {
		return pTime;
	}

	public void setpTime(String pTime) {
		this.pTime = pTime;
	}

	public String getDeviceIdOrProfile() {
		return deviceIdOrProfile;
	}

	public void setDeviceIdOrProfile(String deviceIdOrProfile) {
		this.deviceIdOrProfile = deviceIdOrProfile;
	}

	public int getpStat() {
		return pStat;
	}

	public void setpStat(int pStat) {
		this.pStat = pStat;
	}

	public int getpIsOpen() {
		return pIsOpen;
	}

	public void setpIsOpen(int pIsOpen) {
		this.pIsOpen = pIsOpen;
	}

	@Override
	public String toString() {
		return "Plan [pid=" + pid + ", pName=" + pName + ", pTime=" + pTime + ", deviceIdOrProfile=" + deviceIdOrProfile
				+ ", pStat=" + pStat + ", pIsOpen=" + pIsOpen + "]";
	}

	

}
