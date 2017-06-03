package lzj.entity;

public class Plan {
	private int pid;
	private String pName;
	private String pTime;
	private int pDeviceId;
	private int pStat;

	public Plan() {
		super();
	}

	public Plan(int pid, String pName, String pTime, int pDeviceId, int pStat) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.pTime = pTime;
		this.pDeviceId = pDeviceId;
		this.pStat = pStat;
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

	public int getpDeviceId() {
		return pDeviceId;
	}

	public void setpDeviceId(int pDeviceId) {
		this.pDeviceId = pDeviceId;
	}

	public int getpStat() {
		return pStat;
	}

	public void setpStat(int pStat) {
		this.pStat = pStat;
	}

	@Override
	public String toString() {
		return "Plan [pid=" + pid + ", pName=" + pName + ", pTime=" + pTime + ", pDeviceId=" + pDeviceId + ", pStat="
				+ pStat + "]";
	}

}
