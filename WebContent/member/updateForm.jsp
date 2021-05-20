<%@page import="member.MemberBean"%>
<%@page import="member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<link href="../css/newJoinForm.css" rel="stylesheet" type="text/css">
<script src="../member/postcode.js"></script>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../member/selectEmail.js"></script>
</head>
<body>
<div id="join_container">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->
<!-- 본문들어가는 곳 -->
<!-- 본문내용 -->
<% 
//String id = 세션값 가져오기
String id = (String)session.getAttribute("id");

//MemberDAO mdao 객체 생성
MemberDAO mdao = new MemberDAO();

//MemberBean mb = getMember(id) 메서드 만들고 호출 
MemberBean mb = mdao.getMember(id);
%>

<!-- <article> -->
<h1 class="member">회원정보수정</h1>
<!-- <hr id="line"> -->
<form action="updatePro.jsp" id="join" method="post">
<table class="table">
<tr>
 <th>＊아이디</th>
 <td>
<input type="text" name="id" class="id" id="idBox" value="<%=mb.getId() %>" maxlength="20" readonly>
<input type="button" value="중복확인" class="double" onclick="dupCheck()"><br>
 </td>
</tr>

<tr>
<th>＊비밀번호</th>
<td>
<input type="password" name="pass" maxlength="16" required><br>
</td>
</tr>

<tr>
<th>＊비밀번호 확인</th>
<td>
<!-- <input type="password" id="passCheck" name="passCheck" maxlength="16" ><br> -->
</td>
</tr>

<tr>
<th>＊이름</th>
<td>
<input type="text" name="name" value="<%=mb.getName() %>" maxlength="20"><br>
</td>
</tr>

<tr>
<th>＊이메일</th>
<td>
<input type="text" id="email" name="email" class="email" value="<%=mb.getEmail() %>" maxlength="30">

<select id="emailSelect" name="emailSelect" onchange="checkEmail();">
<!-- 	<option selected>선택하세요.</option> -->
	<option value="">직접입력</option> 
	<option value="naver.com">naver.com</option>
	<option value="hanmail.net">hanmail.net</option>
	<option value="daum.net">daum.net</option>
	<option value="gmail.com">gmail.com</option>
	<option value="nate.com">nate.com</option>
	<option value="hotmail.com">hotmail.com</option>
	<option value="icloud.com">icloud.com</option>
</select><br>
</td>
</tr>

<tr>
<th>＊휴대폰번호</th>
<td>
<input type="text" placeholder="- 없이 입력하세요." id="mobile" name="mobile" value="<%=mb.getMobile() %>" maxlength="11" value="<%=mb.getMobile()%>"><br>
</td>
</tr>

<tr>
<th>전화번호</th>
<td>
<input type="text" placeholder="- 없이 입력하세요." id="phone" name="phone" value="<%=mb.getPhone() %>" maxlength="10"><br>
</td>
</tr>

<tr>
<th rowspan="3">주소</th>
<td>
<input type="text" id="postcode" placeholder="우편번호" name="postcode" value="<%=mb.getPostcode() %>">
<input type="button" id="postcodeBox" value="우편번호 검색" onclick="postcodeCheck()"><br>
<input type="text" id="address" placeholder="주소" name="address" value="<%=mb.getAddress()%>"><br>
<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress" value="<%=mb.getDetailAddress() %>">
</td>
</tr>

</table>
<!-- </fieldset> -->
<div class="clear"></div>

<div id="buttons">
<input type="reset" value="취소" class="cancel">
<input type="submit" value="회원정보수정" class="submit">
</div>
</form>
<!-- </article> -->
<!-- 본문내용 -->

<!-- 본문들어가는 곳 -->

<div class="clear"></div>
<!-- 푸터들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터들어가는 곳 -->
</div>
</body>
</html>