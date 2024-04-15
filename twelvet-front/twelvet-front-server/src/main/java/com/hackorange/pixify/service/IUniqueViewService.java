package com.hackorange.pixify.service;

import com.hackorange.pixify.dto.UniqueViewDTO;
import com.hackorange.pixify.model.UniqueView;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
public interface IUniqueViewService extends IService<UniqueView> {
    List<UniqueViewDTO> listUniqueViews();

}
