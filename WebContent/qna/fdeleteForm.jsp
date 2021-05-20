<%@page import="java.text.SimpleDateFormat"%>
<%@page import="board.BoardBean"%>
<%@page import="java.util.List"%>
<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<link href="../css/newDeleteForm.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->
<!-- 본문들어가는 곳 -->

<!-- 게시판 -->
<%
//String id = 세션값 가져오기
String id = (String)session.getAttribute("id");
//if 세션값이 없으면   ../member/login.jsp 이동
if(id==null){
	response.sendRedirect("../member/login.jsp");
}
//deleteForm.jsp?num=<%=bb.getNum()
int num=Integer.parseInt(request.getParameter("num"));
%>
<!-- <article> -->
<h1>작성글 삭제</h1>
<form action="fdeletePro.jsp" method="post">
<input type="hidden" name="num" value="<%=num%>">
<table class="table">
<tr><th>작성자</th>
<td><input type="text" name="name" value="<%=id%>" readonly></td>
<tr><th>비밀번호</th>
<td><input type="password" name="pass"></td>
</table>
<div id="table_search">
<input type="submit" value="글삭제" class="btn2">
<input type="button" value="글목록" class="btn1" 
              onclick="location.href='fnotice.jsp'">
</div>
</form>
<div class="clear"></div>
<div id="page_control">

</div>
<!-- </article> -->
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp" />
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>