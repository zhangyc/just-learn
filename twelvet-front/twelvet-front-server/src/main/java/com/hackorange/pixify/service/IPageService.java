package com.hackorange.pixify.service;

import com.hackorange.pixify.model.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hackorange.pixify.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 页面 服务类
 * </p>
 *
 * @author pixify
 * @since 2024-04-12
 */
public interface IPageService extends IService<Page> {

    /**
     * 保存或更新页面
     *
     * @param pageVO 页面信息
     */
    void saveOrUpdatePage(PageVO pageVO);

    /**
     * 删除页面
     *
     * @param pageId 页面id
     */
    void deletePage(Integer pageId);

    /**
     * 获取页面列表
     *
     * @return {@link List <PageVO>} 页面列表
     */
    List<PageVO> listPages();
}
