package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CsvTimeServlet
 */
@WebServlet("/CsvTimeServlet")
public class CsvTimeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 	/**
	 * 入退出一覧のCSVデータを出力
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";










	}

}
