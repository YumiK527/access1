package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;

/**
 生徒入力データを受取り、確認画面へ渡す
 */
@WebServlet("/Mode2addServlet")
public class Mode2addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//		String forwordUrl = "/WEB-INF/jsp/Mode2addConfirm.jsp";
		//リクエストパラメータの取得
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String grade = request.getParameter("gradeAllay");
		String pass = request.getParameter("pass");
		String mailaddres = request.getParameter("mail");
		String send = request.getParameter("send");
		String point = request.getParameter("point");

		//Studentインスタンス（登録情報）の生成
		Student student = new Student(code,name,grade,pass,mailaddres,send,point);

		//登録情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("tempStudent",student);

//		request.getRequestDispatcher(forwordUrl).forward(request, response);
		request.getRequestDispatcher("/WEB-INF/jsp/mode2addConfirm.jsp").forward(request, response);

	}

}
