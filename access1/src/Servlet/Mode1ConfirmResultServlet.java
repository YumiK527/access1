package Servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Student;
import beans.Time;
import dao.StudentDBConnect;
import dao.TimeDBConnect;
import logic.GetTime;

/**
 *送信ボタン押下時のデータをDBへ保存しメールを送信、結果画面へ
 */
@WebServlet("/Mode1ConfirmResultServlet")
public class Mode1ConfirmResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String msg = "送信しました。";
		String getTime = "";
		boolean insertTime;
		boolean addTime;


		// セッションスコープから取得
		String inout = (String) session.getAttribute("inout");
		String check = (String) session.getAttribute("check");
		String mailText = (String) session.getAttribute("mailText");
		String textarea = (String) session.getAttribute("textarea");
		List<Student> studentList = (List<Student>) session.getAttribute("studentList");
		//送信ボタン押下時の日時を取得
		getTime = GetTime.getTime();
		//今日の日付を取得
		String today = getTime.substring(0, 10);
		//[I N]2023/06/18%
		String inKey = "[I N]"+ today +"%";
		request.setAttribute("time", getTime);

//		Student student = new Student();
		//timetインスタンス（登録情報）の生成
				for (Student student : studentList) {
		String code = student .getCode();
		String name = student.getName();
		String grade= student.getGrade();
		String send = student.getSend();
		String point = student.getPoint();
		String timeIn = "-";
		String timeOut = "-";
		String mailMsg = "-";

		if(inout.equals("in")&& mailText.equals("msgFree" )){
			mailMsg =textarea;
			timeIn = ( "[I N]"+ getTime);
		}else if(inout.equals("in")) {
			mailMsg =mailText;
			timeIn = ( "[I N]"+ getTime);
		}else if(inout.equals("out") && mailText.equals("msgFree" )) {
			mailMsg =textarea;
			timeOut = ( "[OUT]"+ getTime);
		}else if(inout.equals("out")) {
			mailMsg =mailText;
			timeOut = ( "[OUT]"+ getTime);
		}else if (!(inout.equals("in")) && mailText.equals("msgFree" )) {
			mailMsg =textarea;//(textarea);
			timeIn = ("[Msg]"+getTime);
		}else{
			mailMsg = mailText ;
		}

		//学年から並べ替え用コードのインスタンスを作成
		//Gradecode gradecode = new Gradecode(grade);

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
		String gradecode=(mapGc.get(grade));

		//登塾ポイント加算
		String newPoint = point;
		int p = 1;
		int num;
		if(inout.equals("in")){
		num =Integer.parseInt(point) + p;
		newPoint = String.valueOf(num);

		//「stuidentte」テーブルの書き換え
		insertTime = new StudentDBConnect().poinAddStudent(newPoint,code) ;
			if(!insertTime) {
				msg = "ポイント更新ができませんでした。";
			}
		}


		//日時データを取得
		Time timeRecord = new Time(code,name,grade,newPoint,timeIn,timeOut,send,mailMsg,gradecode);


		if(inout.equals("in")) {//入室日時データをtimeテーブルに追加
			insertTime = new TimeDBConnect().insertTimeData(timeRecord);
			if(!insertTime) {
				msg = "入室時間の送信ができませんでした。";
			}
		} else 	if(inout.equals("out")) {//退室日時データをtimeテーブルに登録
			addTime = new TimeDBConnect().addTimeData(timeOut,mailMsg,inKey,code);
			if(!addTime) {
				msg = "入室時間なし。<br>退室時間の送信ができませんでした。";
			}
		} else {// メッセージのみ
			insertTime = new TimeDBConnect().msgTimeData(timeRecord);
			if(!insertTime) {
				msg = "メッセージの送信ができませんでした。";
			}
		}
				}
		//【未着手】メール送信処理
		request.setAttribute("msg", msg);
		//結果画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/mode1ConfirmResult.jsp").forward(request, response);
	}

}


