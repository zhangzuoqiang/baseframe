/*
 * $Log: IBaseDataTypeService.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.service;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.IBaseTreeTemplate;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * Title: IBaseTypeService<br>
 * Description: 基础数据类别接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 22, 2011
 * @version $Revision: 11 $
 */
public interface IBaseDataTypeService extends IBaseTreeTemplate {

	/**
	 * 新增基础数据类别
	 * 
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 22, 2011
	 */
	public String addBaseType(BaseDataType baseDataType) throws Exception;

	/**
	 * 根据ID查询基础数据类别
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 22, 2011
	 */
	public BaseDataType getBaseDataType(BaseDataTypeCondition condition)
			throws Exception;

	/**
	 * 更新基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 22, 2011
	 */
	public void updateBaseDataType(BaseDataType baseDataType) throws Exception;

	/**
	 * 根据ID数组作废或启用基础数据类别
	 * 
	 * @param condition
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 22, 2011
	 */
	public void discardOrReuseBaseDataType(BaseDataTypeCondition condition)
			throws Exception;

	/**
	 * 根据基础数据类别ID查询是否存在子基础数据类别或基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 22, 2011
	 */
	public boolean checkIsNotExistsDataOrDataType(
			BaseDataTypeCondition condition) throws Exception;

	/**
	 * 新增和更新数据类别前，查看类别代码是否有重复
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 31, 2011
	 */
	public boolean checkTypeCodeIsRepeat(BaseDataType baseDataType)
			throws Exception;
	/**
	 * 查询资源类别集合
	 * 
	 * @param condition
	 * @return 查询资源类别集合，返回资源类别集合List<BaseDataType>
	 * @throws Exception
	 * @author RongLT
	 * @date 2011-12-13
	 */
	public List<BaseDataType> findBaseDataList(BaseDataTypeCondition condition)
			throws Exception;
	/**
	 * @param condition
	 * @return 根据条件查询数据字典分类列表
	 * @author Limk
	 * @throws Exception
	 */
	public List<BaseDataType> listBaseDataType(BaseDataTypeCondition condition) throws Exception ;
	/**
	 * 根据基础数据类别编码查询
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author Limk
	 * @date Sep 7, 2011
	 */
	public List<BaseDataType> getBaseDataTypeByTypeCode(
			BaseDataTypeCondition condition) throws Exception;
}
