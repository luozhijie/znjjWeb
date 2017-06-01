package lzj.entity;

public class Warning {

	private int did; // 设备id
	private int wid; //警告id
	private String times;// 触发时间
	private DeviceType deviceType;// 设备类型
	private String deviceName;// 设备名称

	public Warning() {
	}

	public Warning(int did, int wid, String times, DeviceType deviceType, String deviceName) {
		super();
		this.did = did;
		this.wid = wid;
		this.times = times;
		this.deviceType = deviceType;
		this.deviceName = deviceName;
	}

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public int getWid() {
		return wid;
	}

	public void setWid(int wid) {
		this.wid = wid;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	@Override
	public String toString() {
		return "Warning [did=" + did + ", wid=" + wid + ", times=" + times + ", deviceType=" + deviceType
				+ ", deviceName=" + deviceName + "]";
	}


}
