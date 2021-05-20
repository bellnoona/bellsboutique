<%@page import="email.mail"%>
<%@page import="member.MemberDAO"%>
<%@page import="member.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// member/joinPro.jsp
// join.jsp를 보고싶다고 요청하여 오면서
// http에 회원가입 파라미터값을 들고 서버로 옴
// 서버 WAS 처리 들고온 정보를 request내장객체에 저장 
// request 한글처리
request.setCharacterEncoding("utf-8");

// id,pass,name,email,mobile,phone,postcode,address,detailAddress
// 가져와서 변수에 저장
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");
String email = request.getParameter("email");
String mobile = request.getParameter("mobile");
String phone = request.getParameter("phone");
String postcode = request.getParameter("postcode");
String address = request.getParameter("address");
String detailAddress = request.getParameter("detailAddress");

// 회원정보를 하나의 바구니(자바빈) 안에 담기
// 패키지 member 파일이름 MemberBean
// 멤버변수 정의 -> set() get() 메서드 정의

// MemberBean mb 객체생성 : 클래스를 사용하기 위해서 기억장소 할당
// mb : 바구니 주소
MemberBean mb = new MemberBean();

// set()메서드 호출 -> 파라미터값 저장
mb.setId(id);
mb.setPass(pass);
mb.setName(name);
mb.setEmail(email);
mb.setMobile(mobile);
mb.setPhone(phone);
mb.setPostcode(postcode);
mb.setAddress(address);
mb.setDetailAddress(detailAddress);

// 디비작업
// 패키지 member 파일이름 MemberDAO
// MemberDAO 객체생성
MemberDAO mdao = new MemberDAO();

// insertMember(mb) 메서드 정의(리턴값 없음), 호출
mdao.insertMember(mb);


   if(mdao.idCheck(id) >= 2 && mdao.passCheck(pass) >= 3) { %>
<script>
	alert("환영합니다! 회원가입에 성공하였습니다.");
	location.href = "login.jsp";
</script> <%
   mail m = new mail(); // 자바코드있는 클래스(mail) 객체 생성
   m.mailSend(email, name); // 클래스 안에 있는 메일보내기 메서드(sendMail) 연결해서 email 파라미터로 전달
   } else { %>
   <script>
	 alert("회원정보를 다시 확인해주세요.");
	 location.href = "join.jsp";
   </script>
 <%  }
// "회원가입 성공" login.jsp로 이동


%>