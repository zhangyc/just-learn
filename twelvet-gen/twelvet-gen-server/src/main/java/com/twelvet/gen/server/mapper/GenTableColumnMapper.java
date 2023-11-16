package com.twelvet.gen.server.mapper;

import com.twelvet.gen.api.domain.GenTableColumn;

import java.util.List;

/**
 * @author twelvet
 * @WebSite twelvet.cn
 * @Description: 业务字段 数据层
 */
public interface GenTableColumnMapper {

	/**
	 * 查询业务字段列表
	 * @param tableId 业务字段编号
	 * @return 业务字段集合
	 */
	List<GenTableColumn> selectGenTableColumnListByTableId(Long tableId);

	/**
	 * 新增业务字段
	 * @param genTableColumn 业务字段信息
	 * @return 结果
	 */
	int insertGenTableColumn(GenTableColumn genTableColumn);

	/**
	 * 修改业务字段
	 * @param genTableColumn 业务字段信息
	 * @return 结果
	 */
	int updateGenTableColumn(GenTableColumn genTableColumn);

	/**
	 * 删除业务字段
	 * @param genTableColumns 列数据
	 * @return 结果
	 */
	int deleteGenTableColumns(List<GenTableColumn> genTableColumns);

	/**
	 * 批量删除业务字段
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	int deleteGenTableColumnByIds(Long[] ids);

}
