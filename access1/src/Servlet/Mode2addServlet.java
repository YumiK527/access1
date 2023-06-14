package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import logic.ValueCheck;

/**
 生徒入力データを受取り、確認画面へ渡す
 */
@WebServlet("/Mode2addServlet")
public class Mode2addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		//スコープから削除
		session.removeAttribute("msg");

		String message = "";

		//リクエストパラメータの取得
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String grade = request.getParameter("gradeAllay");
		String pass = request.getParameter("pass");
		String mailaddres = request.getParameter("mail");
		String send = request.getParameter("send");
		String point = request.getParameter("point");

        // Mapの宣言
        Map<String, String> mapGc = new HashMap<>();

        // Mapにキーと値を格納
        mapGc.put("小１", "11");
        mapGc.put("小２", "12");
        mapGc.put("小３", "13");
        mapGc.put("小４", "14");
        mapGc.put("小５", "15");
        mapGc.put("小６", "16");
        mapGc.put("中１", "21");
        mapGc.put("中２", "22");
        mapGc.put("中３", "23");
        mapGc.put("高１", "31");
        mapGc.put("高２", "32");
        mapGc.put("高３", "33");

        // Mapからデータを取得する
        String gc=(mapGc.get(grade));

		//Studentインスタンス（登録情報）の生成
		Student student = new Student(code,name,grade,pass,mailaddres,send,point,gc);
		//登録情報をセッションスコープに保存
		session.setAttribute("tempStudent",student);
		//入力値のチェック
		message = ValueCheck.checkAddStudent(code,name,grade,pass,mailaddres,send,point);
		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);

		if(message !="入力チェック<br>") {
			request.getRequestDispatcher("/WEB-INF/jsp/mode2add.jsp").forward(request, response);
		} else {
		request.getRequestDispatcher("/WEB-INF/jsp/mode2addConfirm.jsp").forward(request, response);
		}
	}
}
