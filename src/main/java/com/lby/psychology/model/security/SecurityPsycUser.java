package com.lby.psychology.model.security;

import com.lby.psychology.model.enums.EnumUserStatus;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SecurityPsycUser extends PsycUser implements UserDetails, Serializable {


    private List<PsycRole> authorities;


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return getUserStatus().equals(EnumUserStatus.USER_ENABLE.getId());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
