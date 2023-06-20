package Servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDBConnect;


/**
 *CSVデータを出力
 */
@WebServlet("/CsvOutputServlet")
public class CsvOutputServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String message = "";


		String OUTPUT_PATH = "C:\\Users\\admin\\OneDrive\\ドキュメント\\student.csv";
		StudentDBConnect studentDb = new StudentDBConnect();

		try (FileWriter fw = new FileWriter(OUTPUT_PATH);
				BufferedWriter bw = new BufferedWriter(fw);) {

			List<String> csvData = studentDb.studentAllCsvFormat();

			// タイトル行書出し
			bw.write("生徒番号,名前,学年,パスワード,メールアドレス,メール可否,ポイント,学年コード");

			// データ行書出し
			for (int i = 0; i < csvData.size(); i++) {
				bw.newLine();
				bw.write(csvData.get(i));
			}
			bw.flush();

			System.out.println("出力処理が完了しました。");
			message = "CSVファイルを出力しました。";
		} catch (Exception e) {
			System.out.println("CSV出力処理中にエラーが発生しました。");
			e.printStackTrace();
			message = "CSVファイルを出力できませんでした。";
		}

		//msgをリクエストスコープに保存
		request.setAttribute("msg", message);

	//同じページログインページにフォワード
	request.getRequestDispatcher("/WEB-INF/jsp/mode2.jsp").forward(request, response);
	}
}
