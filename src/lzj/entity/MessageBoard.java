package lzj.entity;

public class MessageBoard {
	private int mid;
	private String content;
	private int uid;
	private String date;
	private int isRead;
	private String iconName;
	private String userName;

	public MessageBoard() {
		super();
	}

	public MessageBoard(int mid, String content, int uid, String date, int isRead, String iconName, String userName) {
		super();
		this.mid = mid;
		this.content = content;
		this.uid = uid;
		this.date = date;
		this.isRead = isRead;
		this.iconName = iconName;
		this.userName = userName;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}

	public String getIconName() {
		return iconName;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "MessageBoard [mid=" + mid + ", content=" + content + ", uid=" + uid + ", date=" + date + ", isRead="
				+ isRead + ", iconName=" + iconName + ", userName=" + userName + "]";
	}

}
