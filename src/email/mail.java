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
		String sender = "tngusglaso@naver.com"; //�����»�� �����ּ�. ex) mailSender@naver.com
		String subject = "[BellsBoutique] " + name + "���� ������ ���ϵ帳�ϴ�."; //���� ����
		String body = name + "���� BellsBoutique ȸ�������� ���ϵ帳�ϴ�!\n"
				+ "�پ��� ��ǰ������ �Բ� ������ ���������� ������ �Ͻ� �� �ֵ���\n"
				+ "�ּ��� ���ϴ� BellsBoutique�� �ǰڽ��ϴ�.\n�����մϴ�."; //���� ����
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			String un=userId;
			String pw=userPw;
			
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(un, pw);
			}
		});
		session.setDebug(false); //Debug ��� ����.
		
		Message mimeMessage = new MimeMessage(session);
		mimeMessage.setFrom(new InternetAddress(sender)); 
		
// �޴� ��� �̸����ּ� ����
		InternetAddress[] toAddr = new InternetAddress[1];
		toAddr[0] = new InternetAddress(email);
		
		mimeMessage.setRecipients(Message.RecipientType.TO, toAddr ); //������ ����
		
		mimeMessage.setSubject(subject); //���� ����
		mimeMessage.setText(body); //���� ����
		
//���� �߼�
		Transport.send(mimeMessage);
		
		System.out.println("���� �߼� ����!");
	} catch (Exception e) {
		System.out.println("���Ϻ����� ���� : "+ e.getMessage());
	}
	
}

}

