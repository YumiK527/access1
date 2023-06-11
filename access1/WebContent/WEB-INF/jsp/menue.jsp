<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// 結果メッセージをリクエストスコープから取得
String message = (String) request.getAttribute("msg");
//ログイン可否をセッションスコープから取得
String action = (String) session.getAttribute("action");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>【メニュー画面】</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<div class="mx-auto" style="width: 300px;">
		<form action="/access1/Menue2Servlet" method="post" name="login_form">
			<div class="login_form_top">
				<h1>MENUE</h1>
					<font color="red">
						<% if (message != null) { %>
							<%= message %>
						<% } %>
					</font>
				<p>
					メニューボタンを選択してください。
				</p>
			</div>
			<input type="submit"  name="menue" value="入退室連絡">
			<input type="submit"  name="menue" value="管理画面">
			<input type="submit"  name="menue" value="ログアウト">
		</form>
	</div>
</body>
</html>