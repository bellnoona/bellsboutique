<%@page import="living.livDAO"%>
<%@page import="living.livBean"%>
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
<link href="../css/newContent.css" rel="stylesheet" type="text/css">
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
<!-- 게시판 -->
<%
// content.jsp?num=<%=bb.getNum()
int num = Integer.parseInt(request.getParameter("num"));
// BoardDAO bdao 객체생성
livDAO ldao = new livDAO();
// 조회수 1증가  readcount=readcount+1
// wdao.updateReadcount(num);
// BoardBean bb=  getBoard(num);
livBean lb = ldao.getBoard(num);
%>

<table class="product">
<tr>
   <td rowspan="6" class="image"><img src="../upload/<%=lb.getImage() %>" width="600" height="600"></td>
   <td colspan="2" class="pname"><%=lb.getPname()%></td>
</tr>
<tr>
    <td class="first">판매가</td>
    <td class="price"><%=lb.getPrice()%>원</td>
</tr>
<tr>
    <td class="first">배송비</td>
    <td class="second"> 3,000원/주문시결제(선결제) <br><br><br><br><br>택배</td>
</tr>
<tr>
    <td class="first">제조사</td>
    <td class="second"><%=lb.getFactory()%></td>
</tr>
<tr>
    <td class="first">수량</td>
    <td class="second"><input type="number" min="1" max="10" placeholder="1"> 개 <br><br><br><br>
    *10개까지 구매가능합니다.</td>
</tr>
<tr>
    <td class="button1"><input type="button" value="장바구니" class="btn1"></td>
    <td class="button2"><input type="button" value="바로 구매" class="btn2"></td>
</tr>
</table>

<div id="content-search">
<%
// 글수정, 글삭제  보이기 : 글쓴사람, 로그인사람 일치하면 
// String id 세션값 가져오기 
String id = (String)session.getAttribute("id");
// if 세션값이 있으면 
//  if  세션   글쓴이 비교  일치하면 글수정 글삭제 버튼 보이기
if(id != null){
	if(id.equals("admin")) {
	%>
<input type="button" value="글수정" class="btn1" 
        onclick="location.href='livUpdateForm.jsp?num=<%=lb.getNum()%>'">
<input type="button" value="글삭제" class="btn1" 
        onclick="location.href='livDeleteForm.jsp?num=<%=lb.getNum()%>'">	
	<%
	}
}
%>
<input type="button" value="상품목록으로 돌아가기" class="btn3" 
              onclick="location.href='livList.jsp'">
</div>
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