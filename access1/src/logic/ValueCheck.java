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

	//連絡画面用 mode1
	public static String checkMode1(String inout,String check,String mailText) {
		String errMessage =  "入力チェック<br>";
		// 連絡モードの選択チェック
		if (inout == null) {
			errMessage += "処理が選択されていません。<br>";
		}
		//生徒選択チェック
		if(check == null) {
			errMessage  += "生徒が選択されていません。<br>";
		}
		//連絡内容のチェック
		if(mailText == null) {
			errMessage  += "メッセージ内容が選択されていません。<br>";
//		}else if(mailText == "msgFree" && textarea == null) {
//			errMessage  += "メッセージ内容が入力されていません。<br>";
		}
		return errMessage;
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

	//生徒情報入力チェック
	public static String checkAddStudent
	(String code,
		String name,
		String grade,
		String pass,//
		String mailaddres,		//
		String send,            //
		String point) {

	String errMessage = "入力チェック<br>";

	// 生徒番号チェック
	if (code.isEmpty()) {
		errMessage += "生徒番号は必須項目です。<br>";
	}
	if(code.length()!=6){
		errMessage += "生徒番号は6桁です。<br>";
	}
	// 名前チェック
	if (name.isEmpty()) {
		errMessage += "名前は必須項目です。<br>";
	}
	// 学年チェック
	if (grade.isEmpty()) {
		errMessage += "学年は必須項目です。<br>";
	}
	// パスワード内容チェック
	if (pass.isEmpty()) {
		errMessage += "パスワードは必須項目です。<br>";
	}
	long passNum = 0;
	try {
		passNum = Long.parseLong(pass);
	} catch (NumberFormatException e) {
		errMessage += "パスワードは半角整数で入力してください。<br>";
		e.printStackTrace();
	}
	if(pass.length()!=4){
		errMessage += "パスワードは4桁です。<br>";
	}
	// メールチェック
	if (mailaddres.isEmpty()) {
		errMessage += "メールアドレスは必須項目です。<br>";
	}
	// メール受取可否
	if (send == null) {
		errMessage += "メール受取可否は必須項目です。<br>";
	}
	// ポイント内容チェック
	if (point.isEmpty()) {
		errMessage += "ポイントは必須項目です。<br>";
	}
	long pointNum = 0;
	try {
		pointNum = Long.parseLong(point);
	} catch (NumberFormatException e) {
		errMessage += "ポイントは半角整数で入力してください。<br>";
		e.printStackTrace();
	}
	return errMessage;
	}
}
