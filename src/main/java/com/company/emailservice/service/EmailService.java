package com.company.emailservice.service;

import com.company.emailservice.service.dto.SubscriptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSender javaMailSender;

    private final StreamBridge streamBridge;

    public EmailService(JavaMailSender javaMailSender, StreamBridge streamBridge) {
        this.javaMailSender = javaMailSender;
        this.streamBridge = streamBridge;
    }

    public void sendEmail(SubscriptionDTO subscriptionDTO) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, false, StandardCharsets.UTF_8.name());
            message.setTo(subscriptionDTO.getEmail());
            message.setFrom("");
            message.setSubject("subscriptionDTO.getSubject()");
            message.setText("subscriptionDTO.getContent()", "subscriptionDTO.isHtml()");
            javaMailSender.send(mimeMessage);
            LOG.debug("Sent email to User '{}'", subscriptionDTO.getEmail());
        } catch (MailException | MessagingException e) {
            LOG.warn("Email could not be sent to user '{}'", subscriptionDTO.getEmail(), e);
        }
    }
}
