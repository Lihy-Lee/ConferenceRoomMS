package com.meetingroom.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Properties;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 发送验证码到邮箱的类
 * 
 * @author LHY
 *
 */
public class EmailUtil {
	private static String codeNum = null;

	/**
	 * 生成随机码值，包含数字、大小写字母
	 * 
	 * @param number
	 *            生成的随机码位数
	 * @return
	 */
	public static String getRandomCode(int number) {
		String codeNum = "";
		int[] code = new int[3];
		Random random = new Random();
		for (int i = 0; i < number; i++) {
			int num = random.nextInt(10) + 48;
			int uppercase = random.nextInt(26) + 65;
			int lowercase = random.nextInt(26) + 97;
			code[0] = num;
			code[1] = uppercase;
			code[2] = lowercase;
			codeNum += (char) code[random.nextInt(3)];
		}
		System.out.println(codeNum);

		return codeNum;
	}

	/**
	 * 系统发送邮件使用的方法
	 * 
	 * @param email
	 *            待发送的邮箱
	 * @param message
	 *            发送的内容
	 * @param type
	 *            发送类型 (0验证码,1会议提醒,2会议取消提醒)
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendEmails(String email, String message, int type)
			throws GeneralSecurityException, UnsupportedEncodingException, MessagingException {
		if (email == null) {
			return;
		}

		Properties properties = System.getProperties();

		// 基本参数配置
		properties.setProperty("mail.smtp.localhost", "smtp.qq.com");// 发送邮件类型
		properties.put("mail.smtp.auth", "true");
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.ssl.socketFactory", sf);
		Session session = Session.getInstance(properties);
		MimeMessage msg = new MimeMessage(session);

		// 发送方邮箱，标题，编码格式
		InternetAddress receive = new InternetAddress("1582012932@qq.com", "MRMS系统邮件", "utf-8");
		msg.setFrom(receive);

		String title = null;

		if (type == 0) {
			title = "邮箱验证";
			// 接收方邮箱，标题
			msg.setRecipient(RecipientType.TO, new InternetAddress(email, title));
			msg.setSubject("用户邮箱验证");// 首标题
			msg.setText(message);// 内容
			// msg.setText("您的邮箱验证码为:" + codeNum);// 内容
		} else if (type == 1) {
			title = "会议提醒";
			msg.setRecipient(RecipientType.TO, new InternetAddress(email, title));
			msg.setSubject("会议参与提醒");// 首标题
			msg.setText(message);
			// msg.setText("您的邮箱验证码为:" + codeNum);// 内容
		} else if (type == 2) {
			title = "会议取消提醒";
			msg.setRecipient(RecipientType.TO, new InternetAddress(email, title));
			msg.setSubject("会议取消提醒");// 首标题
			msg.setText(message);
			// msg.setText("您的邮箱验证码为:" + codeNum);// 内容
		}

		msg.saveChanges();
		Transport trans = session.getTransport("smtp");
		trans.connect("smtp.qq.com", "1582012932@qq.com", "uofzdhivqbkwgcdj");// 发送方邮件，验证码
		trans.sendMessage(msg, msg.getAllRecipients());// 发送
		trans.close();

	}

	/**
	 * 用户邮箱验证时使用的方法
	 * 
	 * @param email
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 * @throws MessagingException
	 */
	public static void sendVerifyCode(String email)
			throws GeneralSecurityException, UnsupportedEncodingException, MessagingException {

		codeNum = getRandomCode(5);// 自动生成一个5位的包含大小写字母、数字的验证码
		String message = "您的验证码为:" + codeNum;
		sendEmails(email, message, 0);
	}

	/**
	 * 判断验证码是不是正确
	 * 
	 * @param code
	 * @return
	 */
	public static Boolean confirmVerifyCode(String code) {
		boolean flag = code.equalsIgnoreCase(codeNum);
		System.out.println("输入验证码:" + code);
		System.out.println("生成验证码:" + codeNum);
		System.out.println("判断结果:" + flag);
		return flag;
	}

}
