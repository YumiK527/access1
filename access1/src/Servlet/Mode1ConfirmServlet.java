package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDBConnect;
import logic.ValueCheck;

/**
 * Servlet implementation class Mode1ConfirmServlet
 */
@WebServlet("/Mode1ConfirmServlet")
public class Mode1ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";

		//アクション属性取得
		String menue = request.getParameter("menue");


		//リクエストパラメータの取得
		String inout = request.getParameter("inout");
		String check = request.getParameter("check");
		String mailText = request.getParameter("mailText");
		String textarea = request.getParameter("textarea");

		//未選択の有無をチェック
		message = ValueCheck.checkMode1(inout,check,mailText);
		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);


		if(message !="入力チェック<br>") {
			request.setAttribute("msg", message);
			//同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode1.jsp").forward(request, response);
		} else {
			//msgをリクエストスコープに保存
			request.setAttribute("inout", inout);
			request.setAttribute("check", check);
			request.setAttribute("mailText", mailText);
			request.setAttribute("textarea", textarea);
			//選択されたチェックボックスの生徒番号を取得し、変数checkedCodeに代入する。
			String[] checkedCode = request.getParameterValues("check");
			//checkedCodeリストから、生徒レコードを取得し生徒リストを作成
			Student student =null;
			ArrayList<Student> studentList = new ArrayList<Student>();
			for (String co : checkedCode) {
				student = new StudentDBConnect().showCode(co);
				studentList.add(student);
				//セッションスコープに生徒リスト保存
				HttpSession session = request.getSession();
				session.setAttribute("studentList", studentList);
			}
			request.getRequestDispatcher("/WEB-INF/jsp/mode1Confirm.jsp").forward(request, response);

		}
	}
}
