<%@page import="member.MemberBean"%>
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
<!-- 메인이미지 -->
<div id="sub_img_center"></div>
<!-- 메인이미지 -->

<!-- 게시판 -->
<%
// content.jsp?num=<%=bb.getNum()
int num=Integer.parseInt(request.getParameter("num"));
// BoardDAO bdao 객체생성
BoardDAO bdao=new BoardDAO();
// 조회수 1증가  readcount=readcount+1
bdao.updateReadcount(num);
// BoardBean bb=  getBoard(num);
BoardBean bb=bdao.getBoard(num);

%>
<!-- <article> -->
<h1>Notice</h1>
<table class="table">
<tr>
 <th>글번호</th>
   <td><%=bb.getNum() %></td>
 <th>작성일</th>
   <td><%=bb.getDate() %></td></tr>
<tr>
<th>작성자</th>
   <td><%=bb.getName() %></td>
<th>조회수</th>
   <td><%=bb.getReadcount() %></td></tr>
<tr>
<th>글제목</th>
   <td colspan="3"><%=bb.getSubject() %></td></tr>
<tr>
<tr><th>글내용</th><td colspan="3"><pre class="pre"><%=bb.getContent() %></pre><br>
<%
if(bb.getFile() != null) { 
%>
    <p>  <img src="../upload/<%=bb.getFile() %>" width="700" height="700"><br></p>
</td></tr>
<tr>
<th>첨부파일</th>
      <td colspan="3">
      <a href="../upload/<%=bb.getFile() %>" download><%=bb.getFile() %></a><br>
      </td>
      </tr>
	<%
} else { 
%>
	<tr>
	<th>첨부파일</th>
	      <td colspan="3"></td>
	      </tr>
	      <%
}
%>
</table>

<div id="table_search">
<%
// 글수정, 글삭제  보이기 : 글쓴사람, 로그인사람 일치하면 
// String id 세션값 가져오기 
String id = (String)session.getAttribute("id");
// if 세션값이 있으면 
//  if  세션   글쓴이 비교  일치하면 글수정 글삭제 버튼 보이기
if(id !=null){
	if(id.equals(bb.getName())){
	%>
<input type="button" value="글수정" class="btn1" 
        onclick="location.href='fupdateForm.jsp?num=<%=bb.getNum()%>'">
<input type="button" value="글삭제" class="btn1" 
        onclick="location.href='fdeleteForm.jsp?num=<%=bb.getNum()%>'">	
	<%
	}
}
%>
<input type="button" value="글목록" class="btn1" 
              onclick="location.href='fnotice.jsp'">
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