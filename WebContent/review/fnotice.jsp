<%@page import="clothes.cloDAO"%>
<%@page import="clothes.cloBean"%>
<%@page import="review.revDAO"%>
<%@page import="review.revBean"%>
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
<link href="../css/review.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->
<!-- 본문들어가는 곳 -->
<!-- 게시판 -->

<%
revDAO rdao = new revDAO();

int count = rdao.getBoardCount();

int pageSize = 15;
//현페이지 번호 파라미터 가져오기
String pageNum = request.getParameter("pageNum");

if(pageNum == null) {
	pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage-1)*pageSize+1;
int endRow = currentPage*pageSize;

List<revBean> rbList = rdao.getBoardList(startRow, pageSize);
// 제푸품빈 = 제품dao.getboard(board+num);

// 제품빈.ㅎㄷfile


SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

%>

<!-- <article> -->
<h1>REVIEW</h1>
<table class="table">
<tr><th class="tno">글번호</th>
	<th>제품</th>
    <th class="ttitle">제목</th>
    <th class="twrite">작성자</th>
    <th class="tread">조회수</th>
    <th class="tdate">작성일</th>
</tr>
    <%
    for(int i = 0; i < rbList.size(); i++) {
    	revBean rb = rbList.get(i);
    	cloDAO cdao=new cloDAO();
    	cloBean cb = cdao.getBoard(rb.getBoardNum());
    %>
<tr onclick="location.href='fcontent.jsp?num=<%=rb.getNum()%>'">
  <td><%=rb.getNum() %></td>
  <td><a href="../clothes/cloContent.jsp?num=<%=rb.getBoardNum()%>"><img src="../upload/<%=cb.getImage() %>" width="100" height="100"></a></td>
  <td><%=rb.getSubject() %></td>
  <td><%=rb.getName() %></td>
  <td><%=rb.getReadcount() %></td>
  <td><%=sdf.format(rb.getDate()) %></td>
  </tr>
  <%
    }
   %>
</table>
<div id="table_search">
<form action="noticeSearch.jsp" method="get">
<input type="text" name="search" class="input_box">
<input type="submit" value="검색" class="btn1">
<!-- </div> -->

<!-- <div id="table_search"> -->
<%
String id = (String)session.getAttribute("id");
// id 세션값이 있으면  글쓰기 버튼 보이기
if(id != null){
	%>
<input type="button" value="글쓰기" class="btn2" 
                       onclick="location.href='fwriteForm.jsp'">	
	<%
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
	%><a href="fnotice.jsp?pageNum=<%=startPage-pageBlock%>">이전</a><%
}
for(int i=startPage; i<=endPage; i++){
	%><a href="fnotice.jsp?pageNum=<%=i%>"><%=i %></a><%
}
if(endPage < pageCount){
	%><a href="fnotice.jsp?pageNum=<%=startPage+pageBlock%>">다음</a><%
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