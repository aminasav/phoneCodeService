package com.phoneCodeService.usecasses.impl;

import com.byAmina.OutboxEventDTO;
import com.phoneCodeService.api.exception.NonRetryableException;
import com.phoneCodeService.api.exception.RetryableException;
import com.phoneCodeService.persistence.ProcessedEventRepository;
import com.phoneCodeService.persistence.model.ProcessedEventEntity;
import com.phoneCodeService.usecasses.KafkaService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
@KafkaListener(
        topics = "${topic.name}",
        groupId = "${spring.kafka.consumer.group-id}"
)
public class KafkaServiceImpl implements KafkaService {

    private final RestTemplate restTemplate;
    private final ProcessedEventRepository processedEventRepository;

    @KafkaHandler
    @Transactional
    @Override
    public void listen(@Payload OutboxEventDTO outboxEventDTO,
                       @Header("messageId") String messageId,
                       @Header(KafkaHeaders.RECEIVED_KEY) String messageKey) {

        log.info("Received outbox event {}", outboxEventDTO.toString());

        ProcessedEventEntity processedEventEntity = processedEventRepository.findByMessageId(messageId);
        if (processedEventEntity != null) {
            log.info("Duplicate message id: {}", messageId);
            return;
        }

        /*String url = "http://localhost:8081/api/v1/contacts/";
        try {
            ResponseEntity<String> response = restTemplate
                    .exchange(url, HttpMethod.GET, null, String.class);
            if (response.getStatusCode().value() == HttpStatus.OK.value()) {
                log.info("Received response: {}", response.getBody());
            }
        } catch (ResourceAccessException e) {
            log.error(e.getMessage());
            throw new RetryableException(e);
        } catch (HttpServerErrorException e) {
            log.error(e.getMessage());
            throw new NonRetryableException(e);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NonRetryableException(e);
        }*/

        try {
            processedEventRepository.save(new ProcessedEventEntity(messageId));
        } catch (DataIntegrityViolationException e) {
            log.error(e.getMessage());
            throw new NonRetryableException(e);
        }
    }
}