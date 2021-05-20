<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BellsBoutique | 감성유니크 반려동물 부티크</title>
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/subpage.css" rel="stylesheet" type="text/css">
<link href="../css/contact.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=0e6deef50f37d64c00c924f013cc0b00"></script>



</head>
<body>
<div id="wrap">
<!-- 헤더가 들어가는 곳 -->
<jsp:include page="../inc/top.jsp"/>
<!-- 헤더가 들어가는 곳 -->

<!-- <article> -->
<h1>CONTACT INFO</h1>
<div id="map" style="width:480px;height:480px;"></div>	
<script>
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(35.158430, 129.062070), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(35.158430, 129.062070); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
// marker.setMap(null);    
 	</script>

<div class="contact-container">
<div class="contact"><img src="../company/emailicon.jpg" width="45px" height="45px">
Email<br>
tngusglaso@naver.com
</div>
<div class="contact"><img src="../company/phoneicon.jpg" width="45px" height="45px">
	Phone<br>
	010.7324.1273<br>051.241.1273
</div>
<div class="contact"><img src="../company/workicon.jpg" width="45px" height="45px">
Work Hour<br>
Weekday AM 9:00 ~ PM 6:00</div>
<div class="contact"><img src="../company/mapicon.jpg" width="45px" height="45px">
위치<br>
부산 부산진구 동천로 109
</div>
<div class="contact"><img src="../company/instagramicon.jpg" width="45px" height="45px">
<a href="https://instagram.com/bellngus" target="_blank" > 벨스부띠끄 인스타그램</a></div>
</div>

<!-- </article> -->
<!-- 내용 -->
<!-- 본문 들어가는 곳 -->

<!-- <div id="map" style="width:100%;height:350px;"></div> -->


<div class="clear"></div>
<!-- 푸터 들어가는 곳 -->
<jsp:include page="../inc/bottom.jsp"/>
<!-- 푸터 들어가는 곳 -->
</div>
</body>
</html>



    