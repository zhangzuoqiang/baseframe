/*
 * $Log: IBaseDataDao.java,v $
 * Revision 1.1  2012/05/23 09:27:51  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.dao;

import java.util.List;

import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * Title: IBaseDataDao<br>
 * Description: 数据字典Dao接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 11 $
 */
public interface IBaseDataDao {
	/**
	 * 新增数据类型
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public void addBaseData(BaseData baseData) throws Exception;

	/**
	 * 根据ID数组批量作废或启用基础数据
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public void discardOrReuseBaseData(BaseDataCondition condition) throws Exception;

	/**
	 * 根据Id查询基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public BaseData getBaseData(BaseDataCondition condition) throws Exception;

	/**
	 * 根据基础数据类别编码查询基础数据集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public List<BaseData> getBaseDataList(BaseDataCondition condition) throws Exception;

	/**
	 * 更新数据类型
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public void updateBaseData(BaseData baseData) throws Exception;

	/**
	 * 根据基础数据编码查询基础数据信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 23, 2011
	 */
	public BaseData viewBaseData(BaseDataCondition condition) throws Exception;

	/**
	 * 新增和更新基础数据前，查看数据编码是否有重复
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public boolean checkDataCodeIsRepeat(BaseData baseData) throws Exception;

	/**
	 * 查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public List<BaseData> listBaseData(BaseDataCondition condition) throws Exception;

	/**
	 * count查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public Long listBaseDataCount(BaseDataCondition condition) throws Exception;

	/**
	 * 根据TypeID查询该ID下的所有基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public List<BaseData> getAllBaseDataByTypeID(BaseDataTypeCondition condition) throws Exception;

	/**
	 * 根据ID数组获得基础数据 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<BaseData> getBaseDataListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得基础数据 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public BaseData getBaseDataByID(String id) throws Exception;

}
