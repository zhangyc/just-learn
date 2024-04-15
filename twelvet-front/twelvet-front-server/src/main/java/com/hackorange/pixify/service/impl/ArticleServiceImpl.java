package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Article;
import com.hackorange.pixify.mapper.ArticleMapper;
import com.hackorange.pixify.service.IArticleService;
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
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
