<%@page import="clothes.cloBean"%>
<%@page import="java.util.List"%>
<%@page import="clothes.cloDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/front.css" rel="stylesheet" type="text/css">
<link href="../main/mainslide.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@100;200;300;400&family=Noto+Sans+KR:wght@100;300;400;500&display=swap" rel="stylesheet">
</head>
<body>
<div id="wrap">
<!-- 헤더파일들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더파일들어가는 곳 -->
<!-- 메인이미지 들어가는곳 -->
<div class="clear"></div>

<div id="main_img">
<div class="slide">
    <ul>
      <li><img src="../images/woollylogo1.jpg"></li>
      <li><img src="../images/woollylogo2.jpg"></li>
      <li><img src="../images/woollylogo3.jpg"></li>
      <li><img src="../images/woollylogo4.jpg"></li>
    </ul>
    </div>
  </div>
</div>
 
 <div class="font">NEW ARRIVALS</div>
 <div class="main-container">
 <%
 cloDAO cdao = new cloDAO();
 
 int startRow = 1;
 int pageSize = 12;
 
 List<cloBean> cbList = cdao.getBoardList(startRow, pageSize);
 for(int i = 0; i < cbList.size(); i++) {
		cloBean cb = cbList.get(i);
	%>
	<div>
	<a href="../clothes/cloContent.jsp?num=<%=cb.getNum()%>"><img src="../upload/<%=cb.getImage() %>" width="330" height="330"></a><br>
	<div class="pname"><%=cb.getPname() %> <br></div>
	<div class="price"><%=cb.getPrice() %>원</div>
	</div>
	    <%
	}
 %>
</div> 
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터 들어가는 곳 -->
</body>
</html>