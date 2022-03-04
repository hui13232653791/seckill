package com.wangyh.seckill.controller;


import com.wangyh.seckill.pojo.User;
import com.wangyh.seckill.rabbitmq.MQSender;
import com.wangyh.seckill.vo.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wangyh
 * @since 2022-01-24
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MQSender mqSender;

    /**
     * 用户信息（测试）
     * @param user
     * @return
     */
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return RespBean.success(user);
    }

    /**
     * 测试发送RabbitMQ消息
     */
    @RequestMapping("/mq")
    @ResponseBody
    public void mq(){
        mqSender.send_queue("Hello");
    }

    /**
     * 测试发送Fanout模式消息
     */
    @RequestMapping("/mq/fanout")
    @ResponseBody
    public void fanout(){
        mqSender.send_fanout("Hello");
    }

    /**
     * 测试发送Direct模式消息
     */
    @RequestMapping("/mq/direct01")
    @ResponseBody
    public void direct01(){
        mqSender.send_direct01("Hello,Red");
    }

    /**
     * 测试发送Direct模式消息
     */
    @RequestMapping("/mq/direct02")
    @ResponseBody
    public void direct02(){
        mqSender.send_direct02("Hello,Green");
    }

    /**
     * 测试发送Topic模式消息
     */
    @RequestMapping("/mq/topic01")
    @ResponseBody
    public void topic01(){
        mqSender.send_topic01("Hello,Red");
    }

    /**
     * 测试发送Topic模式消息
     */
    @RequestMapping("/mq/topic02")
    @ResponseBody
    public void topic02(){
        mqSender.send_topic02("Hello,Green");
    }

    /**
     * 测试发送Headers模式消息
     */
    @RequestMapping("/mq/headers01")
    @ResponseBody
    public void headers01(){
        mqSender.send_headers01("Hello,Headers01");
    }

    /**
     * 测试发送Headers模式消息
     */
    @RequestMapping("/mq/headers02")
    @ResponseBody
    public void headers02(){
        mqSender.send_headers02("Hello,Headers02");
    }


}
