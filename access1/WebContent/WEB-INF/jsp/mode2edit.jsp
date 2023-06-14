<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%@ page import="beans.Student"%>
<%
//処理モードをセッションスコープから取得
String processmode = (String)session.getAttribute("processmode");

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
	//リクエストスコープからインスタンスを取得
	if (msg != null || msg != "") {
		msg = (String) request.getAttribute("msg");
	}
%>
<%
Student student = (Student) session.getAttribute("student");
String[] gradeAllay = { "", "小１", "小２", "小３", "小４", "小５", "小６", "中１", "中２", "中３","高１", "高２", "高３", };
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
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="blue">生徒情報の変更</font>
			</h1>
			<h3 style="text-align: center">
			<font face="HG丸ｺﾞｼｯｸM-PRO" color="blue"> <%if (msg != null) { %> <%=msg%> <% } %>
			</font></h3>
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
						size="8" placeholder="整数６桁" maxlength="6" value="<%=student.getCode()%>"readonly>
						<font color="#aaa"> 生徒番号は変更できません</font></td>
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
							<%
							String select = "";
							for (String gra : gradeAllay) {
								if (student.getGrade().equals(gra)) {
									select = "selected=\"selected\"";
								} else {
									select = "";
								}
								{
							%>
							<option <%=select%>><%=gra%></option>
							<%
							}
							}
							%>
							<option selected><%=student.getGrade()%>
					</select><br></td>
				</tr>
				<tr>
					<th>連絡可否</th>
					<td></td>
					<td><select name="send" required>
							<option></option>
							<option value="ok">ok</option>
							<option value="no">no</option>
							<option selected><%=student.getSend()%>
							</option>
					</select><font color="#aaa"> ok(受取る) / no(受取らない)</font></td>
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
					<td><input type="number" name="point" size="6"
						maxlength="4" step="1" min="0" max="9999" id="number"
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
					<td><input type="submit" name="botton" value="次へ"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>