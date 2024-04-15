package com.hackorange.pixify.mapper;

import com.hackorange.pixify.dto.UniqueViewDTO;
import com.hackorange.pixify.model.UniqueView;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
public interface UniqueViewMapper extends BaseMapper<UniqueView> {

    /**
     * 获取7天用户量统计
     *
     * @return 用户量
     */
    List<UniqueViewDTO> listUniqueViews();
}
