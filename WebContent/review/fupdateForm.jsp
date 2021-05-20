<%@page import="review.revBean"%>
<%@page import="review.revDAO"%>
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
<link href="../css/newWriteForm.css" rel="stylesheet" type="text/css">
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
//  게시판 글가져오기 
// updateForm.jsp?num=<%=bb.getNum()
int num=Integer.parseInt(request.getParameter("num"));
// BoardDAO bdao 객체생성
revDAO rdao=new revDAO();
// BoardBean bb=  getBoard(num);
revBean rb=rdao.getBoard(num);
%>
<!-- <article> -->
<h1>REVIEW</h1>
<form action="fupdatePro.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="num" value="<%=num%>">
<table class="table">
<tr><th>작성자</th>
<td><input type="text" name="name" value="<%=id%>" readonly></td>
<tr><th>비밀번호</th>
<td><input type="password" name="pass"></td>
<tr><th>글제목</th>
<td><input type="text" name="subject" value="<%=rb.getSubject()%>"></td></tr>
<tr><th>글내용</th>
<td><textarea name="content" rows="25" cols="80"><%=rb.getContent() %></textarea></td></tr>
<tr><th>첨부파일</th>
      <td><input type="file" name="file" ><%=rb.getFile()%>
      <input type="hidden" name="oldfile" value="<%=rb.getFile()%>"></td></tr>      
</table>
<div id="table_search">
<input type="button" value="글목록" class="btn1" 
              onclick="location.href='fnotice.jsp'">
<input type="submit" value="글수정" class="btn2">
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