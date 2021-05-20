<%@page import="qna.qnaBean"%>
<%@page import="qna.qnaDAO"%>
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
<link href="../css/newBoard.css" rel="stylesheet" type="text/css">
</head>
<body>
<div id="wrap">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->
<!-- 본문들어가는 곳 -->
<!-- 메인이미지 -->
<!-- 메인이미지 -->
<!-- 게시판 -->

<%
qnaDAO qdao = new qnaDAO();

int count = qdao.getBoardCount();

int pageSize = 15;
//현페이지 번호 파라미터 가져오기
String pageNum = request.getParameter("pageNum");

if(pageNum == null) {
	pageNum = "1";
}

int currentPage = Integer.parseInt(pageNum);
int startRow = (currentPage-1)*pageSize+1;
int endRow = currentPage*pageSize;

List<qnaBean> qbList = qdao.getBoardList(startRow, pageSize);

SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
%>

<!-- <article> -->
<h1>Q&A</h1>
<table class="table">
<tr><th class="tno">글번호</th>
    <th class="ttitle">제목</th>
    <th class="twrite">작성자</th>
    <th class="tread">조회수</th>
    <th class="tdate">작성일</th>
</tr>
    <%
    for(int i = 0; i<qbList.size(); i++) {
    	qnaBean qb = qbList.get(i);
    %>
<tr onclick="location.href='fcontent.jsp?num=<%=qb.getNum()%>'">
  <td><%=qb.getNum() %></td>
  <td>
  	   <%
	   // 답글이면(re_lev>0)  답글이미지 보이기
	   // 흰색이미지  너비 조정
	   // 한번들여쓰기 10px  두번들여쓰기 20px
	   // re_lev 1 10px  re_lev 2  20px
	   int wid=0;
	   if(qb.getRe_lev()>0){
		   wid=qb.getRe_lev()*10;
		   %>
		   <img src="level.gif" width="<%=wid %>" height="15">
		   <img src="Re.jpg" height="15">
		   <%
	   }
	   %>
  <%=qb.getSubject() %></td>
  <td><%=qb.getName() %></td>
  <td><%=qb.getReadcount() %></td>
  <td><%=sdf.format(qb.getDate()) %></td>
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