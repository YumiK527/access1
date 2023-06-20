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
	private String in;
	private String out;
	private String info;

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
	//並び変え用コードを追加　DB登録する場合
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
	//入退室管理用
	public  Student(String code,String name,String grade,String in,String out,String send,String info,String gc,String hoge) {
		this.code = code;
		this.name = name;
		this.grade = grade;
		this.in = in;
		this.out = out;
		this.send = send;
		this.info = info;
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

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getOut() {
		return out;
	}

	public void setOut(String out) {
		this.out = out;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}



}//end
