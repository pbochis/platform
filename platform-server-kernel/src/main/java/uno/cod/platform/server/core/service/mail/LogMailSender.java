package uno.cod.platform.server.core.service.mail;

import com.google.common.base.Joiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import uno.cod.platform.server.core.Profiles;

import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

@Service
@Profile({Profiles.DEVELOPMENT, Profiles.TEST})
public class LogMailSender extends JavaMailSenderImpl implements JavaMailSender {

    private static final Logger LOG = LoggerFactory.getLogger(LogMailSender.class);

    public LogMailSender() {
        setHost("log-only-sending");//no real host
        LOG.info("LogMailSender in use. No real mails are sent.");
    }

    @Override
    public void send(SimpleMailMessage simpleMessage) {
        logSimpleMailMessage(simpleMessage);
    }

    @Override
    public void send(SimpleMailMessage... simpleMessages) {
        for (SimpleMailMessage message : simpleMessages) {
            logSimpleMailMessage(message);
        }
    }

    @Override
    protected void doSend(MimeMessage[] mimeMessages, Object[] originalMessages) {
        for (MimeMessage mimeMessage : mimeMessages) {
            logMimeMessage(mimeMessage);
        }
    }

    private void logMimeMessage(MimeMessage message) {
        try {
            message.saveChanges();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            message.writeTo(baos);
            String mail = new String(baos.toByteArray(), Charset.forName("UTF-8"));

            LOG.info("Sending message\n" + mail);
        } catch (Exception ex) {
            LOG.error("Error logging mime-message: " + ex.getMessage(), ex);
        }
    }

    private void logSimpleMailMessage(SimpleMailMessage simpleMessage) {
        LOG.info("Sending message" +
                "\n\tto: " + Joiner.on(", ").join(simpleMessage.getTo()) +
                "\n\tfrom: " + simpleMessage.getFrom() +
                "\n\tsubject: " + simpleMessage.getSubject() +
                "\n\tmessage: " + simpleMessage.getText());
    }
}