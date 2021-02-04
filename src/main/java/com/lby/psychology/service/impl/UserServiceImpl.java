package com.lby.psychology.service.impl;

import com.lby.psychology.config.PsycException;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.enums.EnumRedisPre;
import com.lby.psychology.model.enums.EnumResponseType;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUserRoleRlt;
import com.lby.psychology.model.vo.RegisteredUserVo;
import com.lby.psychology.service.IUserService;
import com.lby.psychology.util.CommonUtil;
import com.lby.psychology.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private PsycUserMapper userMapper;

    @Autowired
    private PsycRoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        redisUtil.set(EnumRedisPre.EMAIL_CHECK.getCode()+registeredUserVo.getEmail(),code,expired);
    }

    @Override
    public boolean registeredPsycUser(RegisteredUserVo registeredUserVo) {
        boolean result;
        //获取默认角色
        List<PsycRole> authorityList = roleMapper.selectDefaultRoleList();
        String code = String.valueOf(redisUtil.get(EnumRedisPre.EMAIL_CHECK.getCode()+registeredUserVo.getEmail()));
        if(!StringUtils.isEmpty(registeredUserVo.getCode()) &&!registeredUserVo.getCode().equals(code)){
            throw new PsycException(EnumResponseType.VALID_CODE_NOT_SAME);
        }
        //加密密码
        registeredUserVo.setPassword(passwordEncoder.encode(registeredUserVo.getPassword()));

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
}
