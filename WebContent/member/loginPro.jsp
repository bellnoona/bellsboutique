<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// member/loginPro.jsp
// http가 파라미터 가져와서 서버 request 저장
// request에 저장된 파라미터 가져오기
String id = request.getParameter("id");
String pass = request.getParameter("pass");



// MemberDAO mado 객체 생성
MemberDAO mdao = new MemberDAO();

// int check = userCheck(id,pass) 정의, 호출
int check = mdao.userCheck(id, pass);

// check == 1 아이디,비밀번호 일치 세션값생성"id",id(기준값) -> main.jsp로 이동
// check == 0 "비밀번호 불일치" 뒤로이동
// check == -1 "아이디 없음" 뒤로이동
if(check == 1) {
	session.setAttribute("id", id);
	response.sendRedirect("../main/main.jsp");
} else if(check == 0) {
	%>
	<script>
		alert("비밀번호가 일치하지 않습니다.");
		history.back();
	</script>
	<%
} else {
	%>
	<script>
		alert("아이디가 없습니다.");
		history.back();
	</script>
	<%
}


%>