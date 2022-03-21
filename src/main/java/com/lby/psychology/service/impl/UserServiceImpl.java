package com.lby.psychology.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lby.psychology.config.PsycException;
import com.lby.psychology.config.PsycPasswordEncoder;
import com.lby.psychology.mapper.PsycExamRecordMapper;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.co.PsycUserCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.enums.*;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUser;
import com.lby.psychology.model.pojo.PsycUserRoleRlt;
import com.lby.psychology.model.vo.*;
import com.lby.psychology.service.IUserService;
import com.lby.psychology.util.CommonUtil;
import com.lby.psychology.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private PsycUserMapper userMapper;

    @Autowired
    private PsycRoleMapper roleMapper;

    @Autowired
    private PsycExamRecordMapper examRecordMapper;

    @Autowired
    private PsycPasswordEncoder passwordEncoder;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private CommonUtil commonUtil;

    @Value("${spring.mail.valicode.expired}")
    private Long expired;


    @Override
    public void checkEmailRegistered(RegisteredUserVo registeredUserVo) {
        //检验用户是否存在
        if(userMapper.selectEmailCount(registeredUserVo.getEmail()) > 0){
            throw new PsycException(EnumResponseType.EMAIL_IS_EXIST);
        }
        Integer code = commonUtil.getRandomNumber();
        log.info("开始发送验证码：{}",code);
        commonUtil.sendMailCheckCode(registeredUserVo.getEmail(),code.toString());
        //将验证码设置到redis内，过期时间60s
        redisUtil.set(EnumRedisPre.EMAIL_REG_CHECK.getCode()+registeredUserVo.getEmail(),code,expired);
    }

    @Override
    public boolean registeredPsycUser(RegisteredUserVo registeredUserVo) {
        //用户名和邮箱相同
        registeredUserVo.setUsername(registeredUserVo.getEmail());
        boolean result;
        //获取默认角色
        List<PsycRole> authorityList = roleMapper.selectDefaultRoleList();
        String code = String.valueOf(redisUtil.get(EnumRedisPre.EMAIL_REG_CHECK.getCode()+registeredUserVo.getEmail()));
        if(!StringUtils.isEmpty(registeredUserVo.getCode()) &&!registeredUserVo.getCode().equals(code)){
            throw new PsycException(EnumResponseType.VALID_CODE_NOT_SAME);
        }
        //将秘密进行非对称解密，然后加密保存
        registeredUserVo.setPassword(passwordEncoder.encode(passwordEncoder.decodeByRSA(registeredUserVo.getPassword())));
        result = userMapper.insertPsycUser(registeredUserVo) > 0;
        List<PsycUserRoleRlt> psycUserRoleRltList = new ArrayList<>();
        for(PsycRole role : authorityList){
            PsycUserRoleRlt psycUserRoleRlt = new PsycUserRoleRlt();
            psycUserRoleRlt.setUserId(registeredUserVo.getUserId());
            psycUserRoleRlt.setRoleId(role.getRoleId());
            psycUserRoleRlt.setRemark("qq登录授予用户默认角色，用户名为"+registeredUserVo.getUsername());
            psycUserRoleRltList.add(psycUserRoleRlt);
        }
        roleMapper.insertRoleUserRlt(psycUserRoleRltList);
        return result;
    }

    @Override
    public PageResult getUserList(PsycUserCo co) {
        PageHelper.startPage(co.getPageNum(),co.getPageSize());
        List<PsycUserVo> list = userMapper.selectPsycUser(co);
        list.forEach(e->{
            e.setSexName(Objects.requireNonNull(EnumSex.getEnumById(e.getSex())).getName());
            e.setUserStatusName(Objects.requireNonNull(EnumUserStatus.getEnumById(e.getUserStatus())).getName());
            e.setAuthName(Objects.requireNonNull(EnumAuthType.getEnumById(e.getAuthType())).getName());
        });
        PageInfo<PsycUserVo> pageInfo = new PageInfo<>(list);
        return PageResult.getPageResult(pageInfo);
    }

    @Override
    public boolean deleteUserByUserId(Long userId) {
        boolean result = false;
        if(userMapper.deleteByPrimaryKey(userId) > 0){
            //删除相关权限
            result = userMapper.deleteUserRole(userId) > 0;
            //删除答题记录
            examRecordMapper.deleteRecordByUserId(userId);
        }
        return result;
    }

    @Override
    public PsycUserVo getUserByUserId(Long userId) {
        PsycUserVo e =  userMapper.selectUserByUserId(userId);
        e.setSexName(Objects.requireNonNull(EnumSex.getEnumById(e.getSex())).getName());
        e.setUserStatusName(Objects.requireNonNull(EnumUserStatus.getEnumById(e.getUserStatus())).getName());
        e.setAuthName(Objects.requireNonNull(EnumAuthType.getEnumById(e.getAuthType())).getName());
        return e;
    }

    @Override
    public List<RolePermissionVo> getRolePermissionList(List<Integer> roleIdList, Integer permissionType) {
        return roleMapper.selectRolePermission(roleIdList,permissionType);
    }

    @Override
    public boolean updateFigureUrl(Long userId, String url) {
        return userMapper.updateFigureUrl(userId,url) > 0;
    }

    @Override
    public boolean updateUserInfo(PsycUser user) {
        return userMapper.updateUserInfo(user) > 0;
    }

    @Override
    public boolean updatePassword(PsycChangePasswordVo changePasswordVo) {
        return userMapper.updatePassword(changePasswordVo.getUserId(),changePasswordVo.getNewPassword()) > 0;
    }

    @Override
    public boolean checkPassword(Long userId ,String password) {
        return passwordEncoder.matches(password,userMapper.selectPassword(userId));
    }

    @Override
    public String getPasswordByUserId(Long userId) {
        return userMapper.selectPasswordByUserId(userId);
    }

    @Override
    public boolean forgetPassword(PsycForgetPasswordVo forgetPasswordVo) {
        //检验用户是否存在
        if(userMapper.selectEmailCount(forgetPasswordVo.getEmail()) < 1){
            throw new PsycException(EnumResponseType.EMAIL_NOT_EXIST);
        }
        //如果验证码为空则发送验证码
        if(StringUtils.isEmpty(forgetPasswordVo.getCode())){
            Integer code = commonUtil.getRandomNumber();
            log.info("开始发送验证码：{}",code);
            commonUtil.sendMailCheckCode(forgetPasswordVo.getEmail(),code.toString());
            //将验证码设置到redis内，过期时间60s
            redisUtil.set(EnumRedisPre.EMAIL_FORGET_CHECK.getCode()+forgetPasswordVo.getEmail(),code,expired);
            return true;
        }else{
            //更改密码
            //判断验证码是否正确

            String code = String.valueOf(redisUtil.get(EnumRedisPre.EMAIL_FORGET_CHECK.getCode()+forgetPasswordVo.getEmail()));
            if(!forgetPasswordVo.getCode().equals(code)){
                throw new PsycException(EnumResponseType.VALID_CODE_NOT_SAME);
            }
           String password = passwordEncoder.encode(passwordEncoder.decodeByRSA(forgetPasswordVo.getNewPassword()));
           return userMapper.updatePasswordByEmail(forgetPasswordVo.getEmail(),password) > 0;
        }

    }


}
