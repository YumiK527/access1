<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%@ page import="beans.Student"%>
<%
Student student = (Student) session.getAttribute("student");
%>
<html>
<head>
<meta charset="UTF-8">
<title>【削除確認】</title>
<link rel="stylesheet" href="style3add.css">
<style>
#mode2 {
	margin: auto;
}
</style>
</head>
<body>

	<div class="mx-auto" style="width: 700px;">
		<form action="/access1/Mode2DelServlet" method="post">
			<h1 style="text-align: center">
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#52c2d0">削除確認</font>
			</h1>

			<table id="mode2">
				<colgroup>
					<col width="500">
					<col width="150">
					<col width="700">
				</colgroup>
				<tr>
					<th>生徒番号</th>
					<td>　　    </td>
					<td><%=student.getCode()%></td>
				</tr>
				<tr>
					<th>氏名</th>
					<td></td>
					<td><%=student.getName()%></td>
				</tr>
				<tr>
					<th>学年コード</th>
					<td></td>
					<td><%=student.getGrade()%></td>
				</tr>
				<tr>
					<th>連絡可否</th>
					<td></td>
					<td><%=student.getSend()%></td>
					</tr>
				<tr>
					<th>メールアドレス</th>
					<td></td>
					<td><%=student.getMailaddres()%></td>
				</tr>
				<tr>
					<th>ポイント</th>
					<td></td>
					<td><%=student.getPoint()%></td>
				</tr>
				<tr>
					<th>暗証番号</th>
					<td></td>
					<td><%=student.getPass()%></td>
				</tr>
			</table>
			<table><tr>

			<td><input type="button" onclick="history.back()" value="戻る"></td>
			<td><input type="submit" name="botton" value="削除します"></td></tr></table>
				</form>
	</div>
</body>
</html>