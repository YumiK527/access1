package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Student;
import beans.Time;


public class TimeDBConnect {

	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	//コンストラクタ
	public TimeDBConnect() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました");
			e.printStackTrace();
		}
	}

	/*
	 * 「time」テーブルから全てのデータを検索し
	 * 検索結果を返します。
	 */
	public List<Time> showAll() {

		String sql = "SELECT * FROM time;";

		Time time = null;
		List<Time> timeList = new ArrayList<Time>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code = rs.getString("code");
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				String point = rs.getString("point");
				String timeIn = rs.getString("timeIn");
				String timeOut = rs.getString("timeOut");
				String send = rs.getString("send");
				String mailMsg = rs.getString("mailMsg");
				// データを格納するインスタンス
				time = new Time(code,name,grade,point,timeIn,timeOut,send,mailMsg);

				// リストに1行分のデータを追加
				timeList.add(time);
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return timeList;
	}
	/*
	 * 生徒番号から「time」テーブルのデータを検索し
	 * 検索結果を返します。
	 * １入出時間、2退出時間、3連絡のみ+=
	 */
	//1.入室時間の取得
	public  List<Time>  showInTime(String inKey) {

		String sql = "select * from time where timeIn like ? ;";

		Time time = null;
		List<Time> timeList = new ArrayList<Time>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, inKey);
			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code = rs.getString("code");
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				String point = rs.getString("point");
				String timeIn = rs.getString("timeIn");
				String timeOut = rs.getString("timeOut");
				String send = rs.getString("send");
				String mailMsg = rs.getString("mailMsg");
				String mailMsg2 = rs.getString("mailMsg2");
				// データを格納するインスタンス
				time = new Time(code,name,grade,point,timeIn,timeOut,send,mailMsg,mailMsg2);
				// リストに1行分のデータを追加
				timeList.add(time);
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return timeList;
	}
	//2.退室時間の取得
	public  List<Time>  showOutTime(String outKey) {

		String sql = "select * from time where timeOut like ? ;";

		Time time = null;
		List<Time> timeList = new ArrayList<Time>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, outKey);
			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code = rs.getString("code");
				String timeOut = rs.getString("timeOut");
				String mailMsg = rs.getString("mailMsg2");
				// データを格納するインスタンス
				time = new Time(code,timeOut,mailMsg);
				// リストに1行分のデータを追加
				timeList.add(time);
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return timeList;
	}

	//入室時メール送信時にデータを1件登録するメソッド
	public boolean insertTimeData(Time timeRecord) {
		//SQL文の用意
		String sql = "INSERT INTO time(code,name,grade,point,timeIn,timeOut,send,mailMsg,mailMsg2,gradecode)";
		sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		//リターン用変数
		boolean isInsert = true;

		//SQLの実行
		try (Connection con = DriverManager.getConnection(URL, USER,
				PASSWORD)) {
			PreparedStatement ps = con.prepareStatement(sql);
			//データをSQL文に差し替え
			ps.setString(1, timeRecord.getCode());
			ps.setString(2, timeRecord.getName());
			ps.setString(3, timeRecord.getGrade());
			ps.setString(4, timeRecord.getPoint());
			ps.setString(5, timeRecord.getTimeIn());
			ps.setString(6, timeRecord.getTimeOut());
			ps.setString(7, timeRecord.getSend());
			ps.setString(8, timeRecord.getMailMsg());
			ps.setString(9, "");
			ps.setString(10, timeRecord.getGc());
			//INSERT文の結果を確認
			int result = ps.executeUpdate();
			System.out.println(result);
			if (result == 0) {
				isInsert =false;
				System.out.println( "登録エラー");
			}else {
			System.out.println( "登録しました");
//			isInsert = true;
			}
		} catch (Exception e) {
			System.err.println("登録エラー");
			isInsert = false;
		}
		System.err.println(isInsert);
		return isInsert;
	}

	//退室時メール送信時にデータを1件該当レコードに追加するメソッド
	public boolean addTimeData(String timeOut,String mailMsg2,String inKey,String code) {
		//SQL文の用意
		String sql ="update time set timeout = ?,mailmsg2 = ?";
		sql +="where timein like ? and code = ?;";
		//リターン用変数
		boolean isInsert = true;

		//SQLの実行
		try (Connection con = DriverManager.getConnection(URL, USER,
				PASSWORD)) {
			PreparedStatement ps = con.prepareStatement(sql);
			//商品データをSQL文に差し替え
			ps.setString(1, timeOut);
			ps.setString(2, mailMsg2);
			ps.setString(3, inKey);
			ps.setString(4, code);

			int result = ps.executeUpdate();
			System.out.println(result);
			if (result == 0) {
				isInsert =false;
				System.out.println( "登録エラー");
			}else {
			System.out.println( "登録しました");
//			isInsert = true;
			}
		} catch (Exception e) {
			System.err.println("登録エラー");
			isInsert = false;
		}
		System.err.println(isInsert);
		return isInsert;
	}
	
	//連絡のみの場合にレコードに登録するメソッド
	public boolean msgTimeData(Time timeRecord) {//(String code,String name,String grade,String timet,String send,String mailMsg,String gradecode) {
		//SQL文の用意
		String sql = "INSERT INTO time(code,name,grade,point,timeIn,timeOut,send,mailMsg,mailMsg2,gradecode)";
		sql += "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
		//リターン用変数
		boolean isInsert = true;

		//SQLの実行
		try (Connection con = DriverManager.getConnection(URL, USER,
				PASSWORD)) {
			PreparedStatement ps = con.prepareStatement(sql);
			//データをSQL文に差し替え
			ps.setString(1, timeRecord.getCode());
			ps.setString(2, timeRecord.getName());
			ps.setString(3, timeRecord.getGrade());
			ps.setString(4, timeRecord.getPoint());
			ps.setString(5, timeRecord.getTimeIn());
			ps.setString(6, "");
			ps.setString(7, timeRecord.getSend());
			ps.setString(8, timeRecord.getTimeIn());
			ps.setString(9, timeRecord.getMailMsg());
			ps.setString(10, timeRecord.getGc());
			//INSERT文の結果を確認
			int result = ps.executeUpdate();
			System.out.println(result);
			if (result == 0) {
				isInsert =false;
				System.out.println( "登録エラー");
			}else {
			System.out.println( "登録しました");
//			isInsert = true;
			}
		} catch (Exception e) {
			System.err.println("登録エラー");
			isInsert = false;
		}
		System.err.println(isInsert);
		return isInsert;
	}

	//生徒データを学年順に並べ替えるメソッド
	public List<Student> showSortGrade () {

		String sql = "SELECT * FROM Student order by gradecode;";

		Student student = null;
		List<Student> studentList = new ArrayList<Student>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code = rs.getString("code");
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				String pass = rs.getString("pass");
				String mailaddres = rs.getString("mailaddres");
				String send = rs.getString("send");
				String point = rs.getString("point");
				// データを格納するインスタンス
				student = new Student(code,name,grade,pass,mailaddres,send,point);

				// リストに1行分のデータを追加
				studentList.add(student);
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return studentList;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//生徒データを生徒番号順に並べ替えるメソッド
	public List<Student> showSortCode () {

		String sql = "SELECT * FROM Student order by code;";

		Student student = null;
		List<Student> studentList = new ArrayList<Student>();

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code = rs.getString("code");
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				String pass = rs.getString("pass");
				String mailaddres = rs.getString("mailaddres");
				String send = rs.getString("send");
				String point = rs.getString("point");
				// データを格納するインスタンス
				student = new Student(code,name,grade,pass,mailaddres,send,point);

				// リストに1行分のデータを追加
				studentList.add(student);
			}

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return studentList;
	}

	/*
	 * 検索結果からCSV形式の文字列を作成します。
	 */
	public List<String> makeCSVData(ResultSet rs) throws Exception {
		List<String> csvData = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();

		while (rs.next()) {
			sb = new StringBuilder();

			sb.append(rs.getString("code"));
			sb.append(",");
			sb.append(rs.getString("name"));
			sb.append(",");
			sb.append(rs.getString("grade"));
			sb.append(",");
			sb.append(rs.getString("pass"));
			sb.append(",");
			sb.append(rs.getString("mailaddres"));
			sb.append(",");
			sb.append(rs.getString("send"));
			sb.append(",");
			sb.append(rs.getString("point"));
			sb.append(",");
			sb.append(rs.getString("gradecode"));

			csvData.add(sb.toString());
		}

		return csvData;
	}
	/*
	 * 「student」テーブルから全てのデータを検索し、結果をCSV形式に整形したデータをリストで返します。
	 * （PreparedStatement方式）
	 */
	public List<String> studentAllCsvFormat() {
		List<String> csvData = new ArrayList<String>();

		/* 1) SQL文の準備 */
		String sql = "SELECT * FROM student ORDER BY code;";

		/* 2) PostgreSQLへの接続 */
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {

			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果からCSV形式データを作成 */
			csvData = makeCSVData(rs);

		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}

		return csvData;
	}


}//end

