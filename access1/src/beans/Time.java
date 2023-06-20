package beans;

import java.io.Serializable;

public class Time  implements Serializable{
	private String code;
	private String name;
	private String grade;
	private String point;
	private String timeIn;
	private String timeOut;
	private String send;
	private String mailMsg;
	private String mailMsg2;
	private String gc;

	public  Time() {}

	//DBから取得したデータでインスタンス生成する場合
	public  Time(String code,String name,String grade,String point,String timeIn,String timeOut,String send,String mailMsg) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.point = point;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.send = send;
		this.mailMsg = mailMsg;
	}
	//並び変え用コードを追加　DB登録する場合
	public  Time(String code,String name,String grade,String point,String timeIn,String timeOut,String send,String mailMsg,String gc) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.point = point;
		this.timeIn = timeIn;
		this.timeOut = timeOut;
		this.send = send;
		this.mailMsg = mailMsg;
		this.gc = gc;
	}

		//DBから取得したデータでインスタンス生成する場合
		public  Time(String code,String timeOut,String mailMsg) {
			this.code = code;
			this.timeOut = timeOut;
			this.mailMsg = mailMsg;
	}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}

		public String getPoint() {
			return point;
		}

		public void setPoint(String point) {
			this.point = point;
		}

		public String getTimeIn() {
			return timeIn;
		}

		public void setTimeIn(String timeIn) {
			this.timeIn = timeIn;
		}

		public String getTimeOut() {
			return timeOut;
		}

		public void setTimeOut(String timeOut) {
			this.timeOut = timeOut;
		}

		public String getSend() {
			return send;
		}

		public void setSend(String send) {
			this.send = send;
		}

		public String getMailMsg() {
			return mailMsg;
		}

		public void setMailMsg(String mailMsg) {
			this.mailMsg = mailMsg;
		}

		public String getMailMsg2() {
			return mailMsg2;
		}


		public void setMailMsg2(String mailMsg2) {
			this.mailMsg2 = mailMsg2;
		}

		public String getGc() {
			return gc;
		}

		public void setGc(String gc) {
			this.gc = gc;
		}



}//end
