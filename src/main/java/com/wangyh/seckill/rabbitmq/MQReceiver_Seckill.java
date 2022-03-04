package com.wangyh.seckill.rabbitmq;

import com.wangyh.seckill.pojo.SeckillMessage;
import com.wangyh.seckill.pojo.SeckillOrder;
import com.wangyh.seckill.pojo.User;
import com.wangyh.seckill.service.IGoodsService;
import com.wangyh.seckill.service.IOrderService;
import com.wangyh.seckill.utils.JsonUtil;
import com.wangyh.seckill.vo.GoodsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * 消息消费者-秒杀使用
 */
@Service
@Slf4j
public class MQReceiver_Seckill {

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private IOrderService orderService;

    /**
     * 下单操作
     *
     * @param message
     */
    @RabbitListener(queues = "seckillQueue")
    public void receive(String message) {
        log.info("接收的消息：" + message);
        SeckillMessage seckillMessage = JsonUtil.jsonStr2Object(message, SeckillMessage.class);
        Long goodsId = seckillMessage.getGoodsId();
        User user = seckillMessage.getUser();
        //判断库存
        GoodsVo goodsVo = goodsService.findGoodsVoByGoodsId(goodsId);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        if (goodsVo.getStockCount() < 1) {
            valueOperations.set("isStockEmpty:" + goodsId, "0");
            return;
        }
        //判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) (redisTemplate.opsForValue().get("order:" + user.getId() + ":" + goodsId));
        if (seckillOrder != null) {
            return;
        }
        //下单操作
        orderService.seckill(user, goodsVo);

    }


}
