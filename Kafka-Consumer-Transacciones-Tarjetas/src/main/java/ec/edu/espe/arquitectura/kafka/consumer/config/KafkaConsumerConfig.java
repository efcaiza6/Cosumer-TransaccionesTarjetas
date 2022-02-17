/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.arquitectura.kafka.consumer.config;

import ec.edu.espe.arquitectura.generador.dto.TransaccionRQ;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

/**
 *
 * @author Windows Boo
 */
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String boostrapServers;

    @Value("${spring.kafka.properties.sasl.jaas.config}")
    private String config;

    @Value("${spring.kafka.properties.sasl.mechanism}")
    private String mechanism;

    @Value("${spring.kafka.properties.security.protocol}")
    private String protocol;
    
    @Value("${spring.kafka.consumer.value-deserializer}")
    private String deser;

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, boostrapServers);
        props.put(SaslConfigs.SASL_JAAS_CONFIG, config);
        props.put(SaslConfigs.SASL_MECHANISM, mechanism);
        props.put(AdminClientConfig.SECURITY_PROTOCOL_CONFIG, protocol);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "ec.edu.espe.arquitectura.generador.dto");
        return props;
    }

    @Bean
    public ConsumerFactory<String, TransaccionRQ> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfig());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TransaccionRQ> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TransaccionRQ> factory = new ConcurrentKafkaListenerContainerFactory<String, TransaccionRQ>();
        factory.setConsumerFactory(consumerFactory());
        factory.setConcurrency(3);
        return factory;
    }

}
