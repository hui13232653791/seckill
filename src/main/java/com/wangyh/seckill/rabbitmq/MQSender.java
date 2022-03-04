package com.wangyh.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 消息发送者
 */
@Service
@Slf4j
public class MQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public void send_queue(Object msg) {
        log.info("QUEUE发送消息：" + msg);
        //发送RabbitMQ消息
        rabbitTemplate.convertAndSend("queue", msg);
    }

    public void send_fanout(Object msg) {
        log.info("QUEUE_FANOUT发送消息：" + msg);
        //Fanout模式发送消息
        rabbitTemplate.convertAndSend("fanoutExchange","", msg);
    }

    public void send_direct01(Object msg){
        log.info("QUEUE_DIRECT01发送消息：" + msg);
        rabbitTemplate.convertAndSend("directExchange","queue.red",msg);
    }

    public void send_direct02(Object msg){
        log.info("QUEUE_DIRECT02发送消息：" + msg);
        rabbitTemplate.convertAndSend("directExchange","queue.green",msg);
    }

    public void send_topic01(Object msg){
        log.info("QUEUE_TOPIC01发送消息：" + msg);
        rabbitTemplate.convertAndSend("topicExchange","queue.red.message",msg);
    }

    public void send_topic02(Object msg){
        log.info("QUEUE_TOPIC02发送消息：" + msg);
        rabbitTemplate.convertAndSend("topicExchange","message.queue.green.abc",msg);
    }

    public void send_headers01(String msg){
        log.info("QUEUE_HEADERS发送消息（被两个queue_headers接收）：" + msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("color","red");
        properties.setHeader("speed","fast");
        Message message = new Message(msg.getBytes(),properties);
        rabbitTemplate.convertAndSend("headersExchange","",message);
    }

    public void send_headers02(String msg){
        log.info("QUEUE_HEADERS发送消息（被queue_headers01接收）：" + msg);
        MessageProperties properties = new MessageProperties();
        properties.setHeader("color","red");
        properties.setHeader("speed","normal");
        Message message = new Message(msg.getBytes(),properties);
        rabbitTemplate.convertAndSend("headersExchange","",message);
    }


}
