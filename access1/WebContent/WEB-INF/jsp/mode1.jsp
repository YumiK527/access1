<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%
//処理モードをセッションスコープから取得
String processmode = (String) request.getAttribute("processmode");

List<Student> studentList = (List<Student>) session.getAttribute("studentList");

// 結果メッセージをリクエストスコープから取得
String message = (String) request.getAttribute("msg");
%>

<html>
<head>
<meta charset="UTF-8">
<title>【入退室連絡】</title>
<link rel="stylesheet" href="style.css">
<style>
select {
	background-color: yellow;
	color: blak;
	border-radius: 20px;
	font-size: 20px;
	width: 90px;
	height: 40px;
	cursor: pointer;
}
</style>
</head>
<body>

	<div class="mx-auto" style="width: 500px;">
		<form method="post" action="?" name="login_form">
			<h1 style="text-align:center">
				<font face="HG丸ｺﾞｼｯｸM-PRO">入退室連絡</font>
			</h1>
			<font color="red">
				<% if (message != null) { %> <%= message %><%}%>
			</font>
			<p>該当する生徒を選択してください。</p>
			<%if (studentList != null && !studentList.isEmpty()) {%>
			<table class="design04">
				<tr>
					<th>
					<font size="5"><select name="inout"  style="text-align:-webkit-center;">
					<option value="" disabled selected style="display: none;"> 選択</option>
					<option value="in">入 室</option>
					<option value="out">退 室</option>
					<option value="non">連 絡</option>
				</select></font>
					</th>
					<th>生徒番号</th>
					<th>名 前</th>
					<th>学 年</th>
				</tr>
				<%for (Student student : studentList) {%>
				<tr>
					<td><input type="checkbox" name="check"
						value="<%=student.getCode()%>"></td>
					<td><%=student.getCode()%></td>
					<td><%=student.getName()%></td>
					<td><%=student.getGrade()%></td>
				</tr><%}%>
				</tbody>
			</table>
			<br> <br>
			<table>
				<tr>
					<td><input type="radio" name="mailText" value="-" ></td>
					<td><font color="yellow">メッセージなし</font></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><input type="radio" name="mailText" value="メール発信は遅れましたが通常の時刻に確認しています。" ></td>
					<td><font color="yellow">メール発信は遅れましたが通常の時刻に確認しています。</font></td>
				</tr>
			</table>
			<table>
				<tr>
					<td><input type="radio" name="mailText" value="msgFree" ></td>
					<td><p style="border: solid 3px black">
							<input type="text" name="textText" style="width: 450px; height: 30px;"
							value="メール発信は遅れましたが　　：　　に確認しています。" >
				</tr>
			</table>
			<input type="submit" name="menue" value="確認画面へ" formaction="/access1/Mode1ConfirmServlet">
			<input type="submit" name="menue"  value="メニュー" formaction="/access1/Menue1Servlet">
			<input type="button" onclick="history.back()" value="戻る">
			<%} else {%>
			<p>ーー生徒リストは空ですーー</p>
			<%}%>
		</form>
	</div>
</body>
</html>
