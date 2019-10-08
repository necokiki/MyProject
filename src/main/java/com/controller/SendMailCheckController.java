
package com.controller;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberADTO;
import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class SendMailCheckController {

	@Autowired
	MemberService service;

	@RequestMapping("/sendMailCheck")
	public String sendMailCheck(HttpSession session, MemberDTO dto) {

		dto = (MemberDTO) session.getAttribute("mailCheck");

		String mailTo = dto.getM_email1();
		String mailTo2 = dto.getM_email2();
		String userid = dto.getUserid();

		System.out.println("메이리일:" + dto);

		String host = "smtp.naver.com";
		String subject = "네이버를 이용한 메일발송";
		String from = "s_jh971205@naver.com"; // 보내는 메일
		String fromName = "Test";
		String to = mailTo + "@" + mailTo2; // 받는 메일
		String content = "See-N 아이디:" + userid;

		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Authenticator auth = new SendMailController();
			Session mailSession = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); // 받는 사람설정

			msg.setSubject(subject); // 제목설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=UTF-8"); // 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}

		return "loginUI";

	}

	@RequestMapping("/sendMailCheck_art")
	public String sendMailCheck_art(HttpSession session, MemberADTO dto) {

		dto = (MemberADTO) session.getAttribute("mailCheck");

		String mailTo = dto.getA_email1();
		String mailTo2 = dto.getA_email2();
		String userid = dto.getArtistname();

		System.out.println("메이리일:" + dto);

		String host = "smtp.naver.com";
		String subject = "네이버를 이용한 메일발송";
		String from = "s_jh971205@naver.com"; // 보내는 메일
		String fromName = "Test";
		String to = mailTo + "@" + mailTo2; // 받는 메일
		String content = "See-N 작가명:" + userid;

		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Authenticator auth = new SendMailController();
			Session mailSession = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); // 받는 사람설정

			msg.setSubject(subject); // 제목설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=UTF-8"); // 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}

		return "loginUI";

	}

	@RequestMapping("/matching")

	public String mailcheck(MemberDTO dto, HttpSession session, @RequestParam HashMap<String, String> map,
			@RequestParam("m_username") String m_username, @RequestParam("m_email1") String m_email1,
			@RequestParam("m_email2") String m_email2) {

		map.put("m_username", m_username);
		map.put("m_email1", m_email1);
		map.put("m_email2", m_email2);
		System.out.println("좋은말할때 나와:" + map);

		dto = service.mailCheck(map);
		System.out.println("DTO나와:" + dto);

		if (dto == null) {
			session.setAttribute("notmatch", "이름과 이메일이 동일하지 않습니다.");
			return "mailUI";
		} else {
			session.setAttribute("match", "회원님의 아이디가 전송되었습니다.");
			session.setAttribute("mailCheck", dto);
			return "redirect:sendMailCheck";
		}

	}

	@RequestMapping("/matching_art")

	public String mailcheck_art(MemberADTO dto, HttpSession session, @RequestParam HashMap<String, String> map,
			@RequestParam("a_username") String a_username, @RequestParam("a_email1") String a_email1,
			@RequestParam("a_email2") String a_email2) {

		map.put("a_username", a_username);
		map.put("a_email1", a_email1);
		map.put("a_email2", a_email2);
		System.out.println("좋은말할때 나와:" + map);

		dto = service.mailCheck_art(map);
		System.out.println("DTO나와:" + dto);

		if (dto == null) {
			session.setAttribute("notmatch", "이름과 이메일이 동일하지 않습니다.");
			return "mailUI";

		} else {
			session.setAttribute("match", "회원님의 아이디가 전송되었습니다.");
			session.setAttribute("mailCheck", dto);
			return "redirect:sendMailCheck_art";
		}

	}

