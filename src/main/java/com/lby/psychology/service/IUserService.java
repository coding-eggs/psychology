package com.lby.psychology.service;

import com.lby.psychology.model.co.PsycUserCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.pojo.PsycUser;
import com.lby.psychology.model.vo.*;

import java.util.List;

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


    /**
     * 获取用户角色权限列表
     * @param roleIdList 角色列表
     * @param permissionType 权限类型
     */
    List<RolePermissionVo> getRolePermissionList(List<Integer> roleIdList,Integer permissionType);

    /**
     * 更新用户头像
     * @param userId 用户id
     * @param url 头像地址
     * @return 成功失败
     */
    boolean updateFigureUrl(Long userId, String url);

    /**
     * 更新用户信息
     * @param user 用户信息
     */
    boolean updateUserInfo(PsycUser user);

    /**
     * 更改用户密码
     * @param changePasswordVo
     */
    boolean updatePassword(PsycChangePasswordVo changePasswordVo);

    /**
     * 校验密码
     * @param password
     */
    boolean checkPassword(Long userId, String password);

    /**
     * 查询用户密码
     * @param userId 用户id
     */
    String getPasswordByUserId(Long userId);


    /**
     * 忘记密码
     * @param forgetPasswordVo
     * @return
     */
    boolean forgetPassword(PsycForgetPasswordVo forgetPasswordVo);

}
