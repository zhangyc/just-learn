package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Comment;
import com.hackorange.pixify.mapper.CommentMapper;
import com.hackorange.pixify.service.ICommentService;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

}
