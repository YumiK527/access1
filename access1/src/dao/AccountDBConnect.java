package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import beans.Account;

public class AccountDBConnect {

	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	//コンストラクタ
	public AccountDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//	login内容とadminidテーブルデータとの照会
	public Account getAccount(String userid, String pass) {
		String sql = "SELECT * FROM adminid WHERE userid = ? and pass = ?;";
		Account account ;
		account = null;

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, userid);
			st.setString(2, pass );
			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String userid1 = rs.getString("userid");
				String pass1 = rs.getString("pass");

				if(userid.equals(userid1) && pass.equals(pass1)) {

				// 1行分のデータを格納するインスタンス
				account = new Account(userid,pass);
				return account;
				}else {}
				return null;
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return null;
	}






















}
