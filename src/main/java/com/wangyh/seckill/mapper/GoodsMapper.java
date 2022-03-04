package com.wangyh.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wangyh.seckill.pojo.Goods;
import com.wangyh.seckill.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangyh
 * @since 2022-01-24
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> fondGoodsVo();

    /**
     * 获取商品详情
     * @return
     */
    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
