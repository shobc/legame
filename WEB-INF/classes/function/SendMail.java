package function;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail{

    //エンコード指定
    private static final String ENCODE = "Windows-31J";//"ISO-2022-JP";


//    public static void main(String[] args) {
//
//        //メール送付
//        new SendMailTest().send();
//
//    }

//ここからメール送付に必要なSMTP,SSL認証などの設定

    public void send(String mail,String url) {
        final Properties props = new Properties();

        // SMTPサーバーの設定。ここではgooglemailのsmtpサーバーを設定。
        props.put("mail.smtp.host", "smtp.gmail.com");

        // SSL用にポート番号を変更。
        props.put("mail.smtp.port", "587"); //465

        // タイムアウト設定
        props.put("mail.smtp.connectiontimeout", "60000");
        props.put("mail.smtp.timeout", "60000");

        // 認証
        props.put("mail.smtp.auth", "true");
        //Session session = Session.getInstance( props, new myAuth() );

        //追加
        props.put("mail.smtp.starttls.enable" , "true");

        // SSLを使用するとこはこの設定が必要。
        //props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.setProperty("mail.smtp.socketFactory.fallback", "false");
        //props.setProperty("mail.smtp.socketFactory.port", "465");

        //propsに設定した情報を使用して、sessionの作成
        final Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teamlearn.test@gmail.com", "gakuensai3");
            }
        });



        // ここからメッセージ内容の設定。上記で作成したsessionを引数に渡す。
        final MimeMessage message = new MimeMessage(session);

        try {
            final Address addrFrom = new InternetAddress(
                    "teamlearn.test@gmail.com", "チームラーン", ENCODE);//送信者情報
            message.setFrom(addrFrom);

            final Address addrTo = new InternetAddress(mail,
                    mail+"様", ENCODE);//宛先情報
            message.addRecipient(Message.RecipientType.TO, addrTo);

            // メールのSubject
            message.setSubject("認証", ENCODE);

            // メール本文。
            message.setText("urlにアクセスして登録を完了してください。"+url, ENCODE);


            // その他の付加情報。
            message.addHeader("X-Mailerメーラー", "blancoMail 0.1エンジン？");
            message.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // メール送信。
        try {
            Transport.send(message);

        } catch (AuthenticationFailedException e) {
            // 認証失敗
            e.printStackTrace();
        } catch (MessagingException e) {
            // smtpサーバへの接続失敗
            e.printStackTrace();

        }
    }
}