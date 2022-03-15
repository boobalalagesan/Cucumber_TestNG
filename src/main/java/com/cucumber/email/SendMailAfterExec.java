package com.cucumber.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.cucumber.utils.RunConfig;


public class SendMailAfterExec {

	public void sendMail() {

		System.out.println("=====Sending Mail=====");
		Properties prop=new Properties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		
		
		final String username="alagesanboobal@gmail.com";
		final String password="BooBal123@";

		Session session=Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication(username, password);
			}

		});
	
		final String toRecepiant="boobalalagesan@gmail.com";
		final String ccRecepiant="boobalalagesan@outlook.com";
		Message message =prepareMessage(session, username, toRecepiant, ccRecepiant);
		try {
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("=====Mail Sent=====");
	}
	private static Message prepareMessage(Session session,String username,String toRecepient,String ccRecepient) {
		Message message=new MimeMessage(session);
		try {
			String MsgBody="<p>Hi All, <br> <br> We have executed the automation test cases in current build. Please find the detailed report in the attachment.<br><br>";
			String signature="<br> Please do not reply, This is auto genereated email. <br><br> <p>Thanks,<br> Automation Team.";

			message.setFrom(new InternetAddress(username));
			message.setRecipient(Message.RecipientType.TO,new InternetAddress(toRecepient));
			message.setRecipient(Message.RecipientType.CC,new InternetAddress(ccRecepient));
			message.setSubject("---Test Email--");
			Multipart emailContent=new MimeMultipart();

			MimeBodyPart textBodyPart=new MimeBodyPart();
			textBodyPart.setContent(MsgBody+signature,"text/html");

			MimeBodyPart attachFilePart=new MimeBodyPart();
			attachFilePart.attachFile(RunConfig.REPORT_PATH+".html");
			attachFilePart.setFileName("Test_Report.html");

			emailContent.addBodyPart(attachFilePart);
			emailContent.addBodyPart(textBodyPart);
			message.setContent(emailContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	public static void main(String[] args) {
		SendMailAfterExec sendMailAfterExec=new SendMailAfterExec();
		sendMailAfterExec.sendMail();

	}

}
