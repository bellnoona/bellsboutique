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
<!--[if lt IE 9]>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/IE9.js" type="text/javascript"></script>
<script src="http://ie7-js.googlecode.com/svn/version/2.1(beta4)/ie7-squish.js" type="text/javascript"></script>
<script src="http://html5shim.googlecode.com/svn/trunk/html5.js" type="text/javascript"></script>
<![endif]-->
<!--[if IE 6]>
 <script src="../script/DD_belatedPNG_0.0.8a.js"></script>
 <script>
   /* EXAMPLE */
   DD_belatedPNG.fix('#wrap');
   DD_belatedPNG.fix('#main_img');   

 </script>
 <![endif]-->
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->

<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 왼쪽메뉴 -->
<!-- <nav id="sub_menu"> -->
<!-- <ul> -->
<!-- <li><a href="../center/notice.jsp">Notice</a></li> -->
<!-- <li><a href="#">Public News</a></li> -->
<!-- <li><a href="../center/fnotice.jsp">Driver Download</a></li> -->
<!-- <li><a href="#">Service Policy</a></li> -->
<!-- </ul> -->
<!-- </nav> -->
<!-- 왼쪽메뉴 -->

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
walkDAO wdao = new walkDAO();
// BoardBean bb=  getBoard(num);
walkBean wb = wdao.getBoard(num);
%>
<!-- <article> -->
<h1>상품정보수정</h1>
<form action="walkUpdatePro.jsp" method="post" enctype="multipart/form-data">
<input type="hidden" name="num" value="<%=num%>">
<table class="table">
<tr><th>비밀번호</th><td><input type="password" name="pass"></td></tr>
<tr><th>상품 이름</th><td><input type="text" name="pname" value="<%=wb.getPname()%>"></td></tr>
<tr><th>상품 가격</th><td><input type="text" name="price" value="<%=wb.getPrice()%>"></td></tr>
<tr><th>제조사</th><td><input type="text" name="factory" value="<%=wb.getFactory()%>"></td></tr>
<tr><th>상품 이미지</th>
 <td><input type="file" name="image" ><%=wb.getImage()%>
      <input type="hidden" name="oldimage" value="<%=wb.getImage()%>"></td></tr>
</table>
<div id="table_search">
<input type="button" value="글목록" class="btn1" 
              onclick="location.href='walkList.jsp'">
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