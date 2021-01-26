package com.lby.psychology.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @program: blog
 * @description:
 * @author: Ailuoli
 * @create: 2019-05-27 14:41
 **/

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QQUserInfo {


    //状态码
    private Integer ret;

    // 错误信息
    private String msg;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 性别
     */
    private String gender;

    /**
     * QQ头像
     */
    private String figureurl_qq_1;

    private String figureurl_qq;

}

