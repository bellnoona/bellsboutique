function joinCheck() {
	
	const idtext = document.getElementById('idBox');
	const id = idtext.value;
	
	const passtext = document.getElementById('passBox');
	const pass = passtext.value;
	
	const confirmPasstext = document.getElementById('cofirmPass');
	const confirmPass = confirmPasstext.value;
	
	
	
	if(id == '') {
		document.getElementById('message1').innerHTML='아이디를 입력해주세요.';
		idtext.focus();
		return false;
	}
	if(pass == '') {
		document.getElementById('message2').innerHTML='비밀번호를 입력해주세요.';
		passtext.focus();
		return false;
	}
	
	if(confirmPass == '') {
		document.getElementById('message3').innerHTML='비밀번호를 확인해주세요.';
		confirmPasstext.focus();
		return false;
	}
	
}


	function idCheck() {
		const idtext = document.getElementById('idBox');
		const id = idtext.value;
		const regId = /^[a-z][a-z0-9]{5,14}$/;
		
	if(!regId.test(id)) {
		document.getElementById('message1').innerHTML='5-14자의 영문 소문자, 숫자를 조합하세요.(공백, 숫자로 시작 불가)';
		document.getElementById('message1-1').innerHTML='';
		idtext.focus();
	} else {
		document.getElementById('message1').innerHTML='';
		document.getElementById('message1-1').innerHTML='사용가능한 아이디입니다.';
		}
	}
	
	function passwordCheck() {
		const passtext = document.getElementById('passBox');
		const pass = passtext.value;
		const regPass = /[A-Za-z0-9!@#$%]{8,16}/;
		
		if(!regPass.test(pass)) {
			document.getElementById('message2').innerHTML='8-16자의 영문 대/소문자, 숫자, 특수문자를 조합하세요.';
			document.getElementById('message2-1').innerHTML='';
			passtext.focus();
		} else {
			document.getElementById('message2').innerHTML='';
			document.getElementById('message2-1').innerHTML='안전한 비밀번호입니다.';
			}
	}
	
	
	function confirmPassCheck() {
		const confirmPasstext = document.getElementById('confirmPass');
		const confirmPass = confirmPasstext.value;
		// 여기서 함수의 값이 변경된겁니다,,
		
		const passtext = document.getElementById('passBox');
		const pass = passtext.value;
		
	if(confirmPass != pass) {
		document.getElementById('message3').innerHTML='비밀번호가 서로 다릅니다.';
		document.getElementById('message3-1').innerHTML='';
		confirmPasstext.focus();
	} else {
		document.getElementById('message3').innerHTML='';
		document.getElementById('message3-1').innerHTML='비밀번호가 확인되었습니다.';
		}
	}
	