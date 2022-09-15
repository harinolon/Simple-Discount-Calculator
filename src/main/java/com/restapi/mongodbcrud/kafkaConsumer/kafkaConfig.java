// package com.restapi.mongodbcrud.kafkaConsumer;

// import java.util.HashMap;
// import java.util.Map;

// import org.apache.kafka.clients.consumer.ConsumerConfig;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.kafka.annotation.EnableKafka;
// import
// org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
// import org.springframework.kafka.core.ConsumerFactory;
// import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

// import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

// @EnableKafka
// @Configuration
// public class kafkaConfig {

// @Bean
// public ConsumerFactory<String, String> consumerFactory() {
// Map<String, Object> config = new HashMap<>();

// config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.25.238.179:9092");
// config.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
// config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
// StringDeserializer.class);
// config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
// StringDeserializer.class);
// // config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "")

// return new DefaultKafkaConsumerFactory<>(config);
// }

// @Bean
// public ConcurrentKafkaListenerContainerFactory
// KafkaListenerContainerFactory() {
// ConcurrentKafkaListenerContainerFactory<String, String> factory = new
// ConcurrentKafkaListenerContainerFactory<>();
// factory.setConsumerFactory(consumerFactory());
// return factory;
// }
// }
