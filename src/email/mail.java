package email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {

public static void main(String[] args) {

}

public void mailSend(String email, String name) {
	/*PORT = 25:non-ssl, 465:ssl, 587:tls */
	Properties props = System.getProperties();
	props.put("mail.smtp.host", "smtp.naver.com");
	props.put("mail.smtp.port", 587);
	props.put("defaultEncoding", "utf-8");
	props.put("mail.smtp.auth", "true");
	
	final String userId = "tngusglaso";
	final String userPw = "ejqmfgod37!";
	
	try {
		String sender = "tngusglaso@naver.com"; //보내는사람 메일주소. ex) mailSender@naver.com
		String subject = "[BellsBoutique] " + name + "님의 가입을 축하드립니다."; //메일 제목
		String body = name + "님의 BellsBoutique 회원가입을 축하드립니다!\n"
				+ "다양한 상품정보와 함께 언제나 만족스러운 쇼핑을 하실 수 있도록\n"
				+ "최선을 다하는 BellsBoutique가 되겠습니다.\n감사합니다."; //메일 본문
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un=userId;
			String pw=userPw;
			
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(false); //Debug 모드 설정.
		
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(sender)); 
		
// 받는 사람 이메일주소 세팅
		InternetAddress[] toAddr = new InternetAddress[1];
		toAddr[0] = new InternetAddress(email);
		
		mimeMessage.setRecipients(Message.RecipientType.TO, toAddr ); //수신자 셋팅
		
		mimeMessage.setSubject(subject); //제목 세팅
		mimeMessage.setText(body); //본문 세팅
		
//메일 발송
		Transport.send(mimeMessage);
		
		System.out.println("메일 발송 성공!");
	} catch (Exception e) {
		System.out.println("메일보내기 오류 : "+ e.getMessage());
	}
	
}

}

