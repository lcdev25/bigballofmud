package com.lcdev25.bbm.service;

import com.lcdev25.bbm.dto.SimpleMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Service
public class KafkaProducerService {

  private static final Logger log = LoggerFactory.getLogger(KafkaProducerService.class);
  private final AtomicLong counter = new AtomicLong(0);

  @Value("${app.kafka.topic}")
  private String topic;

  @Autowired private KafkaTemplate<String, SimpleMessage> kafkaTemplate;

  @Scheduled(fixedRate = 15000)
  public void sendMessage() {
    long currentCount = counter.incrementAndGet();
    SimpleMessage simpleMessage =
        new SimpleMessage(currentCount, "Hello from Java Kafka Producer! Count: " + currentCount);
    log.info("Sending message to topic {}: {}", topic, simpleMessage);
    try {
      kafkaTemplate.send(topic, simpleMessage);
    } catch (Exception e) {
      log.error("Error sending message to Kafka: {}", e.getMessage(), e);
    }
  }
}
