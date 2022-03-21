package com.lby.psychology.config;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.alipay.easysdk.base.oauth.models.AlipaySystemOauthTokenResponse;
import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.enums.EnumAuthType;
import com.lby.psychology.model.enums.EnumSex;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUserRoleRlt;
import com.lby.psychology.model.security.SecurityPsycUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Slf4j
public class AlipayAuthenticationManager implements AuthenticationManager {

    private final PsycRoleMapper psycRoleMapper;

    private final PsycUserMapper psycUserMapper;

    private final AlipayClient alipayClient;

    public AlipayAuthenticationManager(PsycRoleMapper psycRoleMapper, PsycUserMapper psycUserMapper,AlipayClient alipayClient) {
        this.psycRoleMapper = psycRoleMapper;
        this.psycUserMapper = psycUserMapper;
        this.alipayClient = alipayClient;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //获取 user info
        SecurityPsycUser principal = null;
        List<PsycRole> authorityList = psycRoleMapper.selectDefaultRoleList();
        if(!ObjectUtils.isEmpty(authentication.getPrincipal()) && !ObjectUtils.isEmpty(authentication.getCredentials())){

            String accessToken = ((AlipaySystemOauthTokenResponse)authentication.getPrincipal()).getAccessToken();
            //请求获取用户信息

            try {
                //请求阿里接口
               AlipayUserInfoShareResponse response = alipayClient.execute(new AlipayUserInfoShareRequest(),accessToken);
                //打印请求用户信息日志
                log.info("支付宝登录者信息为：{}", JSONObject.toJSONString(response));
                if(!ObjectUtils.isEmpty(response)) {
                    SecurityPsycUser securityPsycUser = new SecurityPsycUser();
                    securityPsycUser.setFigureUrl(response.getAvatar());
                    securityPsycUser.setSex(response.getGender().toUpperCase(Locale.ROOT).equals( "F") ? EnumSex.WOMAN.getId():EnumSex.MAN.getId());
                    securityPsycUser.setLastUserLoginDate(new Date());
                    securityPsycUser.setUsername(response.getNickName());
                    securityPsycUser.setNickname(response.getNickName());
                    securityPsycUser.setQqNickname(response.getNickName());
                    securityPsycUser.setQqOpenId(response.getUserId());
                    securityPsycUser.setAuthType(EnumAuthType.ALIPAY.getId());

                    if(psycUserMapper.selectCountByOpenId(response.getUserId(),EnumAuthType.ALIPAY.getId()) > 0){
                        // 更新当前得账户
                        log.info("已存在该用户，更新用户信息");
                        psycUserMapper.updatePsycUserQQLogin(securityPsycUser);
                        principal = psycUserMapper.selectUserByOpenId(response.getUserId());
                        principal.setAuthorities(authorityList);
                    }else {
                        //插入新的用户
                        log.info("新增支付宝登录用户");
                        securityPsycUser.setLastPasswordRestDate(new Date());
                        psycUserMapper.insertPsycUserQQLogin(securityPsycUser);
                        principal = psycUserMapper.selectUserByOpenId(response.getUserId());
                        //插入用户关联角色
                        log.info("插入用户得默认角色，用户id为：{},授予得权限列表为：{}",principal.getUserId(),JSONObject.toJSONString(authorityList));
                        List<PsycUserRoleRlt> psycUserRoleRltList = new ArrayList<>();
                        for(PsycRole role : authorityList){
                            PsycUserRoleRlt psycUserRoleRlt = new PsycUserRoleRlt();
                            psycUserRoleRlt.setUserId(principal.getUserId());
                            psycUserRoleRlt.setRoleId(role.getRoleId());
                            psycUserRoleRlt.setRemark("支付宝登录授予用户默认角色，用户名为"+principal.getQqNickname());
                            psycUserRoleRltList.add(psycUserRoleRlt);
                        }
                        psycRoleMapper.insertRoleUserRlt(psycUserRoleRltList);
                        principal.setAuthorities(authorityList);
                    }
                }


            } catch (AlipayApiException e) {
                e.printStackTrace();
            }

        }

        log.info(JSONObject.toJSONString(principal));
        return new UsernamePasswordAuthenticationToken(principal,null,authorityList);
    }
}
