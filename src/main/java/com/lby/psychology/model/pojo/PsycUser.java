package com.lby.psychology.model.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * psyc_user
 * @author
 */
@Data
public class PsycUser implements Serializable {
    /**
     * id
     */
    @ApiModelProperty(value="id")
    private Long userId;

    /**
     * 认证类型10 邮箱，20 qq登录
     */
    @ApiModelProperty(value="认证类型10 邮箱，20 qq登录")
    private Integer authType;

    /**
     * 用户名
     */
    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 邮箱 ( 检验用户的 )
     */
    @ApiModelProperty(value="邮箱 ( 检验用户的 )")
    private String email;

    /**
     * qq昵称
     */
    @ApiModelProperty(value="qq昵称")
    private String qqNickname;

    /**
     * QQ登录openid，第三方登陆时识别用户信息的唯一标识
     */
    @ApiModelProperty(value="QQ登录openid，第三方登陆时识别用户信息的唯一标识")
    private String qqOpenId;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="生日")
    private Date birthDate;

    /**
     * 10男  20 女  0其他
     */
    @ApiModelProperty(value="10男  20 女  0其他")
    private Short sex;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像")
    private String figureUrl;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value="创建时间")
    private Date createDate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Date updateDate;

    /**
     * 手机号
     */
    @ApiModelProperty(value="手机号")
    private String phone;

    /**
     * 1正常  0 冻结
     */
    @ApiModelProperty(value="1正常  0 冻结")
    private Short userStatus;

    /**
     * 最后更改密码时间
     */
    @ApiModelProperty(value="最后更改密码时间")
    private Date lastPasswordRestDate;

    /**
     * 最后登录时间
     */
    @ApiModelProperty(value="最后登录时间")
    private Date lastUserLoginDate;

    private static final long serialVersionUID = 1L;
}
