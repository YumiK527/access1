

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%
List<Student> studentList = (List<Student>) session.getAttribute("studentList");

// 結果メッセージをリクエストスコープから取得
String message = (String) request.getAttribute("msg");
%>

<html>
<head>
<meta charset="UTF-8">
<title>【管理画面】</title>
<link rel="stylesheet" href="style2.css">
<style>
select {
	background-color: rgb(255, 255, 255);
	color: blak;
	border-radius: 20px;
	font-size: 20px;
	width: 90px;
	height: 40px;
	cursor: pointer;
	color: rgb(0, 0, 255);
}
</style>
</head>
<body>
	<form action="/access1/Mode2Servlet" method="post">
		<h1>
			<font face="HG丸ｺﾞｼｯｸM-PRO">管理画面</font>
		</h1>
		<font color="red"> <% if (message != null) { %> <%= message %> <% } %>
		</font>
		<p>該当する生徒を選択してください。</p>
		<%
			if (studentList != null && !studentList.isEmpty()) {
			%>
		<table class="design04">
			<tr>
				<th><font size="5"><select name="done" required
						style="text-align: -webkit-center;">
							<option value="" disabled selected style="display: none;">
								選択</option>
							<option value="add">追加</option>
							<option value="edit">編集</option>
							<option value="del">削除</option>
					</select></font></th>
				<th>生徒<br>番号
				</th>
				<th>名前</th>
				<th>学年</th>
				<th>メール<br>可否</th>
				<th>メールアドレス</th>
				<th>ポイント</th>
				<th>暗証番号</th>
			</tr>
			<%
				for (Student student : studentList) {%>
			<tr>
				<td><input type="radio" name="check"
					value="<%=student.getCode()%>"></td>
				<td><%=student.getCode()%></td>
				<td><%=student.getName()%></td>
				<td><%=student.getGrade()%></td>
				<td><%=student.getSend()%></td>
				<td><%=student.getMailaddres()%></td>
				<td><%=student.getPoint()%></td>
				<td><%=student.getPass()%></td>
			</tr>
			<%
				}
				%>
			</tbody>
		</table>
		<br> <br> <input type="submit" name="botton" value="次へ">
		<input type="button" onclick="history.back()" value="戻る">
		<%
			} else {
			%>
		<p>ーー生徒リストは空ですーー</p>
		<%
			}
			%>
	</form>

</body>
</html>
