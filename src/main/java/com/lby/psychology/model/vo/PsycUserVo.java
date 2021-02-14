package com.lby.psychology.model.vo;

import com.lby.psychology.model.pojo.PsycUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PsycUserVo extends PsycUser {

    private String sexName;

    private String userStatusName;

    private String roleNames;

    private String authName;

}
