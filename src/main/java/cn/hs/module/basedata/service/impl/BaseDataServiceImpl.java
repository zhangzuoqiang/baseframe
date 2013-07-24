package cn.hs.module.basedata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.annotation.BusinessLogForAdd;
import cn.hs.core.annotation.BusinessLogForBatchUpdateState;
import cn.hs.core.annotation.BusinessLogForUpdate;
import cn.hs.core.annotation.CreateInfo;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.BasePageCommond;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.pagetemplate.PageTemplate;
import cn.hs.module.basedata.dao.IBaseDataDao;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.domain.jsonbean.BaseDataJsonBean;
import cn.hs.module.basedata.service.IBaseDataService;

/**
 * 
 * Title: IBaseDataServiceImpl<br>
 * Description:基础数据接口实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 73 $
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.basedata.service.impl.BaseDataServiceImpl")
public class BaseDataServiceImpl extends PageTemplate implements IBaseDataService {

	// 基础数据DAO
	@Autowired
	private IBaseDataDao baseDataDao;

	/**
	 * 新增基础数据
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	@BusinessLogForAdd(operationModule = "基础数据")
	@CreateInfo(createTimeMethod = "setCreateTime", creatorMethod = "setCreator")
	public void addBaseData(BaseData baseData) throws Exception {
		baseDataDao.addBaseData(baseData);
		// 同步缓存中的基础资源 modify by GuoR at 2012-8-22
		BaseDataUtil.update(baseData);
	}

	/**
	 * 根据ID数组批量作废或启用基础数据
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	@BusinessLogForBatchUpdateState(entityClass = BaseData.class, getEntityPKMethodName = "getDataID", getObjectsByIDArrayMethodName = "getBaseDataListByIDArray", getPKArrayMethodName = "getBaseDataIds", operationModule = "基础数据", pkArrayClass = String[].class)
	public void discardOrReuseBaseData(BaseDataCondition condition) throws Exception {
		baseDataDao.discardOrReuseBaseData(condition);
		// 同步缓存中资源类型与其对应的数据集合的Map,作废的移除 modify by GuoR at 2012-8-22
		if (condition.getBaseDataIds() != null && condition.getBaseDataIds().length > 0) {
			String[] idArray = condition.getBaseDataIds();
			// 查询出批量启用或作废BaseData集合
			BaseDataUtil.discardOrReuseBaseData(getBaseDataListByIDArray(idArray));
		}
	}

	/**
	 * 根据ID查询基础数据
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public BaseData getBaseData(BaseDataCondition condition) throws Exception {
		return baseDataDao.getBaseData(condition);
	}

	/**
	 * 根据基础数据类别编码查询基础数据集合 modify by HuangS at 2011-09-09
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public List<BaseData> getBaseDataList(BaseDataCondition condition) throws Exception {
		return baseDataDao.getBaseDataList(condition);
	}

	/**
	 * 更新基础数据
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	// @BusinessLogForUpdate(getEntityPKMethodName = "getDataID",
	// getObjectByIDMethodName = "getBaseDataByID", operationModule = "基础数据",
	// pkClass = String.class)
	// @CreateInfo(createTimeMethod = "setCreateTime", creatorMethod =
	// "setCreator")
	public void updateBaseData(BaseData baseData) throws Exception {
		BaseData oldData = baseDataDao.getBaseDataByID(baseData.getDataID());
		baseDataDao.updateBaseData(baseData);
		// 同步缓存前先删除旧缓存再同步 modify by HuangS at 2012-10-19
		if (BaseData.IS_ACTIVE_Y.equals(oldData.getActiveState())) {
			BaseDataUtil.delete(oldData.getDataCode());
		}
		// 同步缓存中的基础资源 modify by GuoR at 2012-8-22
		BaseDataUtil.update(baseData);
	}

	/**
	 * 根据基础数据编码查询基础数据信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public BaseData viewBaseData(BaseDataCondition condition) throws Exception {
		return baseDataDao.viewBaseData(condition);
	}

	/**
	 * 调用PageTemplate模板process方法
	 * 
	 * @param pageCommond
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public JSONObject doProcess(BasePageCommond pageCommond, BaseCondition condition) throws Exception {
		return process(pageCommond, condition);
	}

	/**
	 * 新增和更新基础数据前，查看数据编码是否有重复
	 * 
	 * @param baseData
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public boolean checkDataCodeIsRepeat(BaseData baseData) throws Exception {
		return baseDataDao.checkDataCodeIsRepeat(baseData);
	}

	/**
	 * count查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	protected Long findCount(BaseCondition condition) throws Exception {
		return baseDataDao.listBaseDataCount((BaseDataCondition) condition);
	}

	/**
	 * 查询基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 9, 2011
	 */
	@Override
	protected List<BaseData> findList(BaseCondition condition) throws Exception {
		return baseDataDao.listBaseData((BaseDataCondition) condition);
	}

	/**
	 * 根据ID获得基础数据 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public BaseData getBaseDataByID(String id) throws Exception {
		return baseDataDao.getBaseDataByID(id);
	}

	/**
	 * 根据ID数组获得基础数据 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<BaseData> getBaseDataListByIDArray(String[] idArray) throws Exception {
		return baseDataDao.getBaseDataListByIDArray(idArray);
	}

	@Override
	public List<BaseData> getAllBaseDataByTypeID(BaseDataTypeCondition condition) throws Exception {
		return baseDataDao.getAllBaseDataByTypeID(condition);
	}

	@Override
	protected List<?> objListToJsonList(List<?> pageResultList) throws Exception {
		List<BaseDataJsonBean> result = new ArrayList<BaseDataJsonBean>();
		for (Object object : pageResultList) {
			BaseData baseData = (BaseData) object;
			BaseDataJsonBean bean = new BaseDataJsonBean();
			BeanUtils.copyProperties(baseData, bean);
			bean.setBaseDataTypeID(baseData.getBaseDataType().getTypeID());
			bean.setBaseDataTypeName(baseData.getBaseDataType().getTypeName());
			// bean.setCreatorID(baseData.getCreator().getUserID());
			// bean.setCreatorLoginID(baseData.getCreator().getLoginID());
			// bean.setCreatorName(baseData.getCreator().getUserName());
			result.add(bean);
		}
		return result;
	}

}
