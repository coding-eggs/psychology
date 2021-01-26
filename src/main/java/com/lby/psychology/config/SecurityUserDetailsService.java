package com.lby.psychology.config;

import com.lby.psychology.mapper.PsycRoleMapper;
import com.lby.psychology.mapper.PsycUserMapper;
import com.lby.psychology.model.security.SecurityPsycUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class SecurityUserDetailsService implements UserDetailsService {


    @Autowired
    private PsycUserMapper psycUserMapper;

    @Autowired
    private PsycRoleMapper psycRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SecurityPsycUser user = psycUserMapper.selectPsycUserByEmail(s);
        //查询用户对应得角色信息
        if(!ObjectUtils.isEmpty(user)) {
            user.setAuthorities(psycRoleMapper.selectPsycRoleListByUserId(user.getUserId()));
            return user;
        }
        throw new UsernameNotFoundException("用户名不存在！");
    }
}
