package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Message;
import com.hackorange.pixify.mapper.MessageMapper;
import com.hackorange.pixify.service.IMessageService;
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
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
