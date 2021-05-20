<%@page import="living.livDAO"%>
<%@page import="living.livBean"%>
<%@page import="walk.walkDAO"%>
<%@page import="walk.walkBean"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//  fwritePro.jsp 
// 업로드 프로그램 설치 cos.jar

//  request이용,  업로드 폴더 /upload 물리적경로
// , 업로드파일크기 10M  ,  한글처리 , 파일이름 동일하면 이름변경
//  MultipartRequest 객체생성
String uploadPath = request.getRealPath("/upload");
int maxSize = 10*1024*1024;
MultipartRequest multi = new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());

 // 파라미터 가져오기 변수에 저장
String pass = multi.getParameter("pass");
String pname = multi.getParameter("pname");
String price = multi.getParameter("price");
String factory = multi.getParameter("factory");
// file 
String image = multi.getFilesystemName("image");
 
 // BoardBean bb 바구니에 객체생성
livBean lb = new livBean();
 //  멤버변수에 파라미터값 저장
lb.setPass(pass);
lb.setPname(pname);
lb.setPrice(price);
lb.setFactory(factory);
//file
lb.setImage(image);

// BoardDAO bdao 객체생성
livDAO ldao = new livDAO();
// insertBoard(bb) 메서드 만들고 호출
ldao.insertBoard(lb);
 
// notice.jsp 이동
response.sendRedirect("livList.jsp");
%>