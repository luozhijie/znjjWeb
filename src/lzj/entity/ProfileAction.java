package lzj.entity;

public class ProfileAction {
	private int aid;
	private int aDeviceId;
	private int a_action;
	private int pid;
	private String pDeviceName;

	public ProfileAction() {
		super();
	}

	public ProfileAction(int aid, int aDeviceId, int a_action, int pid, String pDeviceName) {
		super();
		this.aid = aid;
		this.aDeviceId = aDeviceId;
		this.a_action = a_action;
		this.pid = pid;
		this.pDeviceName = pDeviceName;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getaDeviceId() {
		return aDeviceId;
	}

	public void setaDeviceId(int aDeviceId) {
		this.aDeviceId = aDeviceId;
	}

	public int getA_action() {
		return a_action;
	}

	public void setA_action(int a_action) {
		this.a_action = a_action;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getpDeviceName() {
		return pDeviceName;
	}

	public void setpDeviceName(String pDeviceName) {
		this.pDeviceName = pDeviceName;
	}

	@Override
	public String toString() {
		return "ProfileAction [aid=" + aid + ", aDeviceId=" + aDeviceId + ", a_action=" + a_action + ", pid=" + pid
				+ ", pDeviceName=" + pDeviceName + "]";
	}


}
