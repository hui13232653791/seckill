package com.wangyh.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wangyh.seckill.pojo.User;
import com.wangyh.seckill.vo.LoginVo;
import com.wangyh.seckill.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wangyh
 * @since 2022-01-24
 */
public interface IUserService extends IService<User> {

    /**
     * 登录功能
     * @param loginVo
     * @return
     */
    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 根据cookie获取用户
     * @param userTicket
     * @return
     */
    User getUserByCookie(String userTicket,HttpServletRequest request,HttpServletResponse response);

    /**
     * 更新密码
     * @param userTicket
     * @param password
     * @param request
     * @param response
     * @return
     */
    RespBean updatePassword(String userTicket,String password, HttpServletRequest request, HttpServletResponse response);

}
