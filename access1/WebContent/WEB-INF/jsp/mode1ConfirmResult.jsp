<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String msg = (String) request.getAttribute("msg");
//時間をリクエストスコープから取得
String time = (String) request.getAttribute("time"); %>
<html>
<head>
<meta charset="UTF-8">
<title>【連絡結果画面】</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="mx-auto" style="width: 500px;">
		<form action="/access1/Menue1Servlet" method="post" name="login_form">
			<h1>
				<font face="HG丸ｺﾞｼｯｸM-PRO"><%= msg %></font>
			</h1>
			<font color="red"> <%= time %></font>
			<input type="submit"  name="menue" value="メニュー">
			<input type="submit"  name="logout" value="ログアウト">
		</form>
	</div>
</body>
</html>