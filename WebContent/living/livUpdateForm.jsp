<%@page import="living.livBean"%>
<%@page import="living.livDAO"%>
<%@page import="walk.walkBean"%>
<%@page import="walk.walkDAO"%>
<%@page import="java.util.List"%>
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
livDAO ldao = new livDAO();
// BoardBean bb=  getBoard(num);
livBean lb = ldao.getBoard(num);
%>
<!-- <article> -->
<h1>상품정보수정</h1>
<form action="livUpdatePro.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="num" value="<%=num%>">
<table class="table">
<tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
<tr><th>상품 이름</th><td><input type="text" name="pname" value="<%=lb.getPname()%>"></td></tr>
<tr><th>상품 가격</th><td><input type="text" name="price" value="<%=lb.getPrice()%>"></td></tr>
<tr><th>제조사</th><td><input type="text" name="factory" value="<%=lb.getFactory()%>"></td></tr>
<tr><th>상품 이미지</th>
 <td><input type="file" name="image" ><%=lb.getImage()%>
      <input type="hidden" name="oldimage" value="<%=lb.getImage()%>"></td></tr>
</table>
<div id="table_search">
<input type="button" value="글목록" class="btn1" 
              onclick="location.href='livList.jsp'">
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