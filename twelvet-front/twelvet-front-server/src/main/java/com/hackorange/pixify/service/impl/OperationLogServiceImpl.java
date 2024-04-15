package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.OperationLog;
import com.hackorange.pixify.mapper.OperationLogMapper;
import com.hackorange.pixify.service.IOperationLogService;
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
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog> implements IOperationLogService {

}
