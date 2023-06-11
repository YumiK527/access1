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
<title>【ログイン画面】</title>
<link rel="stylesheet" href="style.css">
</head>
<body>

	<div class="mx-auto" style="width: 300px;">


		<form action="/access1/LoginServlet" method="post"
			name="login_form">
			<div class="login_form_top">

				<span
					style="font-size: 30px; font-weight: bold; color: transparent; background: repeating-linear-gradient(90deg, #4169e1 0, #90CAF9 100%); -webkit-background-clip: text;">Access
					Mail</span>
				<h1>LOGIN</h1>
				<font color="red"> <% if (message != null) { %> <%= message %>
					<% } %>
				</font>
				<p>
					Processing Mode、User Id ,Passwordをご入力の上、<br>「LOGIN」ボタンをクリックしてください。
				</p>
			</div>
			<div class="login_form_btm">
				<select name="mode" required>
					<option value="" disabled selected style="display: none;">
						Processing Mode</option>
					<option value="mode1">入退室連絡</option>
					<option value="mode2">管理画面</option>
				</select> <input type="id" name="user_id" placeholder="User Id " required>
				<input type="password" name="password" placeholder="Password"
					required> <input type="submit" name="botton" value="LOGIN">
			</div>
		</form>
	</div>
</body>
</html>


