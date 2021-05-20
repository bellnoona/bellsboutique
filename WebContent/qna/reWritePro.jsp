<%@page import="qna.qnaDAO"%>
<%@page import="qna.qnaBean"%>
<%@page import="board.BoardDAO"%>
<%@page import="board.BoardBean"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
</head>
<body>
<h1>board/reWritePro.jsp</h1>
<%
// http 파라미터 가지고 서버 request 내장객체 저장
// request 한글처리
request.setCharacterEncoding("utf-8");

// request 파라미터(name, pass, subject, content) 가져와서 변수 저장
String name = request.getParameter("name");
String pass = request.getParameter("pass");
String subject = request.getParameter("subject");
String content = request.getParameter("content");

// date 글쓴날자
Timestamp date = new Timestamp(System.currentTimeMillis());

// readcount 조회수
int readcount = 0;
// 답글
int num = Integer.parseInt(request.getParameter("num"));
int re_ref = Integer.parseInt(request.getParameter("re_ref"));
int re_lev = Integer.parseInt(request.getParameter("re_lev"));
int re_seq = Integer.parseInt(request.getParameter("re_seq"));

// 게시판 글을 하나의 바구니에 담아서 전달
// 패키지 board 파일 BoardBean
// num, name, pass, subject, content, date, readcount
// 멤버변수 set get메서드 정의

// BoardBean bb 객체생성
qnaBean qb = new qnaBean();
// set메서드 호출 값 저장
qb.setName(name);
qb.setPass(pass);
qb.setSubject(subject);
qb.setContent(content);
qb.setDate(date);
qb.setReadcount(readcount);
qb.setRe_ref(re_ref);
qb.setRe_lev(re_lev);
qb.setRe_seq(re_seq);

// 패키지 board 파일 BoardDAO
qnaDAO qdao = new qnaDAO();
// insertBoard(바구니의 주소 bb) 메서드 정의 호출
qdao.reInsertBoard(qb);


// 게시판 글목록 이동
response.sendRedirect("fnotice.jsp");

%>
</body>
</html>