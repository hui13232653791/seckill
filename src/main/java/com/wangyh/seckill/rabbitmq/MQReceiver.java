package com.wangyh.seckill.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 消息消费者
 */
@Service
@Slf4j
public class MQReceiver {

    @RabbitListener(queues = "queue")
    public void receive_queue(Object msg) {
        log.info("QUEUE接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_fanout01")
    public void receive_fanout01(Object msg) {
        log.info("QUEUE_FANOUT01接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_fanout02")
    public void receive_fanout02(Object msg) {
        log.info("QUEUE_FANOUT02接收消息：" + msg);
    }


    @RabbitListener(queues = "queue_direct01")
    public void receive_direct01(Object msg) {
        log.info("QUEUE_DIRECT01接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_direct02")
    public void receive_direct02(Object msg) {
        log.info("QUEUE_DIRECT02接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_topic01")
    public void receive_topic01(Object msg) {
        log.info("QUEUE_TOPIC01接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_topic02")
    public void receive_topic02(Object msg) {
        log.info("QUEUE_TOPIC02接收消息：" + msg);
    }

    @RabbitListener(queues = "queue_headers01")
    public void receive_headers01(Message message) {
        log.info("QUEUE_HEADERS01接收Message对象：" + message);
        log.info("QUEUE_HEADERS01接收消息：" + new String(message.getBody()));
    }

    @RabbitListener(queues = "queue_headers02")
    public void receive_headers02(Message message) {
        log.info("QUEUE_HEADERS02接收Message对象：" + message);
        log.info("QUEUE_HEADERS02接收消息：" + new String(message.getBody()));
    }


}
