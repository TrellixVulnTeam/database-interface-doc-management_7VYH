package com.chengjf.jfinaldemo.util;

import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 发�1�7�普通邮件，接受普�1�7�邮仄1�7 发�1�7�带有附件的邮件，接收带有附件的邮件 发�1�7�html形式的邮件，接受html形式的邮仄1�7 发�1�7�带有图片的邮件等做了一个�1�7�结〄1�7
 */
public class EmailSender {

    public static final String EMAIL_BODY_HEADER = "";
    // 邮箱服务噄1�7
    private String host = "smtp.exmail.qq.com";
    private String MAIL_SUBJECT = "测试邮件";
    private String PERSONAL_NAME = "朋也";
    private String username;
    private String password;
    private String mail_from;

    static class EmailSenderHolder {
        static Prop prop = PropKit.getProp("config.properties");
        static EmailSender instance = new EmailSender(prop.get("email.username"), prop.get("email.password"));
    }

    public static EmailSender getInstance() {
        return EmailSenderHolder.instance;
    }

    public EmailSender(String username, String password) {
        this.username = username;
        this.password = password;
        this.mail_from = username;
    }

    /**
     * 此段代码用来发�1�7�普通电子邮仄1�7
     */
    public void send(String subject, String[] mailTo, String mailBody) throws Exception {
        try {
            Properties props = new Properties(); // 获取系统环境
            Authenticator auth = new Email_Autherticator(); // 进行邮件服务器用户认评1�7
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props, auth);
            // 设置session,和邮件服务器进行通讯〄1�7
            MimeMessage message = new MimeMessage(session);
            // message.setContent("foobar, "application/x-foobar"); // 设置邮件格式
            message.setSubject(subject == null?MAIL_SUBJECT:subject); // 设置邮件主题
            message.setText(mailBody); // 设置邮件正文
//			message.setHeader(mail_head_name, mail_head_value); // 设置邮件标题
            message.setSentDate(new Date()); // 设置邮件发�1�7�日朄1�7
            Address address = new InternetAddress(mail_from, PERSONAL_NAME);
            message.setFrom(address); // 设置邮件发�1�7��1�7�的地址
            Address toAddress = null;
            for (int i = 0; i < mailTo.length; i++) {
                toAddress = new InternetAddress(mailTo[i]); // 设置邮件接收方的地址
                message.addRecipient(Message.RecipientType.TO, toAddress);
            }
            toAddress = null;
            Transport.send(message); // 发�1�7�邮仄1�7
            System.out.println("send ok!");
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 用来进行服务器对用户的认评1�7
     */
    public class Email_Autherticator extends Authenticator {
        public Email_Autherticator() {
            super();
        }

        public Email_Autherticator(String user, String pwd) {
            super();
            username = user;
            password = pwd;
        }

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }

    public static void sendMail(String title, String[] mailTo, String content) {
        String mailBody = EMAIL_BODY_HEADER + content;
        try {
            EmailSender.getInstance().send(title, mailTo, mailBody);
        } catch (Exception e) {
            System.out.println("email send error:" + mailBody);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMail(null, new String[]{""}, "测试邮件内容");
    }

}
