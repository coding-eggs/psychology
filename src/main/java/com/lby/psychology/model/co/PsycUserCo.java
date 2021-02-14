package com.lby.psychology.model.co;

import com.lby.psychology.model.common.PageBasic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycUserCo extends PageBasic {

    private String username;

    private String nickname;

    private String email;

    private Integer sex;

    private Integer userStatus;

    private String startCreateDate;

    private String endCreateDate;

    private String startLastUserLoginDate;

    private String endLastUserLoginDate;

}
