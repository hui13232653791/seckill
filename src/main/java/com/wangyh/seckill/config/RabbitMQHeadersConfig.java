package com.wangyh.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * RabbitMQ配置类-Headers模式
 */
@Configuration
public class RabbitMQHeadersConfig {

    private static final String QUEUE_HEADERS01 = "queue_headers01";
    private static final String QUEUE_HEADERS02 = "queue_headers02";
    private static final String EXCHANGE_HEADERS = "headersExchange";

    @Bean
    public Queue queue_header01() {
        return new Queue(QUEUE_HEADERS01);
    }

    @Bean
    public Queue queue_header02() {
        return new Queue(QUEUE_HEADERS02);
    }

    @Bean
    public HeadersExchange headersExchange() {
        return new HeadersExchange(EXCHANGE_HEADERS);
    }

    @Bean
    public Binding binding_header01() {
        Map<String,Object> map = new HashMap<>();
        map.put("color","red");
        map.put("speed","low");
        return BindingBuilder.bind(queue_header01()).to(headersExchange()).whereAny(map).match();
    }

    @Bean
    public Binding binding_header02() {
        Map<String,Object> map = new HashMap<>();
        map.put("color","red");
        map.put("speed","fast");
        return BindingBuilder.bind(queue_header02()).to(headersExchange()).whereAll(map).match();
    }


}
