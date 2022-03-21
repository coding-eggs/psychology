package com.lby.psychology.mapper;

import com.lby.psychology.model.co.PsycUserCo;
import com.lby.psychology.model.pojo.PsycUser;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.PsycUserVo;
import com.lby.psychology.model.vo.RegisteredUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Mapper
public interface PsycUserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(PsycUser record);

    int insertSelective(PsycUser record);

    PsycUser selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(PsycUser record);

    int updateByPrimaryKey(PsycUser record);

    //根据用户名（邮箱）查询用户
    SecurityPsycUser selectPsycUserByEmail(String email);

    //查询openId是否存在
    int selectCountByOpenId (@Param("openId") String openId,@Param("authType") Integer authType);

    //更新qq登录信息
    int updatePsycUserQQLogin(SecurityPsycUser user);

    //插入qq登录信息
    int insertPsycUserQQLogin(SecurityPsycUser user);

    //根据openid查询用户信息
    SecurityPsycUser selectUserByOpenId(String openId);

    //更新最后登录时间
    int updateLastLoginDate(@Param("userId") Long userId, @Param("now") LocalDateTime localDateTime);

    //查询该邮箱是否存在
    int selectEmailCount(@Param("email") String email);

    //插入新用户
    int insertPsycUser(RegisteredUserVo registeredUserVo);

    //查询用户列表
    List<PsycUserVo> selectPsycUser(PsycUserCo psycUserCo);

    int deleteUserRole(Long userId);

    PsycUserVo selectUserByUserId(Long userId);

    int updateFigureUrl(@Param("userId") Long userId,@Param("url") String url);

    int updateUserInfo(PsycUser user);

    String selectPassword(@Param("userId") Long userId);

    int updatePassword(@Param("userId") Long userId,@Param("password") String password);

    String selectPasswordByUserId(@Param("userId") Long userId);

    int updatePasswordByEmail(@Param("mail") String mail,@Param("password") String password);
}
