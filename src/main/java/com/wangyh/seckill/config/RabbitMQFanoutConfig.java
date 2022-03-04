package com.wangyh.seckill.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类-Fanout模式
 */
@Configuration
public class RabbitMQFanoutConfig {

    private static final String QUEUE_FANOUT01 = "queue_fanout01";
    private static final String QUEUE_FANOUT02 = "queue_fanout02";
    private static final String EXCHANGE_FANOUT = "fanoutExchange";

    @Bean
    public Queue queue() {
        return new Queue("queue", true);
    }

    @Bean
    public Queue queue_fanout01() {
        return new Queue(QUEUE_FANOUT01);
    }

    @Bean
    public Queue queue_fanout02() {
        return new Queue(QUEUE_FANOUT02);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGE_FANOUT);
    }

    @Bean
    public Binding binding_fanout01() {
        return BindingBuilder.bind(queue_fanout01()).to(fanoutExchange());
    }

    @Bean
    public Binding binding_fanout02() {
        return BindingBuilder.bind(queue_fanout02()).to(fanoutExchange());
    }

}
