package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDBConnect;

/**
 確認画面からデータベースへ登録し結果を表示
 */
@WebServlet("/Mode2addConfirmServlet")
public class Mode2addConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");



		//セッションスコープから登録情報を取得
		HttpSession session = request.getSession();
		Student tempStudent = (Student) session.getAttribute("tempStudent");

		//DAOでデータベースに1件追加
		boolean resultStudent =new StudentDBConnect().insertStudentData(tempStudent);

		String msg ="";
		if(resultStudent) {
			msg = "登録が完了しました。";
		}else {
			msg = "登録に失敗しました。";
		}
		//セッションスコープを破棄
		session.invalidate();
		request.setAttribute("msg", msg);
		//結果画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);

	}

}