<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// member/logout.jsp
// 세션값 초기화(전체삭제)
session.invalidate();

// 로그아웃 login.jsp로 이동
%>
	<script>
		alert("로그아웃 되셨습니다.");
		location.href="../main/main.jsp";
	</script>