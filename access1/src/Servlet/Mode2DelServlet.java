package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDBConnect;

/**
 *DBにアクセスし結果画面へ
 */
@WebServlet("/Mode2DelServlet")
public class Mode2DelServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");


		//セッションスコープから登録情報を取得
		HttpSession session = request.getSession();
		String check =  (String) session.getAttribute("check");
		//DAOでデータベースに1件削除
		boolean resultStudent =new StudentDBConnect().deleteStudent(check);
System.out.println(check);
		String msg ="";
		if(resultStudent) {
			msg = "削除しました。";
		}else {
			msg = "削除に失敗しました。";
		}

		request.setAttribute("msg", msg);
		//結果画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(request, response);
		}

	}


