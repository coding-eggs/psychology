package com.lby.psychology.service;

import com.lby.psychology.model.vo.RegisteredUserVo;

public interface IUserService {

    /**
    * 描述
    * @author lby
    * @param registeredUserVo 注册验证邮箱真实性
    * @date 2021/2/4 18:03
    */
    void checkEmailRegistered(RegisteredUserVo registeredUserVo);

    /**
    * 注册用户
    * @author lby
     * @param registeredUserVo 注册验证信息
    * @date 2021/2/4 18:21
    * @return int
    */
    boolean registeredPsycUser(RegisteredUserVo registeredUserVo);

}
