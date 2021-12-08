package com.company.emailservice.service;

import com.company.emailservice.event.Event;
import com.company.emailservice.service.dto.SubscriptionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.function.Consumer;

@Configuration
public class MessageProcessorConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MessageProcessorConfig.class);

    private final EmailService emailService;

    @Autowired
    public MessageProcessorConfig(EmailService emailService) {
        this.emailService = emailService;
    }

    @Bean
    public Consumer<Event<UUID, SubscriptionDTO>> messageProcessor() {
        return event -> {
            LOG.info("Process message created at {}...", event.getEventCreatedAt());

            SubscriptionDTO subscriptionDTO = event.getData();
            LOG.info("Send email to: {}", subscriptionDTO.getEmail());
            emailService.sendEmail(subscriptionDTO);
            LOG.info("Message processing done!");
        };
    }
}
