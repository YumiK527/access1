<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="java.util.List,beans.Time" %>
<%@ page import="java.util.List,logic.GetTime"%>
<%
//処理モードをセッションスコープから取得
String processmode = (String) session.getAttribute("processmode");

List<Time> studentListBase = (List<Time>) session.getAttribute("studentListBase");
String today = (String) session.getAttribute("today");
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
<title>【今日の入退室】</title>
<link rel="stylesheet" href="style4time.css">
</head>
<body>
	<form  method="post" action="?">

	<style>
.example2{
    display: flex;
}
.example2 label{
    display: block;
    width: 120px;
    background: none;
    color: rgb(255, 255, 255);
    padding: 10px;
    margin: 10px;
    box-sizing: border-box;
    text-align: center;
    text-decoration: none;
    border-radius: 30px;
    cursor: pointer;
}
.example2 input:checked+label{
    background: RGB(255,153,204);
    color: #FFF;
}
.example2 input{
    display: none;
}
</style>

			<h1 style="text-align: center">
				<font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff0099">今日の入退室</font>
			</h1>
			 <h2 style="text-align: center">
			<font face="HG丸ｺﾞｼｯｸM-PRO" color="red"> <%if (message != null) {%> <%=message%> <% } %>
			</font></h2>
		<h2 style="text-align: center"><font face="HG丸ｺﾞｼｯｸM-PRO" color="#ff0099">
		[　<%=today %>　]</font></h2>
		<%
			if (studentListBase != null && !studentListBase.isEmpty()) {
			%>
					<table>
						<tr>
							<td><input type="submit" name="sort" value="今日の入退室CSV（未）"
								formaction="/access1/CsvTimeServlet"></td>
						</tr>
					</table>
		<%--  <table class="design04" id="search-box">
				<tr><th><input type="submit" name="sort" value="生徒番号"
								formaction="/access1/44444444kServlet" id="search_submit"></th>
					<th><input type="submit" name="sort" value="名前"
								formaction="/access1/44444444kServlet" id="search_submit"></th>
				<th><input type="submit" name="sort" value="学年"
								formaction="/access1/44444444kServlet" id="search_submit"></th>
				<th><input type="submit" name="sort" value="IN"
								formaction="/access1/44444444kServlet" id="search_submit"></th>
				<th><input type="submit" name="sort" value="OUT"
								formaction="/access1/44444444kServlet" id="search_submit"></th>
				<th>可否</th>
				<th>連絡</th>
		</table>
							--%>
		<div class="example2"  >
			<table class="design04">
				<tr align="center">
					<th><input type="radio"  id="11" name="example5"><label
						for="11">生徒番号</label></th>
					<th><input type="radio" id="12" name="example5"><label
						for="12">名前</label></th>
					<th><input type="radio" checked id="13" name="example5"><label
						for="13">学年</label></th>
					<th><input type="radio" checked id="14" name="example5"><label
						for="13">ポイント</label></th>
					<th><input type="radio" id="15" name="example5"><label
						for="14">IN</label></th>
					<th><input type="radio" id="16" name="example5"><label
						for="15">OUT</label></th>
					<th>可否</th>
					<th>連絡</th>
				</tr>
				<%
				for (Time studentBase : studentListBase) {%>
				<tr>
					<td><%=studentBase.getCode()%></td>
					<td><%=studentBase.getName()%></td>
					<td><%=studentBase.getGrade()%></td>
					<td><%=studentBase.getPoint()%></td>
					<td><%=studentBase.getTimeIn()%></td>
					<td><%=studentBase.getTimeOut()%></td>
					<td><%=studentBase.getSend()%></td>
					<td><%=studentBase.getMailMsg()%><br><%=studentBase.getGc()%></td>
				</tr>
				<%
				}
				%>
				</tbody>
			</table>
		</div>
		<%
			} else {
			%>
		<p>ーー生徒リストは空ですーー</p>
		<%
			}
			%>
		<table  id="buttonStyle" >
			<tr>
			<td>　　　</td>
			<td><input type="button" onclick="history.back()" value="戻る"></td>
			<td><input type="submit" name="menue"  value="メニュー" formaction="/access1/Menue1Servlet"></td>
			<td><input type="submit" name="botton" value="管理画面へ" formaction="/access1/Mode2Servlet"></td>
			<td>　　　</td>
		</tr>
		</table>

	</form>

</body>
</html>
