//package Servlet;
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import beans.Student;
//import dao.StudentDBConnect;
//import logic.ValueCheck;
//
///**
// 管理画面メニューから、メニューボタン毎へ分岐
// */
//@WebServlet("/Mode2ConfirmServlet")
//public class Mode2ConfirmServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//
//		String message = "";
//
//		//リクエストパラメータの取得
//
//		String done = request.getParameter("done");
//		String check = request.getParameter("check");
//
//
////		未選択の有無をチェック
//		message = ValueCheck.checkStudent(check);
////		msgをリクエストスコープに保存
//		request.setAttribute("msg", message);
//
//		//「追加」の場合、新規生徒入力画面へフォワード
//		if(done.equals("add")) {
//			request.getRequestDispatcher("/WEB-INF/jsp/mode2add.jsp").forward(request, response);
//		}else if ("生徒が選択されていません。<br>".equals(message)) {
//			//ログイン失敗時 同じページログインページにフォワード
//			request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
//		} else  if(done.equals("edit")){
//			//編集ページにフォワード
//			request.getRequestDispatcher("/WEB-INF/jsp/mode2edit.jsp").forward(request, response);
//		} else  {
//			//削除ページにフォワード
//			request.getRequestDispatcher("/WEB-INF/jsp/mode2del.jsp").forward(request, response);
//		}
//		//選択されたチェックボックスのデータを取得し、変数checkedCodeに代入する。
//		String[] checkedCode = request.getParameterValues("check");
//		Student student =null;
//		//checkedCodeリストから、生徒レコードを取得し生徒リストを作成
//		List<Student> studentList = new ArrayList<Student>();
//		for (String co : checkedCode) {
//			student = new StudentDBConnect().showCode(co);
//			//studentList.add(student);
//		}
//		//セッションスコープに生徒リスト保存
//		HttpSession session = request.getSession();
//		session.setAttribute("studentList", studentList);
//		request.getRequestDispatcher("/WEB-INF/jsp/mode2Confirm.jsp").forward(request, response);
//	}
//
//}