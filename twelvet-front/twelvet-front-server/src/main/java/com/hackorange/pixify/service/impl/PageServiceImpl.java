package com.hackorange.pixify.service.impl;

import com.hackorange.pixify.model.Page;
import com.hackorange.pixify.mapper.PageMapper;
import com.hackorange.pixify.service.IPageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hackorange.pixify.vo.PageVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 页面 服务实现类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
@Service
public class PageServiceImpl extends ServiceImpl<PageMapper, Page> implements IPageService {

    @Override
    public void saveOrUpdatePage(PageVO pageVO) {

    }

    @Override
    public void deletePage(Integer pageId) {

    }

    @Override
    public List<PageVO> listPages() {
        return null;
    }
}
