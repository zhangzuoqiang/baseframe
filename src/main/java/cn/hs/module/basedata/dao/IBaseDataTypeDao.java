package cn.hs.module.basedata.dao;

import java.util.List;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * 
 * Title: IBaseDataTypeDao<br>
 * Description: 基础数据类别Dao接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 5, 2011
 * @version $Revision: 72 $
 */
public interface IBaseDataTypeDao {

	/**
	 * 查询基础数据类别列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	public List<BaseDataType> listBaseDataType(
			BaseDataTypeCondition condition) throws Exception;

	/**
	 * 是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	public boolean isLeapNode(BaseDataTypeCondition condition) throws Exception;

	/**
	 * 添加基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void addBaseDataType(BaseDataType baseDataType) throws Exception;

	/**
	 * 根据ID查询基础数据类别
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataType getBaseDataType(BaseDataTypeCondition condition)
			throws Exception;

	/**
	 * 更新基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void updateBaseDataType(BaseDataType baseDataType) throws Exception;

	/**
	 * 根据基础数据类别编码查询
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public List<BaseDataType> getBaseDataTypeByTypeCode(
			BaseDataTypeCondition condition) throws Exception;

	public void updateTreeOrderNum(UpdateOrderParameter updateOrderParameter)
			throws Exception;

	public void updateTreeParentID(BaseDataTypeCondition condition)
			throws Exception;

	
	
	/**
	 * 根据行政区编码查询所有的地址
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 31, 2012
	 */
	public List<BaseDataType> listBaseDataTypeByParenCode(
			BaseDataAddressTreeCondition condition)throws Exception;
}
