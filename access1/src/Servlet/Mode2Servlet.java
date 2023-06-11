package Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 管理画面メニューから、メニューボタン毎へ分岐
 */
@WebServlet("/Mode2Servlet")
public class Mode2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  		request.setCharacterEncoding("UTF-8");

		String message = "";

		//リクエストパラメータの取得

		String done = request.getParameter("done");
		String check = request.getParameter("check");

		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("check", check);

System.out.println(check);
		//未選択の有無をチェック
		message = ValueCheck.checkStudent(check);
		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);

		//「追加」の場合、新規生徒入力画面へフォワード
		if(done.equals("add")) {
			message ="生徒情報を入力してください。";
			request.setAttribute("msg", message);
			request.getRequestDispatcher("/WEB-INF/jsp/mode2add.jsp").forward(request, response);
		}else if ("生徒が選択されていません。<br>".equals(message)) {
			//ログイン失敗時 同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		} else  if(done.equals("edit")){
			//選択されたチェックボックスの生徒番号を取得し、変数checkedCodeに代入する。
			String[] checkedCode = request.getParameterValues("check");
			//checkedCodeリストから、生徒レコードを取得し生徒リストを作成
			Student student =null;
			List<Student> studentList = new ArrayList<Student>();
			for (String co : checkedCode) {
				student = new StudentDBConnect().showCode(co);
				//studentList.add(student);
			}
			//セッションスコープに生徒リスト保存
			session.setAttribute("student", student);
			//編集ページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2edit.jsp").forward(request, response);
		} else  {
			//選択されたチェックボックスの生徒番号を取得し、変数checkedCodeに代入する。
			String[] checkedCode = request.getParameterValues("check");
			//checkedCodeリストから、生徒レコードを取得し生徒リストを作成
			Student student =null;
			List<Student> studentList = new ArrayList<Student>();
			for (String co : checkedCode) {
				student = new StudentDBConnect().showCode(co);
				//studentList.add(student);
			}
			//セッションスコープに生徒リスト保存
			session.setAttribute("student", student);

			//削除ページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2del.jsp").forward(request, response);
		}

	}

}