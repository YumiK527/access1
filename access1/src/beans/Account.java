package beans;

import java.io.Serializable;

public class Account implements Serializable{

	private String userid;
	private String pass;

	public Account() {}

	//ログイン画面からログインをする場合のコンストラクタ
	public Account(String userid, String pass) {
		this.userid = userid;
		this. pass = pass;
	}
	public String getMail() {
		return userid;
	}

	public void setMail(String userid) {
		this.userid = userid;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
