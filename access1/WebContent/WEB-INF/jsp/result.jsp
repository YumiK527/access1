<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
// 結果メッセージをリクエストスコープから取得
String message = (String) request.getAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
<title>【結果】</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="mx-auto" style="width: 500px;">
		<form action="/access1/Menue1Servlet" method="post" name="login_form">
			<h1>
				<font face="HG丸ｺﾞｼｯｸM-PRO"><%=message %></font>
			</h1>
			<input type="submit"  name="menue" value="メニュー">
			<input type="submit"  name="logout" value="ログアウト">
		</form>
	</div>
</body>
</html>