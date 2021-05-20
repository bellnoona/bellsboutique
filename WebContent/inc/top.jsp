<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="../css/dropdown.css" rel="stylesheet" type="text/css">
<header>

<div class="clear"></div>
<!-- 로고들어가는 곳 -->
<div id="logo"><a href="../main/main.jsp"><img src="../images/logo.jpg" width="250" height="250" alt="logo"></a></div>
<!-- 로고들어가는 곳 -->
<nav id="top_menu">
<div id="header-dropdown">
<ul>
	<li>
	<a href="#">SHOP</a>
		<ul>
            <li><a href="../clothes/cloList.jsp">CLOTHES</a></li>
            <li><a href="../walk/walkList.jsp">WALK</a></li>
            <li><a href="../living/livList.jsp">LIVING</a></li>
<!--             <li><a href="#">ACC</a></li> -->
        </ul>   
	</li>
		
	<li><a href="../center/fnotice.jsp">NOTICE</a></li>
	<li><a href="../review/fnotice.jsp">REVIEW</a></li>
	<li><a href="../qna/fnotice.jsp">Q&A</a></li>
	<li><a href="../company/welcome.jsp">CONTACT</a></li>
</ul>

<div id="menu-right">
<ul>
<%
// String id = 세션값 가져오기
String id = (String)session.getAttribute("id");

// if 세션값이  없으면 로그인 안 한 상태 => login join 보이기
//              있으면 로그인 성공 => ..님  logout  회원정보수정
if(id == null) {
	%>
<!-- <div id="login"> -->

<li><a href="../main/main.jsp">HOME</a></li>
<li><a href="../member/login.jsp">LOGIN</a></li>
<li><a href="../member/join.jsp">JOIN</a></li>
<!-- </div> -->
	<%
} else {
	%>
<!-- <div id="login"> -->
<li><a href="../main/main.jsp">HOME</a></li>
<li><a href="../member/logout.jsp">LOGOUT</a></li>
<li><a href="../member/updateForm.jsp">MY PAGE</a></li>
<!-- </div> -->
	<%	
}
%>
</ul>
</div>

</div>
</nav>
</header>
