package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Photo;
import com.hackorange.pixify.mapper.PhotoMapper;
import com.hackorange.pixify.service.IPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 照片 服务实现类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements IPhotoService {

}
