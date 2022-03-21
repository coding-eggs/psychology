package com.lby.psychology.controller;

import com.lby.psychology.config.PsycException;
import com.lby.psychology.config.PsycPasswordEncoder;
import com.lby.psychology.model.ResponseData;
import com.lby.psychology.model.co.PsycUserCo;
import com.lby.psychology.model.common.PageResult;
import com.lby.psychology.model.enums.EnumPermissionType;
import com.lby.psychology.model.enums.EnumResponseType;
import com.lby.psychology.model.pojo.PsycRole;
import com.lby.psychology.model.pojo.PsycUser;
import com.lby.psychology.model.security.SecurityPsycUser;
import com.lby.psychology.model.vo.*;
import com.lby.psychology.service.IUserService;
import com.lby.psychology.util.MinioUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "用户接口API")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private IUserService userService;

    @Autowired
    private PsycPasswordEncoder psycPasswordEncoder;


    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private KeyPair keyPair;

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/getUserInfo")
    public ResponseData<Map<String,Object>> getUserInfo(Authentication authentication){

        Map<String,Object> map = new HashMap<>();
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        //置空密码
        securityPsycUser.setPassword("");
        List<RolePermissionVo> permissionVoList =
                userService.getRolePermissionList(securityPsycUser.getAuthorities().
                        stream().
                        map(PsycRole::getRoleId).
                        collect(Collectors.toList()),
                        EnumPermissionType.PAGE.getId());
        LinkedHashMap<String, List<RolePermissionVo>>  menuMap =
                permissionVoList.stream().
                        collect(Collectors.groupingBy(RolePermissionVo::getGroupName, LinkedHashMap::new,Collectors.toList()));
        map.put("routeMap",menuMap);
        map.put("userinfo",securityPsycUser);
        return new ResponseData<>(map);
    }

    @ApiOperation(value = "更改当前用户头像")
    @PostMapping("/updateCurrentFigureUrl")
    public ResponseData<String> updateCurrentFigureUrl(@RequestParam("file") MultipartFile file,Authentication authentication) throws IOException {

        SecurityPsycUser user = (SecurityPsycUser) authentication.getPrincipal();
        String url;

        try {
            url = minioUtil.uploadFile(file,MinioUtil.DEFAULT_BUCKET);
            //取问号前部分为永久链接
            log.info("用户头像地址：{}",url);
        } catch (Exception e) {
            e.printStackTrace();
            throw new PsycException(EnumResponseType.UPLOAD_FIGURE_URL);
        }
        if(!StringUtils.isEmpty(url)){
            userService.updateFigureUrl(user.getUserId(),url);
        }
        return new ResponseData<>(url);
    }

    @ApiOperation(value = "更改当前用户信息（非账户）")
    @PostMapping("/updateCurrentUserInfo")
    public ResponseData<Boolean> updateCurrentUserInfo(@RequestBody PsycUser user,Authentication authentication){

        boolean result = false;
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        user.setUserId(securityPsycUser.getUserId());
        //设置Spring Security 中得用户信息
        if(userService.updateUserInfo(user)){
            securityPsycUser.setBirthDate(user.getBirthDate());
            securityPsycUser.setNickname(user.getNickname());
            securityPsycUser.setPhone(user.getPhone());
            securityPsycUser.setSex(user.getSex());
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(securityPsycUser, securityPsycUser.getPassword(), authentication.getAuthorities()));
            result = true;
        }
        return new ResponseData<>(result);
    }

    @ApiOperation(value = "更改用户信息（非账户）")
    @PostMapping("/updateUserInfo")
    public ResponseData<Boolean> updateUserInfo(@RequestBody PsycUser user){
        return new ResponseData<>(userService.updateUserInfo(user));
    }


    @ApiOperation(value = "根据邮箱进行注册")
    @PostMapping("/email/registered")
    public ResponseData<Boolean> emailRegistered(@RequestBody @Validated RegisteredUserVo registeredUserVo){
        ResponseData<Boolean> responseData = new ResponseData<>(false);
        if(userService.registeredPsycUser(registeredUserVo)){
            responseData.setData(true);
        }
        return responseData;
    }

    @ApiOperation(value = "验证邮箱")
    @PostMapping("/email/check")
    public ResponseData<Boolean> emailCheck(@RequestBody RegisteredUserVo registeredUserVo){
        userService.checkEmailRegistered(registeredUserVo);
        return new ResponseData<>(true);
    }

    @ApiOperation(value = "获取公钥")
    @GetMapping("/publicKey")
    public ResponseData<String> publicKey() throws Exception {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        return new ResponseData<>("-----BEGIN PUBLIC KEY-----\n" + new String(Base64.encode(publicKey.getEncoded()))
                + "\n-----END PUBLIC KEY-----");
    }

    @ApiOperation(value = "获取用户列表")
    @PostMapping("/userList")
    public ResponseData<PageResult> userList(@RequestBody PsycUserCo co){
        return new ResponseData<>(userService.getUserList(co));
    }

    @ApiOperation(value = "根据用户id删除用户")
    @GetMapping(value = "/deleteByUserId")
    public ResponseData<Boolean> deleteByUserId(Long userId){
        return new ResponseData<>(userService.deleteUserByUserId(userId));
    }

    @ApiOperation(value = "根据用户id查询用户详情")
    @GetMapping(value = "/getUserByUserId")
    public ResponseData<PsycUserVo> getUserByUserId(Long userId){
        return new ResponseData<>(userService.getUserByUserId(userId));
    }

    @ApiOperation(value = "更改当前用户密码")
    @PostMapping(value = "/changeCurrentPassword")
    public ResponseData<Boolean> changeCurrentPassword(@RequestBody @Validated PsycChangePasswordVo changePasswordVo,Authentication authentication){

        String oldPassword = psycPasswordEncoder.encode(psycPasswordEncoder.decodeByRSA(changePasswordVo.getOldPassword()));
        String newPassword = psycPasswordEncoder.encode(psycPasswordEncoder.decodeByRSA(changePasswordVo.getNewPassword()));
        SecurityPsycUser securityPsycUser = (SecurityPsycUser) authentication.getPrincipal();
        //检查密码是否正确
        if(!userService.checkPassword(securityPsycUser.getUserId(),changePasswordVo.getOldPassword())){
            throw new PsycException(EnumResponseType.OLD_PASSWORD_ERROR);
        }
        //两次密码不相同
        if(psycPasswordEncoder.matches(changePasswordVo.getNewPassword(),oldPassword)){
            throw new PsycException(EnumResponseType.TWO_PASSWORD_NOT_SAME);
        }
        changePasswordVo.setNewPassword(newPassword);
        changePasswordVo.setUserId(securityPsycUser.getUserId());
        return new ResponseData<>(userService.updatePassword(changePasswordVo));
    }

    @ApiOperation(value = "更改用户密码")
    @PostMapping(value = "/changePassword")
    public ResponseData<Boolean> changePassword(@RequestBody PsycChangePasswordVo user){
        String newPassword = psycPasswordEncoder.encode(psycPasswordEncoder.decodeByRSA(user.getNewPassword()));
        if(psycPasswordEncoder.matches(user.getNewPassword(),userService.getPasswordByUserId(user.getUserId()))){
            throw new PsycException(EnumResponseType.OLD_NEW_PASSWORD_NOT_SAME);
        }
        user.setNewPassword(newPassword);
        return new ResponseData<>(userService.updatePassword(user));
    }




    @ApiOperation(value = "忘记密码发送验证码")
    @PostMapping(value = "/email/forgetPassword")
    public ResponseData<Boolean> emailForgetPassword(@RequestBody PsycForgetPasswordVo forgetPasswordVo){
        return new ResponseData<>(userService.forgetPassword(forgetPasswordVo));
    }



    @ApiOperation(value = "忘记密码")
    @PostMapping(value = "/forgetPassword")
    public ResponseData<Boolean> forgetPassword(@RequestBody PsycForgetPasswordVo forgetPasswordVo){
        return new ResponseData<>(userService.forgetPassword(forgetPasswordVo));
    }
}
