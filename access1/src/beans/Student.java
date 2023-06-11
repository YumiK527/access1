package beans;

import java.io.Serializable;

public class Student  implements Serializable{
	private String code;
	private String name;
	private String grade;
	private String pass;
	private String mailaddres;
	private String send;
	private String point;
	private int pointInt;


	public  Student() {}

	//DBから取得したデータでインスタンス生成する場合
	public  Student(String code,String name,String grade,String pass,String mailaddres,String send,String point) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.pass = pass;
		this.mailaddres = mailaddres;
		this.send = send;
		this.point = point;
	}

	public  Student(String code,String name,String grade,String pass,String mailaddres,String send,int pointInt) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.pass = pass;
		this.mailaddres = mailaddres;
		this.send = send;
		this.pointInt = pointInt;
	}

	public  Student(String code) {
		this.code = code;
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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getMailaddres() {
		return mailaddres;
	}

	public void setMailaddres(String mailaddres) {
		this.mailaddres = mailaddres;
	}

	public String getSend() {
		return send;
	}

	public void setSend(String send) {
		this.send = send;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public int getPointInt() {
		return pointInt;
	}

	public void setPointInt(int pointInt) {
		this.pointInt = pointInt;
	}

	//管理画面から商品情報を追加する場合のコンストラクタ
//	public Shohin(String shohinMei, String shohinBunrui, int hanbaiTanka, int shiireTanka, String releseDate,
//			int stock, String show) {
//		this.shohinMei = shohinMei;
//		this.shohinBunrui = shohinBunrui;
//		this.hanbaiTanka = hanbaiTanka;
//		this.shiireTanka = shiireTanka;
//		this.releseDate = Date.valueOf(releseDate);
//		this.stock = stock;
//		this.show = show;
//	}
}
