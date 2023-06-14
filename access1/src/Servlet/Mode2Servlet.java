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
		String sort="並べ替え：学年順";

		//セッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("check", check);
		//未選択の有無をチェック
		message = ValueCheck.checkStudent(check);
		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);

		if(done == null) {
			message = "処理が選択されていません。<br>";
			request.setAttribute("msg", message);
			request.setAttribute("sort", sort);
			//メソッド実行し生徒リストを用意
			List<Student> studentList = new StudentDBConnect().showSortGrade();
			//セッションスコープに生徒リスト保存
			session.setAttribute("studentList", studentList);
			//同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		}

		//「追加」の場合、新規生徒入力画面へフォワード
		if(done.equals("add")) {
			//セッションスコープを破棄
			session.removeAttribute("student");
			session.removeAttribute("studentList");
			message ="";
//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode2add");
			request.setAttribute("msg", message);
			request.getRequestDispatcher("/WEB-INF/jsp/mode2add.jsp").forward(request, response);
		}else if ("生徒が選択されていません。<br>".equals(message)) {
			//メソッド実行し生徒リストを用意
			List<Student> studentList = new StudentDBConnect().showSortGrade();
			//セッションスコープに生徒リスト保存
			session.setAttribute("studentList", studentList);
			//同じページログインページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
		} else  if(done.equals("edit")){
			message ="生徒情報を変更してください。";
			request.setAttribute("msg", message);
			//選択されたチェックボックスの生徒番号を取得し、変数checkedCodeに代入する。
			String[] checkedCode = request.getParameterValues("check");
			//checkedCodeリストから、生徒レコードを取得し生徒リストを作成
			Student student =null;
			List<Student> studentList = new ArrayList<Student>();
			for (String co : checkedCode) {
				student = new StudentDBConnect().showCode(co);
				studentList.add(student);
			}
//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode2edit");
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
				studentList.add(student);
			}
			//セッションスコープに生徒リスト保存
			session.setAttribute("student", student);
//セッションスコープに処理モードを保存
			session.setAttribute("processmode", "mode2del");
			message ="下記の生徒を削除します。";
			request.setAttribute("msg", message);
			//削除ページにフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/mode2del.jsp").forward(request, response);
		}

	}

}