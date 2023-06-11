package logic;
import beans.Account;

public class ValueCheck {

	//ログインページ用
	public static String checkLogin(Account account) {
		String msg = "";
		if(account == null) {
			 msg += "User Id、または Password が間違ってます。<br>";
		}else {
			msg += "";
		}
		return msg;
	}


	//生徒選択用
	public static String checkStudent(String check) {
		String msg = "";
		if(check == null) {
			 msg += "生徒が選択されていません。<br>";
		}else {
			msg += "";
		}
		return msg;
	}






}
