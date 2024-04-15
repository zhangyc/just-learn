package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.UserInfo;
import com.hackorange.pixify.mapper.UserInfoMapper;
import com.hackorange.pixify.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
