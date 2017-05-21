package lzj.entity;

public class BodySensorInfo {
	private int bid;
	private int deviceId;
	private String time;
	private int isCheck;

	public BodySensorInfo() {
		super();
	}

	public BodySensorInfo(int bid, int deviceId, String time, int isCheck) {
		super();
		this.bid = bid;
		this.deviceId = deviceId;
		this.time = time;
		this.isCheck = isCheck;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}

	@Override
	public String toString() {
		return "BodySensor [bid=" + bid + ", deviceId=" + deviceId + ", time=" + time + ", isCheck=" + isCheck + "]";
	}

}
