package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Account;
import beans.Student;
import dao.AccountDBConnect;
import dao.StudentDBConnect;
import logic.ValueCheck;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //ログイン画面を表示させる
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//		//アクション属性取得
//		String action = request.getParameter("account");
//		//ログアウトの場合
//		if (action != null && action.equals("logout")) {
//			//ログイン情報をスコープから削除
//			HttpSession session = request.getSession();
//			session.removeAttribute("account");
//			//TOP画面へフォワード
//			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
//		}
		//ログイン画面へフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,
				response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";

		//リクエストパラメータの取得
		String userid = request.getParameter("user_id");
		String pass = request.getParameter("password");
		String mode = request.getParameter("mode");

		HttpSession session = request.getSession();
		//アカウントチェック
		Account account = new AccountDBConnect().getAccount(userid, pass);
		//ユーザ情報をセッションスコープに保存
		session.setAttribute("account", account);

		message = ValueCheck.checkLogin(account);
		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);


		//メソッド実行し生徒リストを用意(.showSortGrade()学年順/showSortCode()生徒番号順
		List<Student> studentList = new StudentDBConnect(). showSortGrade();
		//セッションスコープに生徒リスト保存
		session.setAttribute("studentList", studentList);


		if ("User Id、または Password が間違ってます。<br>".equals(message)) {
			//ログイン失敗時 同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		} else if ("mode1".equals(mode)) {
			//ログイン成功の処理 mode1：入退室連絡
			//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode1top");
			//入退室連絡画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode1.jsp").forward(request, response);
		} else if("mode2".equals(mode)){
			//ログイン成功の処理 mode2:管理画面
			String sort ="並べ替え：学年順";
			request.setAttribute("sort", "並べ替え：学年順");
			//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode2top");
			//管理画面へフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		} else {
			//該当なしの場合 同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		}
	}
}
//
//           http://localhost:8080/access1/LoginServlet