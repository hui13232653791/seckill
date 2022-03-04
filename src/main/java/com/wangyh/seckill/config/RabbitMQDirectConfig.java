package com.wangyh.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类-Direct模式
 */
@Configuration
public class RabbitMQDirectConfig {

    private static final String QUEUE_DIRECT01 = "queue_direct01";
    private static final String QUEUE_DIRECT02 = "queue_direct02";
    private static final String EXCHANGE_DIRECT = "directExchange";
    private static final String ROUTINGKEY_DIRECT01 = "queue.red";
    private static final String ROUTINGKEY_DIRECT02 = "queue.green";

    @Bean
    public Queue queue_direct01() {
        return new Queue(QUEUE_DIRECT01);
    }

    @Bean
    public Queue queue_direct02() {
        return new Queue(QUEUE_DIRECT02);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_DIRECT);
    }

    @Bean
    public Binding binding_direct01() {
        return BindingBuilder.bind(queue_direct01()).to(directExchange()).with(ROUTINGKEY_DIRECT01);
    }

    @Bean
    public Binding binding_direct02() {
        return BindingBuilder.bind(queue_direct02()).to(directExchange()).with(ROUTINGKEY_DIRECT02);
    }


}
