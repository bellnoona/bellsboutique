<%@page import="member.MemberDAO"%>
<%@page import="member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

request.setCharacterEncoding("utf-8");

String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");
String email = request.getParameter("email");
String mobile = request.getParameter("mobile");
String phone = request.getParameter("phone");
String postcode = request.getParameter("postcode");
String address = request.getParameter("address");
String detailAddress = request.getParameter("detailAddress");

MemberBean mb = new MemberBean();

mb.setId(id);
mb.setPass(pass);
mb.setName(name);
mb.setEmail(email);
mb.setMobile(mobile);
mb.setPhone(phone);
mb.setPostcode(postcode);
mb.setAddress(address);
mb.setDetailAddress(detailAddress);

MemberDAO mdao = new MemberDAO();

int check = mdao.userCheck(id, pass);

if(check == 1) {
	mdao.updateMember(mb);
	%>
	<script>
		alert("회원정보를 수정하였습니다.");
		location.href = "../main/main.jsp";
	</script>
	<%
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
		alert("아이디를 다시 입력해주세요.");
		history.back();
	</script>
	<%
}


%>