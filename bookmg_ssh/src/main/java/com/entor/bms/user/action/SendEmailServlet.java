package com.entor.bms.user.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.entor.bms.utils.Authentication;

@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 收件人的电子邮件 ID
		String to = "35773597@qq.com";

		// 发件人的电子邮件 ID
		String from = "laowuandhisfriends@163.com";
		String user = "laowuandhisfriends@163.com";
		String password = "123abc";// 这是163邮箱的授权码

		// 假设您是从本地主机发送电子邮件
		String host = "smtp.163.com";
		String port = "25";

		// 获取系统的属性
		Properties properties = System.getProperties();

		// 设置邮件服务器
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		Authentication authorization = new Authentication(user, password);

		// 获取默认的 Session 对象
		Session session = Session.getDefaultInstance(properties, authorization);

		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// 创建一个默认的 MimeMessage 对象
			MimeMessage message = new MimeMessage(session);
			// 设置 From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// 设置 To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 设置 Subject: header field
			message.setSubject("来自唐上锟的问候");
			// 现在设置实际消息
			message.setText("老吴你好帅啊！老吴你好帅啊！老吴你好帅啊！老吴你好帅啊！老吴你好帅啊！老吴你好帅啊！老吴你好帅啊！");
			// 发送消息
			Transport transport = session.getTransport();
			transport.connect();
			transport.sendMessage(message, message.getAllRecipients());

			out.write("{\"msg\":\"发送成功!\"}");
			out.flush();
		} catch (MessagingException mex) {
			out.write("{\"msg\":\"发送失败!\"}");
			out.flush();
			mex.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
