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

		String sort = request.getParameter("sort");
		HttpSession session = request.getSession();
		//生徒リストをスコープから削除
		session.removeAttribute("studentList");
		session.removeAttribute("msg");

		//アクション属性取得
		String menue = request.getParameter("menue");

		//メソッド実行し生徒リストを用意
		List<Student> studentList = new StudentDBConnect().showSortGrade();
		//セッションスコープに生徒リスト保存
		session.setAttribute("studentList", studentList);
		//メニューの場合
		if (menue.equals("入退室連絡")){
			//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode1top");
			//入退室連絡画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode1.jsp").forward(request, response);
		}else if (menue.equals("管理画面")){
			request.setAttribute("sort", sort);
			//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode2top");
			//管理画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		}else {
			//スコープから削除
			session.removeAttribute("account");
			session.removeAttribute("studentList");
			session.removeAttribute("msg");
			session.removeAttribute("processmode");
			//ログイン画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
		}

	}

}