/////여기부터 비번찾기
	public static String getRamdomPassword(int len) {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		int idx = 0;
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < len; i++) {
			idx = (int) (charSet.length * Math.random());

			sb.append(charSet[idx]);
		}

		return sb.toString();
	}

	String m_passwd = getRamdomPassword(10);

	@RequestMapping("/passwdSearch")
	public String passwdSearch(RedirectAttributes attr,HttpSession session, @RequestParam("m_email1") String m_email1,
			@RequestParam("m_email2") String m_email2, @RequestParam("m_username") String m_username) {
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("m_email1", m_email1);
		map.put("m_email2", m_email2);
		map.put("m_passwd", m_passwd);
		map.put("m_username", m_username);
	
	
		
		service.passwdchange(map);
	
		MemberDTO dto = service.mailCheck(map);
	
		String nextP = null;

		if (dto == null) {
			System.out.println("if");
			nextP = "mailUI";
			session.setAttribute("notmatch", "이름과 이메일이 동일하지 않습니다.");
		} else {
			System.out.println("else");
			session.setAttribute("match", "회원님의 새로운 비밀번호가 전송되었습니다.");
			String mailTo = dto.getM_email1();
			String mailTo2 = dto.getM_email2();
			attr.addAttribute("mailCheck", mailTo);
			attr.addAttribute("mailCheck2", mailTo2);
			attr.addAttribute("m_passwd", m_passwd);
			System.out.println("넘억마");
			nextP = "redirect:newpasswdSend";
			
	
		}

		return nextP;
	}
	
	@RequestMapping("/passwdSearch_art")
	public String passwdSearch_art (RedirectAttributes attr,HttpSession session, @RequestParam("a_email1") String a_email1,
			@RequestParam("a_email2") String a_email2, @RequestParam("a_username") String a_username) {
		
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("a_email1", a_email1);
		map.put("a_email2", a_email2);
		map.put("a_passwd", m_passwd);
		map.put("a_username", a_username);
	
	
		System.out.println("여기까지?");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~"+map);
		service.passwdchange_art(map);
	
		MemberADTO dto = service.mailCheck_art(map);
	
		String nextP = null;

		if (dto == null) {
			System.out.println("if");
			nextP = "mailUI";
			session.setAttribute("notmatch", "이름과 이메일이 동일하지 않습니다.");
		} else {
			System.out.println("else");
			session.setAttribute("match", "회원님의 새로운 비밀번호가 전송되었습니다.");
			String mailTo = dto.getA_email1();
			String mailTo2 = dto.getA_email2();
			attr.addAttribute("mailCheck", mailTo);
			attr.addAttribute("mailCheck2", mailTo2);
			attr.addAttribute("a_passwd", m_passwd);
			System.out.println("넘억마");
			nextP = "redirect:newpasswdSend_art";
			
	
		}

		return nextP;
	}

	@RequestMapping("/newpasswdSend")
	public String newpasswdSend(HttpSession session, @RequestParam("mailCheck") String mailTo,@RequestParam("mailCheck2") String mailTo2,
			@RequestParam("m_passwd") String newpasswd) {
		System.out.println("이 메서드 까지 옴");
		
		String host = "smtp.naver.com";
		String subject = "네이버를 이용한 메일발송";
		String from = "s_jh971205@naver.com"; // 보내는 메일
		String fromName = "Test";
		String to = mailTo + "@" + mailTo2; // 받는 메일
		String content = "See-N의 새로운 비밀번호:" + newpasswd;
		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Authenticator auth = new SendMailController();
			Session mailSession = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); // 받는 사람설정

			msg.setSubject(subject); // 제목설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=UTF-8"); // 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}

		return "loginUI";
	}

	@RequestMapping("/newpasswdSend_art")
	public String newpasswdSend_art(HttpSession session, @RequestParam("mailCheck") String mailTo,@RequestParam("mailCheck2") String mailTo2,
			@RequestParam("a_passwd") String newpasswd) {
		System.out.println("이 메서드 까지 옴");
		
		String host = "smtp.naver.com";
		String subject = "네이버를 이용한 메일발송";
		String from = "s_jh971205@naver.com"; // 보내는 메일
		String fromName = "Test";
		String to = mailTo + "@" + mailTo2; // 받는 메일
		String content = "See-N의 새로운 비밀번호:" + newpasswd;
		try {
			// 프로퍼티 값 인스턴스 생성과 기본세션(SMTP 서버 호스트 지정)
			Properties props = new Properties();
			// 네이버 SMTP 사용시
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.transport.protocol", "smtp");
			props.put("mail.smtp.host", host);

			props.put("mail.smtp.port", "465"); // 보내는 메일 포트 설정
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.debug", "true");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");

			Authenticator auth = new SendMailController();
			Session mailSession = Session.getDefaultInstance(props, auth);

			Message msg = new MimeMessage(mailSession);
			msg.setFrom(new InternetAddress(from, MimeUtility.encodeText(fromName, "UTF-8", "B"))); // 보내는 사람 설정
			InternetAddress[] address = { new InternetAddress(to) };

			msg.setRecipients(Message.RecipientType.TO, address); // 받는 사람설정

			msg.setSubject(subject); // 제목설정
			msg.setSentDate(new java.util.Date()); // 보내는 날짜 설정
			msg.setContent(content, "text/html; charset=UTF-8"); // 내용 설정(MIME 지정-HTML 형식)

			Transport.send(msg); // 메일 보내기

		} catch (MessagingException ex) {
			System.out.println("mail send error : " + ex.getMessage());
			ex.printStackTrace();
		} catch (Exception e) {
			System.out.println("error : " + e.getMessage());
			e.printStackTrace();
		}

		return "loginUI";
	}

}
