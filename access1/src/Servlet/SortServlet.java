package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import dao.StudentDBConnect;

/**
 * Servlet implementation class SortServlet
 */
@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     *並べ替えボタンの処理
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		String sort = request.getParameter("sort");

		if(sort.equals("並べ替え：学年順")){;
		//msgSortをリクエストスコープに保存
		sort="並べ替え：生徒番号順";
		request.setAttribute("sort", sort);
		//daoで並べ替え
		List<Student> studentList = new StudentDBConnect().showSortCode();
		//セッションスコープに生徒リスト保存
		session.setAttribute("studentList", studentList);
		}else {
			//msgSortをリクエストスコープに保存
			sort="並べ替え：学年順";
			request.setAttribute("sort", sort);
			//daoで並べ替え
			List<Student> studentList = new StudentDBConnect().showSortGrade();
			//セッションスコープに生徒リスト保存
			session.setAttribute("studentList", studentList);
		}
		//同じページログインページにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
	}

}
