package com.phoneCodeService.usecasses;

import com.byAmina.OutboxEventDTO;

public interface KafkaService {
    void listen(OutboxEventDTO outboxEventDTO,
                String messageId, String messageKey);
}
