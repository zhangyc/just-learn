package com.hackorange.pixify.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.hackorange.pixify.constant.CommonConst;
import com.hackorange.pixify.dto.*;
import com.hackorange.pixify.enums.LoginTypeEnum;
import com.hackorange.pixify.enums.RoleEnum;
import com.hackorange.pixify.exception.BizException;
import com.hackorange.pixify.mapper.UserInfoMapper;
import com.hackorange.pixify.mapper.UserRoleMapper;
import com.hackorange.pixify.model.UserAuth;
import com.hackorange.pixify.mapper.UserAuthMapper;
import com.hackorange.pixify.model.UserInfo;
import com.hackorange.pixify.model.UserRole;
import com.hackorange.pixify.service.IUserAuthService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hackorange.pixify.vo.*;
import com.twelvet.framework.RedisUtils;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static com.hackorange.pixify.constant.MQPrefixConst.EMAIL_EXCHANGE;
import static com.hackorange.pixify.constant.RedisPrefixConst.CODE_EXPIRE_TIME;
import static com.hackorange.pixify.constant.RedisPrefixConst.USER_CODE_KEY;
import static com.hackorange.pixify.util.CommonUtils.checkEmail;
import static com.hackorange.pixify.util.CommonUtils.getRandomCode;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@Service
public class UserAuthServiceImpl extends ServiceImpl<UserAuthMapper, UserAuth> implements IUserAuthService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserAuthMapper tbUserAuthMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;


    @Override
    public void sendCode(String username) {
// 校验账号是否合法
        if (!checkEmail(username)) {
            throw new BizException("请输入正确邮箱");
        }
        // 生成六位随机验证码发送
        String code = getRandomCode();
        // 发送验证码
        EmailDTO emailDTO = EmailDTO.builder()
                .email(username)
                .subject("验证码")
                .content("您的验证码为 " + code + " 有效期15分钟，请不要告诉他人哦！")
                .build();
        rabbitTemplate.convertAndSend(EMAIL_EXCHANGE, "*", new Message(JSON.toJSONBytes(emailDTO), new MessageProperties()));
        // 将验证码存入redis，设置过期时间为15分钟
        RedisUtils.setCacheObject(USER_CODE_KEY + username, code, CODE_EXPIRE_TIME, TimeUnit.MILLISECONDS);
    }

    @Override
    public List<UserAreaDTO> listUserAreas(ConditionVO conditionVO) {
        return null;
    }

    @Transactional
    @Override
    public void register(UserVO user) {
        // 校验账号是否合法
        if (checkUser(user)) {
            throw new BizException("邮箱已被注册！");
        }
        // 新增用户信息
        UserInfo userInfo = UserInfo.builder()
                .email(user.getUsername())
                .nickname(CommonConst.DEFAULT_NICKNAME + IdWorker.getId())
                .build();
        userInfoMapper.insert(userInfo);
        // 绑定用户角色
        UserRole userRole = UserRole.builder()
                .userId(userInfo.getId())
                .roleId(RoleEnum.USER.getRoleId())
                .build();
        userRoleMapper.insert(userRole);
        // 新增用户账号
        UserAuth userAuth = UserAuth.builder()
                .userInfoId(userInfo.getId())
                .username(user.getUsername())
                .password(BCrypt.hashpw(user.getPassword()))//BCrypt.hashpw(user.getPassword())
                .loginType(LoginTypeEnum.EMAIL.getType())
                .build();
        tbUserAuthMapper.insert(userAuth);

    }

    @Override
    public void logout() {

    }

    @Override
    public String login(String username, String password) {
        try {
            if (StringUtils.isBlank(username)) {
                throw new BizException("用户名不能为空！");
            }
            // 查询账号是否存在
            UserAuth userAuth = tbUserAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                    .select(UserAuth::getId, UserAuth::getUserInfoId, UserAuth::getUsername, UserAuth::getPassword, UserAuth::getLoginType)
                    .eq(UserAuth::getUsername, username));
            if (Objects.isNull(userAuth)) {
                throw new BizException("用户名不存在!");
            }
            if(Objects.equals(BCrypt.hashpw(password), userAuth.getPassword())){
                ///根据用户名查询用户，并且匹配密码是否相等
                // 第1步，先登录上
                StpUtil.login(userAuth.getUsername());  ///
                // 第2步，获取 Token  相关参数
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                return  tokenInfo.getTokenValue();
            }
            ///增加redis管理token
        } catch (Exception e) {
           log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public UserInfoDTO qqLogin(QQLoginVO qqLoginVO) {
        return null;
    }

    @Override
    public UserInfoDTO weiboLogin(WeiboLoginVO weiboLoginVO) {
        return null;
    }

    @Override
    public void updatePassword(UserVO user) {

    }

    @Override
    public void updateAdminPassword(PasswordVO passwordVO) {

    }

    @Override
    public PageResult<UserBackDTO> listUserBackDTO(ConditionVO condition) {
        return null;
    }

    /**
     * 校验用户数据是否合法
     *
     * @param user 用户数据
     * @return 结果
     */
    private Boolean checkUser(UserVO user) {
        if (!user.getCode().equals(RedisUtils.getCacheObject(USER_CODE_KEY + user.getUsername()))) {
            throw new BizException("验证码错误！");
        }
        //查询用户名是否存在
        UserAuth userAuth = tbUserAuthMapper.selectOne(new LambdaQueryWrapper<UserAuth>()
                .select(UserAuth::getUsername)
                .eq(UserAuth::getUsername, user.getUsername()));
        return Objects.nonNull(userAuth);
    }
}
