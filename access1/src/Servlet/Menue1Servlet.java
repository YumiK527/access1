package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 結果画面からメニューリストへ
 */
@WebServlet("/Menue1Servlet")
public class Menue1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//アクション属性取得
		String menue = request.getParameter("menue");
		HttpSession session = request.getSession();

		//メニューの場合
		if (menue != null){
			//生徒リストをスコープから削除
			session.removeAttribute("studentList");
			//入退室連絡画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/menue.jsp").forward(request,response);
		}else {//ログアウトの場合
			//ログイン情報をスコープから削除
			session.removeAttribute("account");
			//生徒リストをスコープから削除
			session.removeAttribute("studentList");

			//ログイン画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);

		}
	}

}
