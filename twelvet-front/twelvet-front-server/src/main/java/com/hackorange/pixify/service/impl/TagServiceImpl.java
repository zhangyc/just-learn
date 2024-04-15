package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Tag;
import com.hackorange.pixify.mapper.TagMapper;
import com.hackorange.pixify.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
