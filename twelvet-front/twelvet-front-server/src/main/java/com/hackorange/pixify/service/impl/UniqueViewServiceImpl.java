package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.dto.UniqueViewDTO;
import com.hackorange.pixify.model.UniqueView;
import com.hackorange.pixify.mapper.UniqueViewMapper;
import com.hackorange.pixify.service.IUniqueViewService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@Service
public class UniqueViewServiceImpl extends ServiceImpl<UniqueViewMapper, UniqueView> implements IUniqueViewService {

    @Override
    public List<UniqueViewDTO> listUniqueViews() {
        return null;
    }
}
