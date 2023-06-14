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
	private String gc;


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

	public  Student(String code,String name,String grade,String pass,String mailaddres,String send,String point,String gc) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.pass = pass;
		this.mailaddres = mailaddres;
		this.send = send;
		this.point = point;
		this.gc = gc;
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

	public String getGc() {
		return gc;
	}

	public void setGc(String gc) {
		this.gc = gc;
	}


}
