package com.lby.psychology.config;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.QQAccessToken;
import com.lby.psychology.model.QQOpenId;
import com.lby.psychology.model.QQUserInfo;
import com.lby.psychology.model.enums.EnumAuthType;
import com.lby.psychology.model.enums.EnumSex;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUserRoleRlt;
import com.lby.psychology.model.security.SecurityPsycUser;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
@Slf4j
public class QQAuthenticationManager implements AuthenticationManager {
    /**
     * 获取用户信息
     */
    private final static String USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=%s&oauth_consumer_key=%s&openid=%s";

    /**
     * client_id 由腾讯提供(即AppId)
     */
    private static final String CLIENT_ID = "101583722";


    private final RestTemplate restTemplate;

    private final PsycRoleMapper psycRoleMapper;

    private final PsycUserMapper psycUserMapper;

    public QQAuthenticationManager(PsycRoleMapper psycRoleMapper,PsycUserMapper psycUserMapper,RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.psycUserMapper = psycUserMapper;
        this.psycRoleMapper = psycRoleMapper;
    }

    @SneakyThrows
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //获取qq user info
        SecurityPsycUser principal = null;
        List<PsycRole> authorityList = psycRoleMapper.selectDefaultRoleList();
        if(!ObjectUtils.isEmpty(authentication.getPrincipal()) && !ObjectUtils.isEmpty(authentication.getCredentials())){

            String accessToken = ((QQAccessToken)authentication.getPrincipal()).getAccess_token();
            String openId = ((QQOpenId)authentication.getCredentials()).getOpenid();
            //请求获取用户信息
            QQUserInfo qqUserInfo = restTemplate.getForObject(String.format(USER_INFO_URL,accessToken,CLIENT_ID,openId), QQUserInfo.class);
            //打印请求用户信息日志
            log.info("qq登录者信息为：{}",JSONObject.toJSONString(qqUserInfo));
            if(!ObjectUtils.isEmpty(qqUserInfo) && qqUserInfo.getRet() == 0) {
                SecurityPsycUser securityPsycUser = new SecurityPsycUser();
                securityPsycUser.setFigureUrl(qqUserInfo.getFigureurl_qq());
                securityPsycUser.setSex(EnumSex.MAN.getName().equals(qqUserInfo.getGender().trim()) ? EnumSex.MAN.getId():EnumSex.WOMAN.getId());
                securityPsycUser.setLastUserLoginDate(new Date());
                securityPsycUser.setQqNickname(qqUserInfo.getNickname());
                securityPsycUser.setQqOpenId(openId);
                securityPsycUser.setAuthType(EnumAuthType.QQ.getId());

                if(psycUserMapper.selectCountByOpenId(openId) > 0){
                    // 更新当前得账户
                    log.info("已存在该用户，更新用户信息");
                    psycUserMapper.updatePsycUserQQLogin(securityPsycUser);
                    principal = psycUserMapper.selectUserByOpenId(openId);
                }else {
                    //插入新的用户
                    log.info("新增qq登录用户");
                    securityPsycUser.setLastPasswordRestDate(new Date());
                    psycUserMapper.insertPsycUserQQLogin(securityPsycUser);
                    principal = psycUserMapper.selectUserByOpenId(openId);
                    //插入用户关联角色
                    log.info("插入用户得默认角色，用户id为：{},授予得权限列表为：{}",principal.getUserId(),JSONObject.toJSONString(authorityList));
                    List<PsycUserRoleRlt> psycUserRoleRltList = new ArrayList<>();
                    for(PsycRole role : authorityList){
                        PsycUserRoleRlt psycUserRoleRlt = new PsycUserRoleRlt();
                        psycUserRoleRlt.setUserId(principal.getUserId());
                        psycUserRoleRlt.setRoleId(role.getRoleId());
                        psycUserRoleRlt.setRemark("qq登录授予用户默认角色，用户名为"+principal.getQqNickname());
                        psycUserRoleRltList.add(psycUserRoleRlt);
                    }
                    psycRoleMapper.insertRoleUserRlt(psycUserRoleRltList);
                }
            }

        }

        log.info(JSONObject.toJSONString(principal));
        return new UsernamePasswordAuthenticationToken(principal,null,authorityList);
    }
}
