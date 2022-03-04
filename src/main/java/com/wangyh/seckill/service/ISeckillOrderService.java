package com.wangyh.seckill.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyh.seckill.pojo.SeckillGoods;
import com.wangyh.seckill.pojo.SeckillOrder;
import com.wangyh.seckill.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyh
 * @since 2022-01-24
 */
public interface ISeckillOrderService extends IService<SeckillOrder> {

    /**
     * 获取秒杀结果
     * @param user
     * @param goodsId
     * @return orderId：成功；   -1：秒杀失败；  0：秒杀排队中；
     */
    Long getResult(User user, Long goodsId);
}
