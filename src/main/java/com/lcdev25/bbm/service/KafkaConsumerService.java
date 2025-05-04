package com.lcdev25.bbm.service;

import com.lcdev25.bbm.dto.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

  private static final Logger log = LoggerFactory.getLogger(KafkaConsumerService.class);

  // Use property placeholder for topic and group id from application.properties
  @KafkaListener(topics = "${app.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
  public void listen(
      @Payload SimpleMessage message,
      @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
      @Header(KafkaHeaders.OFFSET) long offset) {

    log.info("Received Message: Partition={}, Offset={}, Value={}", partition, offset, message);
    // Add processing logic here
  }
}
