package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Category;
import com.hackorange.pixify.mapper.CategoryMapper;
import com.hackorange.pixify.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
