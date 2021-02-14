package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycUserCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.vo.PsycUserVo;
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


    /**
    * 分页查询用户列表
    * @author lby
    * @param co 查询条件
    * @date 2021/2/9 14:47
    * @return com.lby.psychology.model.common.PageResult<com.lby.psychology.model.vo.PsycUserVo>
    */
    PageResult getUserList(PsycUserCo co);


    /**
    * 根据用户id删除用户
    * @author lby
    * @param userId 用户id
    * @date 2021/2/13 10:33
    * @return boolean
    */
    boolean deleteUserByUserId(Long userId);

    /**
    * 查询用户详情
    * @author lby
    * @param userId 用户id
    * @date 2021/2/14 0:34
    * @return com.lby.psychology.model.vo.PsycUserVo
    */
    PsycUserVo getUserByUserId(Long userId);

}
