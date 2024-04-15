package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Resource;
import com.hackorange.pixify.mapper.ResourceMapper;
import com.hackorange.pixify.service.IResourceService;
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
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

}
