package lzj.entity;

public class Warning {

	private String times;// 触发时间
	private DeviceType deviceType;// 设备类型
	private String deviceName;// 设备名称

	public Warning() {
	}

	public Warning(String times, DeviceType deviceType, String deviceName) {
		super();
		this.times = times;
		this.deviceType = deviceType;
		this.deviceName = deviceName;
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
		return "Warning [times=" + times + ", deviceType=" + deviceType + ", deviceName=" + deviceName + "]";
	}

}
