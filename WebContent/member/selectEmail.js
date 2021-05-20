function checkEmail() {
	const target = document.getElementById('emailSelect');
	const textBox = document.getElementById('email');
//	const textBox = document.querySelector("#email1");  // 아이디로 가져오고 싶으면 앞에 #을, 클래스는 . 을 쓰면 됩니다.

	textBox.value = textBox.value.split("@")[0] + '@' + target.value;
}
