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
<link href="../css/walkList.css" rel="stylesheet" type="text/css">
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
<!-- <li><a href="#">Notice</a></li> -->
<!-- <li><a href="#">Public News</a></li> -->
<!-- <li><a href="#">Driver Download</a></li> -->
<!-- <li><a href="#">Service Policy</a></li> -->
<!-- </ul> -->
<!-- </nav> -->
<!-- 왼쪽메뉴 -->

<!-- 게시판 -->

<%
walkDAO wdao = new walkDAO();

int count = wdao.getBoardCount();

int pageSize = 16;
//현페이지 번호 파라미터 가져오기
String pageNum = request.getParameter("pageNum");

if(pageNum == null) {
	pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage-1)*pageSize+1;
int endRow = currentPage*pageSize;


List<walkBean> wbList = wdao.getBoardList(startRow, pageSize);

%>

<!-- <article> -->
<h1>WALK</h1>
<h3>상품 <%=count %>개</h3>
<div class="clo-container">
    <%
    for(int i=0; i<wbList.size(); i++){
    	walkBean wb = wbList.get(i);
    	%>
<div onclick="location.href='walkContent.jsp?num=<%=wb.getNum()%>'"> <br>
<img src="../upload/<%=wb.getImage() %>" width="325" height="325"> <br><br>
<div class="pname"><%=wb.getPname() %> <br></div>
<div class="price"><%=wb.getPrice() %>원</div>
</div>
    	<%
    }
    %>
</div>

<div id="table_search">
<form action="walkSearch.jsp" method="get">
<input type="text" name="search" class="input_box">
<input type="submit" value="검색" class="btn1">
<!-- </div> -->

<!-- <div id="table_search"> -->
<%
String id = (String)session.getAttribute("id");
// id 세션값이 있으면  글쓰기 버튼 보이기
if(id != null) {
if(id.equals("admin")){
	%>
<input type="button" value="글쓰기" class="btn2" 
                       onclick="location.href='walkWriteForm.jsp'">	
	<%
	}
}
%>
</form>
</div>
<div class="clear"></div>
<div id="page_control">
<%
int pageBlock = 10;
int startPage = ((currentPage-1) /pageBlock)*pageBlock + 1;
int endPage = startPage+pageBlock-1;
int pageCount=count/pageSize+(count%pageSize==0?0:1);

if(endPage > pageCount) {
	endPage = pageCount;
}
if(startPage > pageBlock){
	%><a href="walkList.jsp?pageNum=<%=startPage-pageBlock%>">이전</a><%
}
for(int i=startPage; i<=endPage; i++){
	%><a href="walkList.jsp?pageNum=<%=i%>"><%=i %></a><%
}
if(endPage < pageCount){
	%><a href="walkList.jsp?pageNum=<%=startPage+pageBlock%>">다음</a><%
}
%>
</div>
<!-- </article> -->
<!-- 게시판 -->
<!-- 본문들어가는 곳 -->
<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>