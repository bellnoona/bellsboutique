<%@page import="living.livDAO"%>
<%@page import="living.livBean"%>
<%@page import="walk.walkDAO"%>
<%@page import="walk.walkBean"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// center/fupdatePro.jsp
//  request이용,  업로드 폴더 /upload 물리적경로
// , 업로드파일크기 10M  ,  한글처리 , 파일이름 동일하면 이름변경
//  MultipartRequest 객체생성
String uploadPath=request.getRealPath("/upload");
int maxSize=10*1024*1024;
MultipartRequest multi=new MultipartRequest(request,uploadPath,maxSize,"utf-8",new DefaultFileRenamePolicy());

// request 파라미터 가져와서 변수에 저장
int num=Integer.parseInt(multi.getParameter("num"));
String pass = multi.getParameter("pass");
String pname = multi.getParameter("pname");
String price = multi.getParameter("price");
String factory = multi.getParameter("factory");
// file 
String image = multi.getFilesystemName("image");

if(image==null){
	// 새롭게 수정할 파일이 없으면 기존 파일이름 "oldfile" 저장
	image = multi.getParameter("oldimage");
}

// BoardBean bb 바구니객체생성
livBean lb = new livBean();
// 바구니에 파라미터값 저장

lb.setNum(num);
lb.setPass(pass);
lb.setPname(pname);
lb.setPrice(price);
lb.setFactory(factory);
//file
lb.setImage(image);

// BoardDAO bdao 객체생성
livDAO ldao = new livDAO();
// int check=   numCheck(num,pass)
int check=ldao.numCheck(num, pass);
// check ==1  글수정  updateBoard(bb) notice.jsp 이동
// check ==0  "비밀번호틀림" 뒤로이동
// check == -1 "num없음" 뒤로이동

if(check==1){
	
	ldao.updateBoard(lb);
	response.sendRedirect("livList.jsp");
}else if(check==0){
	%>
	<script>
		alert("비밀번호가 일치하지 않습니다");
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