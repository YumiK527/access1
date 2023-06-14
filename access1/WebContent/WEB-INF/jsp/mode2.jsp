<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Student"%>
<%
//処理モードをセッションスコープから取得
String processmode = (String) session.getAttribute("processmode");

List<Student> studentList = (List<Student>) session.getAttribute("studentList");

// 結果メッセージをリクエストスコープから取得
String message = (String) request.getAttribute("msg");
String sort = (String) request.getAttribute("sort");
if (sort == null) {
	sort = "並べ替え：学年順";
}
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
	<form  method="post" action="?">
			<h1 style="text-align: center">
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="blue">管理画面</font>
			</h1>
			<h2 style="text-align: center">
			<font face="HG丸ｺﾞｼｯｸM-PRO" color="red"> <%if (message != null) { %> <%=message%> <% } %>
			</font></h2>
		<h3 style="text-align: center"><font face="HG丸ｺﾞｼｯｸM-PRO" color="blue">
		該当する生徒を選択してください。</font></h3>
		<%
			if (studentList != null && !studentList.isEmpty()) {
			%>
		<table>
			<tr>
				<th><input type="submit" name="sort" value="<%=sort%>"
					formaction="/access1/SortServlet"></th>
			</tr>
		</table>
		<table class="design04">
			<tr>
				<th><font size="5"><select name="done"
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
		<table  id="buttonStyle" >
			<tr>
			<td>　　　</td>
			<td><input type="button" onclick="history.back()" value="戻る"></td>
			<td><input type="submit" name="menue"  value="メニュー" formaction="/access1/Menue1Servlet"></td>
			<td><input type="submit" name="botton" value="次へ" formaction="/access1/Mode2Servlet"></td>
			<td>　　　</td>
		</tr>
		</table>
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
