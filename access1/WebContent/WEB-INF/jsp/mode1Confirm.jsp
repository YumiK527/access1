<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%
// 結果メッセージをリクエストスコープから取得
String inout = (String) request.getAttribute("inout");
String check = (String) request.getAttribute("check");
String mailText = (String) request.getAttribute("mailText");
String textarea = (String) request.getAttribute("textarea");
List<Student> studentList = (List<Student>) session.getAttribute("studentList");

%>

<html>
<head>
<meta charset="UTF-8">
<title>【連絡確認画面】</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<div class="mx-auto" style="width: 500px;">
		<form action="/access1/Mode1ConfirmResultServlet" method="post"
			name="login_form">
			<h1>
				<font face="HG丸ｺﾞｼｯｸM-PRO">連絡確認画面</font>
			</h1>
			<%
			if (inout.equals("in")) {//入退室どちらかの表示
			%>
			<h2>
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff69b4">入室連絡</font>
			</h2>
			<%
			} else if(inout.equals("out")) {
			%>
			<h2>
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff69b4">退室連絡</font>
			</h2>
			<%
				} else {
			%>
			<h2>
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff69b4">連絡</font>
			</h2>
			<%
			}
			%>
			<%
			if (mailText.equals("選択肢3")) {//メッセージの表示
			%>
			<h3>
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff69b4"><%=textarea%></font>

				<%
				} else {
			%>
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff69b4"><%=mailText%><br></font>
			</h3>

			<%
			}
			%>
			<br>
			<br>

			<table class="design04">
				<tr>
					<th>生徒番号</th>
					<th>名 前</th>
					<th>学 年</th>
				</tr>
				<%
				for (Student student : studentList) {
				%>
				<tr>
					<td><%=student.getCode()%></td>
					<td><%=student.getName()%></td>
					<td><%=student.getGrade()%></td>
				</tr>
				<%
				}
				%>
				</tbody>
			</table>
			<input type="submit" name="botton" value="メール送信"> <input
				type="button" onclick="history.back()" value="戻る">
		</form>
	</div>
</body>
</html>
