<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
<style>
</style>
<script type="text/javascript">
	function ok() {
		// window.opener 변수 : join.jsp에서 window.open(dupcheck.jsp) 창열기
		// dupcheck.jsp 에서 창을 열리게 한 페이지 join.jsp 를 opener변수에 저장
// 		join.jsp 아이디상자 value= dupcheck.jsp에서 찾은 아이디 value 
// 		alert("찾은 아이디 : "+document.wfr.id.value);
		window.opener.document.fr.id.value=document.wfr.id.value;
		// 창닫기
		window.close();
	}
</script>
</head>
<body>
<!-- member/dupcheck.jsp -->
<h2>아이디 중복체크</h2>
<%
// http://localhost:8080/FunWeb/member/dupcheck.jsp?id=dd
// http가 id파라미터 값을 들고 옴 => request 저장
String id=request.getParameter("id");
// MemberDAO mdao 객체생성
MemberDAO mdao=new MemberDAO();
// int check= iddupcheck(id) 메서드 정의 하고 호출
int check=mdao.idDupcheck(id);
// check==1  "아이디 있음" "아이디 중복"
// check==-1 "아이디 없음" "아이디 사용가능"  
if(check==1){
	%>이미 등록된 아이디입니다. 다른 아이디를 입력해주세요.<%
}else{
	%>사용 가능한 아이디입니다. 
	<input type="button" value="아이디 사용" onclick="ok()"><%
}
%>
<form action="dupCheck.jsp" method="get" name="wfr">
아이디 : <input type="text" name="id" value="<%=id%>">
<input type="submit" value="아이디 중복확인">
</form>
<br>
공백 또는 특수문자가 포함된 아이디는 사용할 수 없습니다.<br>
숫자로 시작하거나, 숫자로만 이루어진 아이디는 사용할 수 없습니다.
</body>
</html>