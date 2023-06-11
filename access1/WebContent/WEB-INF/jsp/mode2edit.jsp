<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%@ page import="beans.Student"%>
<%
List<Student> studentList = (List<Student>) session.getAttribute("studentList");

// 結果メッセージをリクエストスコープから取得
String msg = (String) request.getAttribute("msg");
//セッションスコープから登録情報を取得
Student tempStudent = (Student) session.getAttribute("tempStudent");
String code = "";
String name = "";
String grade = "";
String pass = "";
String mailaddres = "";
String send = "";
String point = "0";
msg = "";
if (tempStudent != null) {
	code = tempStudent.getCode();
	name = tempStudent.getName();
	grade = tempStudent.getGrade();
	pass = tempStudent.getPass();
	mailaddres = tempStudent.getMailaddres();
	send = tempStudent.getSend();
	point = tempStudent.getPoint();

	//リクエストスコープからインスタンスを取得
	if (msg != null || msg != "") {
		msg = (String) request.getAttribute("msg");
	}

}
%>
<%
Student student = (Student) session.getAttribute("student");
String[] gradeAllay = { "", "S1(小1)", "S2(小2)", "S3(小3)", "S4(小4)", "S5(小5)", "S6(小6)", "C1(中1)", "C2(中2)", "C3(中3)","K1(高1)", "K2(高2)", "K3(高3)", };
%>
<html>
<head>
<meta charset="UTF-8">
<title>【変更】</title>
<link rel="stylesheet" href="style3add.css">
<style>
#mode2 {
	margin: auto;
}
</style>
</head>
<body>

	<div class="mx-auto" style="width: 700px;">
		<form action="/access1/Mode2addServlet" method="post"
			name="login_form">
			<h1 style="text-align: center">
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#52c2d0">生徒入力画面</font>
			</h1>
			<font color="red"> <%
 if (msg != null) {
 %> <%=msg%> <%
 }
 %>
			</font>

			<table id="mode2">
				<colgroup>
					<col width="500">
					<col width="150">
					<col width="700">
				</colgroup>
				<tr>
					<th>生徒番号</th>
					<td>　　</td>
					<td><input type="text" name="code" pattern="\d*" required
						size="8" placeholder="整数６桁" maxlength="6" value="<%=student.getCode()%>"><br></td>
				</tr>
				<tr>
					<th>氏名</th>
					<td></td>
					<td><input type="text" name="name" required size="30"
						placeholder="名前" value="<%=student.getName()%>"><br></td>
				</tr>
				<tr>
					<th>学年コード</th>
					<td></td>
					<td><select name="gradeAllay">
							<%String select = "";for (String gra : gradeAllay) {if (grade.equals(gra)) {
									select = "selected=\"selected\"";
								} else {
									select = "";}{%>
							<option <%=select%>><%=student.getGrade()%></option>
							<%
							}
							}
							%>
					</select><br></td>
				</tr>
				<tr>
					<th>連絡可否</th>
					<td></td>
					<td><select name="send" required>
							<option></option>
							<option value="ok">ｏｋ</option>
							<option value="no">ｎｏ</option>
							<option><%=student.getSend()%>
							</option>
					</select><font color="#aaa"> ok(送る) / no(送らない)</font></td>
				</tr>
				<tr>
					<th>メールアドレス</th>
					<td></td>
					<td><input type="email" name="mail" size="30" required
						placeholder="メールアドレス" maxlength="100" value="<%=student.getMailaddres()%>"></td>
				</tr>
				<tr>
					<th>ポイント</th>
					<td></td>
					<td><input type="number" name="point" size="6" required
						maxlength="4" step="10" min="0" max="9999" id="number"
						value="<%=student.getPoint()%>"></td>
				</tr>
				<tr>
					<th>暗証番号</th>
					<td></td>
					<td><input type="text" name="pass" required size="4"
						maxlength="4"
						value="<%=student.getPass()%>"></td>
				</tr>
			</table>

			<table>
				<tr>
					<td><input type="button" onclick="history.back()" value="戻る"></td>
					<td><input type="reset" value="クリア"></td>
					<td><input type="submit" name="botton" value="次へ"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>