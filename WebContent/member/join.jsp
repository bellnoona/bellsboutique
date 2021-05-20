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
<script defer src="joinCheck.js"></script>
<script type="text/javascript">
function dupCheck() {
	if(document.fr.id.value=="") {
		alert("아이디를 입력하세요.");
		document.fr.id.focus();
		return;
	}
	var idvalue = document.fr.id.value;
	window.open("dupCheck.jsp?id="+idvalue,"","width=520,height=280");
}
</script>
</head>
<body>
<div id="join_container">
<!-- 헤더들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더들어가는 곳 -->
<!-- 본문들어가는 곳 -->
<!-- 본문내용 -->
<!-- <article> -->
<h1 class="member">회원가입</h1>
<form action="joinPro.jsp" id="join" method="post" name="fr" onsubmit="return joinCheck()">
<table class="table">
<tr>
 <th>＊아이디</th>
 <td>
<input type="text" name="id" class="id" id="idBox" maxlength="20" onkeydown="idCheck()" required>
<input type="button" value="중복확인" class="double" onclick="dupCheck()"><br>
<span id="message1"></span>
<span id="message1-1"></span>
 </td>
</tr>

<tr>
<th>＊비밀번호</th>
<td>
<!-- <input type="password" id="pass" name="pass" maxlength="16" placeholder="영문 대소문자,숫자,특수문자 조합 8~16자" onkeydown="passCheck()" required> -->
<input type="password" id="passBox" name="pass" maxlength="16" onkeydown="passwordCheck()" placeholder="영문 대소문자 숫자 특수문자 조합 8~16자" required><br>
<span id="message2"></span>
<span id="message2-1"></span>
</td>
</tr>

<tr>
<th>＊비밀번호 확인</th>
<td>
<input type="password" id="confirmPass" name="passCheck" maxlength="16" onkeydown="confirmPassCheck()" required><br>
<span id="message3"></span>
<span id="message3-1"></span>
</td>
</tr>

<tr>
<th>＊이름</th>
<td>
<input type="text" name="name" id="name" maxlength="20" required><br>
</td>
</tr>

<tr>
<th>＊이메일</th>
<td>
<input type="text" id="email" name="email" class="email" maxlength="30" required>

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
<input type="text" placeholder="- 없이 입력하세요." id="mobile" name="mobile" maxlength="11" required><br>
</td>
</tr>

<tr>
<th>전화번호</th>
<td>
<input type="text" placeholder="- 없이 입력하세요." id="phone" name="phone" maxlength="10"><br>
</td>
</tr>

<tr>
<th rowspan="3">주소</th>
<td>
<input type="text" id="postcode" placeholder="우편번호" name="postcode">
<input type="button" id="postcodeBox" value="우편번호 검색" onclick="postcodeCheck()"><br>
<input type="text" id="address" placeholder="주소" name="address"><br>
<input type="text" id="detailAddress" placeholder="상세주소" name="detailAddress">
</td>
</tr>

</table>
<!-- </fieldset> -->
<div class="clear"></div>

<div id="buttons">
<input type="reset" value="취소" class="cancel">
<input type="submit" value="회원가입" class="submit">
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