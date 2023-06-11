package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDBConnect;

/**
 メニューリストボタンからそれぞれのメニューへ
 */
@WebServlet("/Menue2Servlet")
public class Menue2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//アクション属性取得
		String menue = request.getParameter("menue");
		HttpSession session = request.getSession();

		//生徒リストをスコープから削除
		session.removeAttribute("studentList");

		//メニューの場合
		if (menue.equals("入退室連絡")){
			//生徒リストを再度取得
			//入退室連絡画面へフォワード
			//ログイン成功の処理 mode1：入退室連絡
			//匿名クラスでメソッド実行し生徒リストを用意
			List<Student> studentList = new StudentDBConnect().showAll();
			//セッションスコープに生徒リスト保存
			session.setAttribute("studentList", studentList);
			//入退室連絡画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode1.jsp").forward(request, response);
		}else if (menue.equals("管理画面")){
			//生徒リストを再度取得
			//管理面へフォワード
			//匿名クラスでメソッド実行し生徒リストを用意
			List<Student> studentList = new StudentDBConnect().showAll();
			//セッションスコープに生徒リスト保存
			session.setAttribute("studentList", studentList);
			//管理画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		}else {
			//ログイン情報をスコープから削除
			session.removeAttribute("account");

			//ログイン画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
		}

	}

}
