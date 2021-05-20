<%@page import="board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// center/fdeletePro.jsp
// num pass name 파라미터 가져오기
int num=Integer.parseInt(request.getParameter("num"));
 String name=request.getParameter("name");
String pass=request.getParameter("pass");
// BoardDAO bdao 객체생성
BoardDAO bdao=new BoardDAO();
// int check=   numCheck(num,pass)
int check=bdao.numCheck(num, pass);
// check ==1  글삭제  deleteBoard(num) notice.jsp 이동
// check ==0  "비밀번호틀림" 뒤로이동
// check == -1 "num없음" 뒤로이동
if(check==1){
	bdao.deleteBoard(num);
	response.sendRedirect("fnotice.jsp");
}else if(check==0){
	%>
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.back();
	</script>
	<%
}else{
	%>
	<script>
		alert("num없음");
		history.back();
	</script>
	<%
}
%>