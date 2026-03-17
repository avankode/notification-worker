package com.avaneesh.notifcation_worker.config;

import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

// Make sure your worker's DTO is imported here!
import com.avaneesh.notifcation_worker.dto.NotificationRequestDTO;

@Configuration
public class RabbitMQConfig {

    @Bean
    public MessageConverter jsonMessageConverter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");

        // --- THE MAGIC TRANSLATION MAP ---
        Map<String, Class<?>> idClassMapping = new HashMap<>();

        // Tell the worker: "If you see the API's class name, just use my local class instead!"
        idClassMapping.put("com.avaneesh.notifcation_api.dto.NotificationRequestDTO", NotificationRequestDTO.class);

        classMapper.setIdClassMapping(idClassMapping);
        converter.setClassMapper(classMapper);

        return converter;
    }
}
