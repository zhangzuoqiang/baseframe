package cn.hs.module.basedata.service.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hs.core.cache.CacheFactory;
import cn.hs.core.cache.IBaseCache;
import cn.hs.core.log4j.impl.Log4jManager;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.service.IBaseDataService;
import cn.hs.module.basedata.service.IBaseDataTypeService;

/**
 * 
 * Title: BaseDataCacheManager<br>
 * Description: 数据字典缓存工具类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author HuangS
 * @createDate Jan 10, 2012
 * @version $Revision: 1.1 $
 */
@Component(value = "cn.hs.module.basedata.service.cache.BaseDataCacheUtil")
public class BaseDataCacheUtil {

	/**
	 * 在服务器缓存中存储HashMap<String, BaseData>的key,用来同步不同服务器之间的数据
	 */
	private final String BASE_DATA_MAP = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_MAP";
	/**
	 * 在服务器缓存中存储的数据字典中基本类型的TypeCode与该基本类型下的基础数据对应的Map<String,List<BaseData>>的key ,
	 */
	private final String BASE_DATA_TYPE_MAP = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_TYPE_MAP";
	/**
	 * 在服务器缓存中存储的数据字典中基本类型的TypeID与其TypeCode对应的Map<Integer,String>
	 */
	private final String BASE_DATA_TYPE_ID_TO_CODE = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_TYPE_ID_TO_CODE";

	// 缓存基类
	@Resource(name = "cacheFactory")
	private IBaseCache baseCache;

	// 基础数据类别接口
	@Autowired
	private IBaseDataTypeService baseDataTypeService;

	// 基础数据接口
	@Autowired
	private IBaseDataService baseDataService;

	@Autowired
	private Log4jManager log4jManager;

	public Map<String, BaseData> initBaseData() {
		Map<String, BaseData> dbsMap = new HashMap<String, BaseData>();
		try {
			List<BaseData> baseDataList = baseDataService.getBaseDataList(new BaseDataCondition());
			for (BaseData baseData : baseDataList) {
				dbsMap.put(baseData.getDataCode(), baseData);
			}
			putBaseData(dbsMap);
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(this.getClass().getName(), "init()", "初始化时加载数据字典至缓存出现异常", e);
		}
		return dbsMap;
	}

	/**
	 * 存放不同服务器之间的基础数据
	 * 
	 * @param baseDataMap
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	public void putBaseData(Map<String, BaseData> baseDataMap) {
		baseCache.put(BASE_DATA_MAP, baseDataMap);
	}

	/**
	 * 存放数据字典基础分类所属基础数据集合
	 * 
	 * @param baseDataListMap
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	public void putBaseDataList(Map<String, List<BaseData>> baseDataListMap) {
		baseCache.put(BASE_DATA_TYPE_MAP, baseDataListMap);
	}

	/**
	 * 存放数据字典基础类型ID、CODE关系
	 * 
	 * @param baseDataTypeIdToTypeCodes
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	public void putBaseDataTypeIdToTypeCodes(Map<String, String> baseDataTypeIdToTypeCodes) {
		baseCache.put(BASE_DATA_TYPE_ID_TO_CODE, baseDataTypeIdToTypeCodes);
	}

	/**
	 * 删除不同服务器之间的基础数据缓存
	 * 
	 * @author HuangS
	 * @date Feb 8, 2012
	 */
	public void delBaseData() {
		baseCache.remove(BASE_DATA_MAP);
	}

	/**
	 * 删除数据字典基础分类所属基础数据集合缓存
	 * 
	 * @author HuangS
	 * @date Feb 8, 2012
	 */
	public void delBaseDataList() {
		baseCache.remove(BASE_DATA_TYPE_MAP);
	}

	/**
	 * 删除数据字典基础类型ID、CODE关系缓存
	 * 
	 * @author HuangS
	 * @date Feb 8, 2012
	 */
	public void delBaseDataTypeIdToTypeCodes() {
		baseCache.remove(BASE_DATA_TYPE_ID_TO_CODE);
	}

