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

    //�G���R�[�h�w��
    private static final String ENCODE = "Windows-31J";//"ISO-2022-JP";


//    public static void main(String[] args) {
//
//        //���[�����t
//        new SendMailTest().send();
//
//    }

//�������烁�[�����t�ɕK�v��SMTP,SSL�F�؂Ȃǂ̐ݒ�

    public void send(String mail,String send_message,String url) {
        final Properties props = new Properties();

        // SMTP�T�[�o�[�̐ݒ�B�����ł�googlemail��smtp�T�[�o�[��ݒ�B
        props.put("mail.smtp.host", "smtp.gmail.com");

        // SSL�p�Ƀ|�[�g�ԍ���ύX�B
        props.put("mail.smtp.port", "587"); //465

        // �^�C���A�E�g�ݒ�
        props.put("mail.smtp.connectiontimeout", "60000");
        props.put("mail.smtp.timeout", "60000");

        // �F��
        props.put("mail.smtp.auth", "true");
        //Session session = Session.getInstance( props, new myAuth() );

        //�ǉ�
        props.put("mail.smtp.starttls.enable" , "true");

        // SSL���g�p����Ƃ��͂��̐ݒ肪�K�v�B
        //props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.setProperty("mail.smtp.socketFactory.fallback", "false");
        //props.setProperty("mail.smtp.socketFactory.port", "465");

        //props�ɐݒ肵�������g�p���āAsession�̍쐬
        final Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("teamlearn.test@gmail.com", "teamlearn");
            }
        });



        // �������烁�b�Z�[�W���e�̐ݒ�B��L�ō쐬����session�������ɓn���B
        final MimeMessage message = new MimeMessage(session);

        try {
            final Address addrFrom = new InternetAddress(
                    "teamlearn.test@gmail.com", "�`�[�����[��", ENCODE);//���M�ҏ��
            message.setFrom(addrFrom);

            final Address addrTo = new InternetAddress(mail,
                    mail+"�l", ENCODE);//������
            message.addRecipient(Message.RecipientType.TO, addrTo);

            // ���[����Subject
            message.setSubject("�F��", ENCODE);

            // ���[���{���B
//            message.setText("url�ɃA�N�Z�X���ēo�^���������Ă��������B"+url, ENCODE);
            message.setText(send_message+url, ENCODE);


            // ���̑��̕t�����B
            message.addHeader("X-Mailer���[���[", "blancoMail 0.1�G���W���H");
            message.setSentDate(new Date());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // ���[�����M�B
        try {
            Transport.send(message);

        } catch (AuthenticationFailedException e) {
            // �F�؎��s
            e.printStackTrace();
        } catch (MessagingException e) {
            // smtp�T�[�o�ւ̐ڑ����s
            e.printStackTrace();

        }
    }
}