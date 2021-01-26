package com.lby.psychology.config;

import com.lby.psychology.mapper.PsycPermissionMapper;
import com.lby.psychology.model.security.SecurityRolePermission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class SecurityAuthorityDecision {

    public static Map<String, Collection<ConfigAttribute>> map;

    @Autowired
    private PsycPermissionMapper psycPermissionMapper;


    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        if(authentication.getPrincipal().equals("anonymousUser")){
            return false;
        }
        Collection<ConfigAttribute> roles = new ArrayList<>();

        //初始化所有权限对应的角色
        if(CollectionUtils.isEmpty(map)){
            map = new HashMap<>();
            List<SecurityRolePermission> rolePermissionList = psycPermissionMapper.selectRolePermission();
            //将信息按照权限为key ， 角色为value存进去
            rolePermissionList.forEach(e->{
                ConfigAttribute configAttribute = new SecurityConfig(e.getRoleCode());
                if(map.containsKey(e.getPermissionUrl())) {
                    map.get(e.getPermissionUrl()).add(configAttribute);
                }else{
                    Collection<ConfigAttribute> list = new ArrayList<>();
                    list.add(configAttribute);
                    map.put(e.getPermissionUrl(),list);
                }
            });
        }
        //查看当前请求得url在所有资源中是否存在
        for (Map.Entry<String,Collection<ConfigAttribute>> entry : map.entrySet()) {
            if(new AntPathRequestMatcher(entry.getKey()).matches(request)){
                roles.addAll(map.get(entry.getKey()));
            }
        }
        if(CollectionUtils.isEmpty(roles)){
            return false;
        }

        // 查看匹配到得这些角色中是否存在该用户的角色
        for(ConfigAttribute attribute : roles) {
            for(GrantedAuthority grantedAuthority : authentication.getAuthorities()){
                if(attribute.getAttribute().trim().equals(grantedAuthority.getAuthority().trim())){
                    return true;
                }
            }
        }
        throw new AccessDeniedException("权限不足");
    }

}