	/**
	 * 获取不同服务器之间的基础数据
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@SuppressWarnings("unchecked")
	public Map<String, BaseData> getBaseData() throws Exception {
		Map<String, BaseData> baseDataMap = (Map<String, BaseData>) baseCache.get(BASE_DATA_MAP);
		if (baseDataMap == null) {
			// 缓存为空，重新从数据库中查询
			List<BaseData> baseDataList = baseDataService.getBaseDataList(new BaseDataCondition());

			baseDataMap = new HashMap<String, BaseData>();
			for (BaseData baseData : baseDataList) {
				baseDataMap.put(baseData.getDataCode(), baseData);
			}
			// 将查询结果放入缓存
			this.putBaseData(baseDataMap);
		}
		return baseDataMap;
	}

	/**
	 * 获取数据字典基础分类所属基础数据集合
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@SuppressWarnings("unchecked")
	public Map<String, List<BaseData>> getBaseDataList() throws Exception {
		Map<String, List<BaseData>> baseDataListMap = (Map<String, List<BaseData>>) baseCache.get(BASE_DATA_TYPE_MAP);

		if (baseDataListMap == null) {
			// 数据字典中基本类型的TypeCode与该基本类型下的基础数据对应的Map<String,List<BaseData>>
			baseDataListMap = new HashMap<String, List<BaseData>>();

			// 查询数据字典中的所有数据类型
			List<BaseDataType> baseDataTypeList = baseDataTypeService.findBaseDataList(new BaseDataTypeCondition());
			if (baseDataTypeList != null && baseDataTypeList.size() > 0) {
				BaseDataCondition baseDataCondition = new BaseDataCondition();
				for (BaseDataType baseDataType : baseDataTypeList) {
					baseDataCondition.setSearchActiveState(BaseData.IS_ACTIVE_Y + "");
					baseDataCondition.setSearchDataTypeID(baseDataType.getTypeID());
					List<BaseData> baseDataLists = baseDataService.getBaseDataList(baseDataCondition);// 查询该数据类型包含的数据集合
					if (baseDataLists == null || "".equals(baseDataLists)) {
						// 如果该数据类型不包含数据,是其数据集合为空集合
						baseDataLists = new ArrayList<BaseData>();
					}
					baseDataListMap.put(baseDataType.getTypeCode(), baseDataLists);
				}
			}
			// 将数据类型与 其对应数据集合的Map存入服务器缓存
			this.putBaseDataList(baseDataListMap);
		}

		return baseDataListMap;
	}

	/**
	 * 获取数据字典基础类型ID、CODE关系
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> getBaseDataTypeIdToTypeCodes() throws Exception {
		Map<String, String> baseDataTypeIdToTypeCodes = (Map<String, String>) baseCache.get(BASE_DATA_TYPE_ID_TO_CODE);
		if (baseDataTypeIdToTypeCodes == null) {
			// 数据字典中基本类型的TypeID与其TypeCode对应的Map<Integer,String>
			baseDataTypeIdToTypeCodes = new HashMap<String, String>();

			// 查询数据字典中的所有数据类型
			List<BaseDataType> baseDataTypeList = baseDataTypeService.findBaseDataList(new BaseDataTypeCondition());
			if (baseDataTypeList != null && baseDataTypeList.size() > 0) {
				for (BaseDataType baseDataType : baseDataTypeList) {
					baseDataTypeIdToTypeCodes.put(baseDataType.getTypeID(), baseDataType.getTypeCode());
				}
			}
			// 将数据类型的TypeID与该数据类型的TypeCode对应的Map存入服务器缓存
			this.putBaseDataTypeIdToTypeCodes(baseDataTypeIdToTypeCodes);
		}
		return baseDataTypeIdToTypeCodes;
	}

}
