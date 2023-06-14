package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Student;


public class StudentDBConnect {

	private final String URL = "jdbc:postgresql://localhost:5432/postgres";
	private final String USER = "postgres";
	private final String PASSWORD = "test";

	//コンストラクタ
	public StudentDBConnect() {
	try {
		Class.forName("org.postgresql.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("ドライバのロードに失敗しました");
		e.printStackTrace();
	}
}

/*
 * 「Student」テーブルから全てのデータを検索し
 * 検索結果を返します。
 */
	public List<Student> showAll() {

		String sql = "SELECT * FROM Student;";

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
	 * 生徒番号から「Student」テーブルのデータを検索し
	 * 検索結果を返します。
	 */
	public Student showCode(String code) {
		String sql = "SELECT * FROM Student WHERE code = ? ;";
		Student student = null;

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement st = con.prepareStatement(sql);) {
			st.setString(1, code);
			/* 3) SQL文の実行 */
			ResultSet rs = st.executeQuery();

			/* 4) 結果をリストに移し替える */
			while (rs.next()) {
				// 1行分のデータを読込む
				String code1 = rs.getString("code");
				String name = rs.getString("name");
				String grade = rs.getString("grade");
				String pass = rs.getString("pass");
				String mailaddres = rs.getString("mailaddres");
				String send = rs.getString("send");
				String point = rs.getString("point");

				if(code.equals(code1)) {

					// 1行分のデータを格納するインスタンス
					// データを格納するインスタンス
					student = new Student(code,name,grade,pass,mailaddres,send,point);

					return student;
				}else {}
				return null;
			}
		} catch (Exception e) {
			System.out.println("DBアクセス時にエラーが発生しました。");
			e.printStackTrace();
		}
		return null;
	}


//登録画面からデータを1件登録するメソッド
public boolean insertStudentData(Student student) {
	//SQL文の用意
	String sql = "INSERT INTO student(code,name,grade,pass,mailaddres,send,point,gradecode)";
	sql += "values(?, ?, ?, ?, ?, ?, ?, ?);";
	//リターン用変数
	boolean isInsert = true;

	//SQLの実行
	try (Connection con = DriverManager.getConnection(URL, USER,
			PASSWORD)) {
		PreparedStatement ps = con.prepareStatement(sql);
		//商品データをSQL文に差し替え
		ps.setString(1, student.getCode());
		ps.setString(2, student.getName());
		ps.setString(3, student.getGrade());
		ps.setString(4, student.getPass());
		ps.setString(5, student.getMailaddres());
		ps.setString(6, student.getSend());
		ps.setString(7, student.getPoint());
		ps.setString(8, student.getGc());
		//INSERT文の結果を確認
		int result = ps.executeUpdate();
		System.out.println(result);
		if (result == 0) {
			 isInsert =false;
		}
		System.out.println( "登録しました");
		 //isInsert = true;

	} catch (Exception e) {
		System.err.println("登録エラー");
		 isInsert = false;
	}
	return isInsert;
}

//削除画面からデータを1件削除するメソッド
public boolean deleteStudent(String code) {
	//SQL文の用意
	String sql = "DELETE from student ";
	sql += "WHERE code = ?;";
	//リターン用変数
	boolean isDelete = true;
	//SQLの実行
	try (Connection con = DriverManager.getConnection(URL, USER,
			PASSWORD)) {
		PreparedStatement ps = con.prepareStatement(sql);
		//データをSQL文に差し替え
		ps.setString(1, code);
		//INSERT文の結果を確認
		int result = ps.executeUpdate();
		if (result == 0) {
			return false;
		}
		System.out.println(result + "件削除しました");

	} catch (Exception e) {
		System.err.println("削除エラー");
		e.printStackTrace();
	}
	return isDelete;
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

}

