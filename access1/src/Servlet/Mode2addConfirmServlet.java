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
		//セッションスコープから登録情報を取得
		String processmode = (String)session.getAttribute("processmode");
		Student tempStudent = (Student) session.getAttribute("tempStudent");
		String code = tempStudent.getCode();

		String msg ="";
		//変更処理の場合、レコードを削除
		if(processmode.equals("mode2edit")) {
			//DAOでデータベースから件削除
			boolean delStudent =new StudentDBConnect().deleteStudent(code);

			if(delStudent) {
				msg = "削除が完了しました。";
			}else {
				msg = "データの削除に失敗しました。";
			}
		}

		//変更処理の続きまたは新規追加処理
		if(msg.equals("削除が完了しました。") || processmode.equals("mode2add") ) {

			//DAOでデータベースに1件追加
			boolean addStudent =new StudentDBConnect().insertStudentData(tempStudent);

			msg ="";
			if(addStudent) {
				msg = "登録が完了しました。";
				//セッションスコープを破棄
				session.removeAttribute("tempStudent");
			}else {
				msg = "登録に失敗しました。";
			}
		}
			request.setAttribute("msg", msg);
			//結果画面にフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
	}
}
