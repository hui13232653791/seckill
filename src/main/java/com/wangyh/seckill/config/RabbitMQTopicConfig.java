package com.wangyh.seckill.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类-Topic模式
 */
@Configuration
public class RabbitMQTopicConfig {

    private static final String QUEUE_TOPIC01 = "queue_topic01";
    private static final String QUEUE_TOPIC02 = "queue_topic02";
    private static final String EXCHANGE_TOPIC = "topicExchange";
    //#表示多个，*表示一个
    private static final String ROUTINGKEY_TOPIC01 = "#.queue.#";  //x.x.xxx.queue.xxxx.x.x
    private static final String ROUTINGKEY_TOPIC02 = "*.queue.#";  //xxx.queue.xxx.x.x

    @Bean
    public Queue queue_topic01() {
        return new Queue(QUEUE_TOPIC01);
    }

    @Bean
    public Queue queue_topic02() {
        return new Queue(QUEUE_TOPIC02);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_TOPIC);
    }

    @Bean
    public Binding binding_topic01() {
        return BindingBuilder.bind(queue_topic01()).to(topicExchange()).with(ROUTINGKEY_TOPIC01);
    }

    @Bean
    public Binding binding_topic02() {
        return BindingBuilder.bind(queue_topic02()).to(topicExchange()).with(ROUTINGKEY_TOPIC02);
    }


}
