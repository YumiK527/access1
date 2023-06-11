package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mode1ConfirmResultServlet
 */
@WebServlet("/Mode1ConfirmResultServlet")
public class Mode1ConfirmResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";

		//結果画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/mode1ConfirmResult.jsp").forward(request, response);

	}

}
