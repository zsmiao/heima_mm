package xyz.itclay.heima_mm.utils.mail;
/**
 * 简单邮件（不带附件的邮件）发送器
 */


import xyz.itclay.heima_mm.utils.DateTimeUtil;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件
 *
 * @author ZhangSenmiao
 * @date 2021/3/9 8:50
 **/
public class SimpleMailSender {
    /**
     * 以文本格式发送邮件
     *
     * @param mailInfo 待发送的邮件的信息
     */
    public boolean sendTextMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        if (mailInfo.isValidate()) {
            // 如果需要身份认证，则创建一个密码验证器
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);

        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // 设置邮件消息的主要内容
            String mailContent = mailInfo.getContent();
            mailMessage.setText(mailContent);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    /**
     * 以HTML格式发送邮件
     *
     * @param mailInfo 待发送的邮件信息
     */
    public static boolean sendHtmlMail(MailSenderInfo mailInfo) {
        // 判断是否需要身份认证
        MyAuthenticator authenticator = null;
        Properties pro = mailInfo.getProperties();
        //如果需要身份认证，则创建一个密码验证器
        if (mailInfo.isValidate()) {
            authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());
        }
        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro, authenticator);
        try {
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(mailInfo.getFromAddress());
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            // 创建邮件的接收者地址，并设置到邮件消息中
            Address to = new InternetAddress(mailInfo.getToAddress());
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipient(Message.RecipientType.TO, to);
            // 设置邮件消息的主题
            mailMessage.setSubject(mailInfo.getSubject());
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
            mainPart.addBodyPart(html);
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        //这个类主要是设置邮件   
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setMailServerHost("smtp.office365.com");
        mailInfo.setMailServerPort("587");
        mailInfo.setValidate(true);
        mailInfo.setUserName("zsmiao2020@outlook.com");
        mailInfo.setPassword("conquest195498SM");
        mailInfo.setFromAddress("zsmiao2020@outlook.com");
        mailInfo.setToAddress("1984897966@qq.com");
//        mailInfo.setToAddress("zsmiao2020@outlook.com");
        mailInfo.setSubject("这是测试邮件");
        mailInfo.setContent("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<title></title>\n" +
                "<style>\n" +
                ".qmbox {\n" +
                "\tpadding: 0;\n" +
                "}\n" +
                ".qm_con_body_content {\n" +
                "\theight: auto;\n" +
                "\tmin-height: 100px;\n" +
                "\t_height: 100px;\n" +
                "\tword-wrap: break-word;\n" +
                "\tfont-size: 14px;\n" +
                "\tfont-family: \"lucida Grande\", Verdana, \"Microsoft YaHei\";\n" +
                "}\n" +
                ".body {\n" +
                "\tline-height: 170%;\n" +
                "}\n" +
                "BODY {\n" +
                "\tfont-family: \"lucida Grande\", Verdana, \"Microsoft YaHei\";\n" +
                "\tfont-size: 12px;\n" +
                "\t-webkit-font-smoothing: subpixel-antialiased;\n" +
                "}\n" +
                "BODY {\n" +
                "\tmargin: 0;\n" +
                "\tpadding: 0;\n" +
                "}\n" +
                "BODY {\n" +
                "\tbackground-color: #fff;\n" +
                "\tfont-size: 12px;\n" +
                "}\n" +
                "BODY {\n" +
                "\tbackground: #fff;\n" +
                "}\n" +
                "BODY {\n" +
                "\tbackground: #fff;\n" +
                "\tcolor: #000;\n" +
                "\tfont-weight: normal;\n" +
                "\tfont-family: \"lucida Grande\", Verdana, \"Microsoft YaHei\";\n" +
                "\tpadding: 0 7px 6px 4px;\n" +
                "\tmargin: 0;\n" +
                "}\n" +
                "HTML {\n" +
                "\ttop: 0px;\n" +
                "}\n" +
                ".body P {\n" +
                "\tline-height: 170%;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<BODY mu=\"mu\" md=\"md\" module=\"qmReadMail\" context=\"ZC1912-rQ7uXSy7P7HThxdLFCOuY92\"><DIV class=mailcontainer id=qqmail_mailcontainer>\n" +
                "<DIV id=mainmail style=\"MARGIN-BOTTOM: 12px; POSITION: relative; Z-INDEX: 1\">\n" +
                "<DIV class=body id=contentDiv style=\"FONT-SIZE: 14px; HEIGHT: auto; POSITION: relative; ZOOM: 1; PADDING-BOTTOM: 10px; PADDING-TOP: 15px; PADDING-LEFT: 15px; Z-INDEX: 1; LINE-HEIGHT: 1.7; PADDING-RIGHT: 15px\" onmouseover=getTop().stopPropagation(event); onclick=\"getTop().preSwapLink(event, 'html', 'ZC1912-rQ7uXSy7P7HThxdLFCOuY92');\">\n" +
                "<DIV id=qm_con_body>\n" +
                "<DIV class=\"qmbox qm_con_body_content qqmail_webmail_only\" id=mailContentContainer>\n" +
                "<DIV class=main style=\"OVERFLOW: hidden; WIDTH: 100%; BACKGROUND-COLOR: #f7f7f7\">\n" +
                "<DIV class=content style=\"BORDER-TOP: #cccccc 1px solid; BORDER-RIGHT: #cccccc 1px solid; BACKGROUND: #ffffff; BORDER-BOTTOM: #cccccc 1px solid; PADDING-BOTTOM: 10px; PADDING-TOP: 10px; PADDING-LEFT: 25px; BORDER-LEFT: #cccccc 1px solid; MARGIN: 50px; PADDING-RIGHT: 25px\">\n" +
                "<DIV class=header style=\"MARGIN-BOTTOM: 30px\">\n" +
                "<P>亲爱的用户：</P></DIV>\n" +
                "<P>您好！您正在进行邮箱验证，本次请求的验证码为：</P>\n" +
                "<P><SPAN style=\"FONT-SIZE: 18px; FONT-WEIGHT: bold; COLOR: #f90\">" + 123422 + "</SPAN><SPAN style=\"COLOR: #000000\">(为了保障您帐号的安全性，请在10分钟内完成验证)</SPAN></P>\n" +
                "<DIV class=footer style=\"MARGIN-TOP: 30px\">\n" +
                "<P>" + "黑马面面项目实战" + "</P>\n" +
                DateTimeUtil.getDateTimeStr() +
                "<P><SPAN style=\"BORDER-BOTTOM: #ccc 1px dashed; POSITION: relative; _display: inline-block\" t=\"5\" times=\"\" isout=\"0\"></SPAN></P></DIV>\n" +
                "<DIV class=tip style=\"COLOR: #cccccc; TEXT-ALIGN: center\">该邮件为系统自动发送，请勿进行回复 </DIV></DIV></DIV></DIV></DIV></DIV></DIV></DIV></BODY>\n" +
                "</html>\n");
        //这个类主要来发送邮件

        SimpleMailSender sms = new SimpleMailSender();
//	     sms.sendTextMail(mailInfo);//发送文体格式
        sms.sendHtmlMail(mailInfo);//发送html格式

    }
}

