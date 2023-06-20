package Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Time;
import dao.TimeDBConnect;
import logic.GetTime;


@WebServlet("/TimeClockServlet")
public class TimeClockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 「今日の入退室状況」ボタン押下したときの処理
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String message = "";

		//日時を取得
		String getTime = GetTime.getTime();
		//今日の日付を取得
		String today = getTime.substring(0, 10);
		//[I N]2023/06/18%  String inKey = "[I N]"+ today +"%";
		String inKey = "%"+ today +"%";
		//String outKey = "[OUT]"+ today +"%";
				//日付をキーにDBからデータを取得
		List<Time> studentListBase = new TimeDBConnect(). showInTime(inKey);


		//セッションスコープに保存
		session.setAttribute("today", today);
		session.setAttribute("studentListBase", studentListBase);
	//	session.setAttribute("studentListAdd", studentListAdd);
		//「今日の入退室」ページにフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/timeClock.jsp").forward(request, response);



	}
}