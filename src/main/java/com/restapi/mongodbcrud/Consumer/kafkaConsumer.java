package com.restapi.mongodbcrud.Consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class kafkaConsumer {

@KafkaListener(topics = "businessKafka", groupId = "test-group")
public void consume(Object message) {
ConsumerRecord<String, String> record = (ConsumerRecord<String, String>)
message;
String kafkaMessage = record.value().toString().split(",", 6)[5];
String messageSplit1 = kafkaMessage.split("}", 2)[0];
String messageSplit2 = messageSplit1.split(":", 2)[1];
String overallMessage = messageSplit2.split(",", 2)[0];
String overallNumber = messageSplit2.split(",", 2)[1];

String messageNew = overallMessage.split("\"", 2)[1];
String numberNew = overallNumber.split("\"", 2)[0];

// twilioSMS twilio = new twilioSMS();
// twilio.sendSMS(numberNew, messageNew);
}
}
