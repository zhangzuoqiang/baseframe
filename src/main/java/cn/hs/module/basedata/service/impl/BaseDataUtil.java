package cn.hs.module.basedata.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;

import cn.hs.core.cache.CacheFactory;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.service.IBaseDataService;
import cn.hs.module.basedata.service.IBaseDataTypeService;
import cn.hs.module.basedata.service.cache.BaseDataCacheUtil;

public class BaseDataUtil implements Cloneable {

	private static ApplicationContext ac = null;
	private IBaseDataService baseDataService = null;
	private IBaseDataTypeService baseDataTypeService = null;
	private static ServletContext servletContext;
	// modify by RongLT 2011-10-17
	// 在服务器缓存中存储HashMap<String, BaseData>的key,用来同步不同服务器之间的数据
	public final static String BASE_DATA_MAP = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_MAP";
	// modify by RongLT at 2011-12-13
	// 在服务器缓存中存储的数据字典中基本类型的TypeCode与该基本类型下的基础数据对应的Map<String,
	// List<BaseData>>的key,
	public final static String BASE_DATA_TYPE_MAP = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_TYPE_MAP";
	// 在服务器缓存中存储的数据字典中基本类型的TypeID与其TypeCode对应的Map<Integer,String>
	public final static String BASE_DATA_TYPE_ID_TO_CODE = CacheFactory.PREFIX_CACHE_KEY + "BASE_DATA_TYPE_ID_TO_CODE";

	private static BaseDataCacheUtil baseDataCacheUtil;

	public BaseDataUtil(ApplicationContext ac, ServletContext sc) {
		this.ac = ac;
		servletContext = sc;
	}

	/**
	 * 初始化时加载数据字典至缓存
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	public Map<String, BaseData> init() {
		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");
		Map<String, BaseData> initBaseData = baseDataCacheUtil.initBaseData();
		servletContext.setAttribute("baseDataMap", initBaseData);
		return initBaseData;
	}

	/**
	 * 更新系统缓存中的数据字典中的基础数据缓存
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@SuppressWarnings("unchecked")
	public static void update(BaseData baseData) throws Exception {

		Map<String, BaseData> dbMapClone = (Map<String, BaseData>) ((HashMap<String, BaseData>) servletContext.getAttribute("baseDataMap")).clone();

		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");
		// baseCache = (IBaseCache) ac.getBean("cacheFactory");// 获取服务器缓存
		baseDataCacheUtil.putBaseData(dbMapClone);// 同步数据到服务器缓存
		// baseCache.put(BASE_DATA_MAP, dbMapClone);
		servletContext.setAttribute("baseDataMap", dbMapClone);
		Map<String, List<BaseData>> baseDataTypes = (Map<String, List<BaseData>>) baseDataCacheUtil.getBaseDataList();
		Map<String, String> baseDataTypeIdToCodes = (Map<String, String>) baseDataCacheUtil.getBaseDataTypeIdToTypeCodes();
		String dataTypeCode = null;
		if (baseData.getBaseDataType() != null && !"".equals(baseData.getBaseDataType())) {
			// 修改为更新基础数据时查询基础数据类别并放入基础数据中 modify by HuangS at 2012-10-23
			IBaseDataTypeService baseDataTypeService = (IBaseDataTypeService) ac.getBean("cn.hs.module.basedata.service.impl.BaseDataTypeServiceImpl");
			BaseDataTypeCondition condition = new BaseDataTypeCondition();
			condition.setSearchTypeID(baseData.getBaseDataType().getTypeID());
			BaseDataType baseDataType = baseDataTypeService.getBaseDataType(condition);
			baseData.setBaseDataType(baseDataType);
			if (baseDataTypeIdToCodes.containsKey(baseData.getBaseDataType().getTypeID())) {
				dataTypeCode = baseDataTypeIdToCodes.get(baseData.getBaseDataType().getTypeID());
			}
			List<BaseData> baseDatas = null;
			if (dataTypeCode != null && !"".equals(dataTypeCode)) {
				baseDatas = baseDataTypes.get(dataTypeCode);
				if (baseDatas == null || "".equals(baseDatas)) {
					baseDatas = new ArrayList<BaseData>();
				}
				Iterator<BaseData> ite = baseDatas.iterator();
				while (ite.hasNext()) {
					BaseData bData = (BaseData) ite.next();
					if (bData.getDataCode().equalsIgnoreCase(baseData.getDataCode())) {
						ite.remove();// 不管启用状态,先将该数据从缓存中移除
						break;
					}
				}
				if (baseData.getActiveState().intValue() == BaseData.IS_ACTIVE_Y.intValue()) {// 当为启用状态时,再将该数据加回缓存
					baseDatas.add(baseData);
					baseData.getBaseDataType().setTypeCode(dataTypeCode);
					dbMapClone.put(baseData.getDataCode(), baseData);
				}
				baseDataTypes.put(dataTypeCode, baseDatas);
				baseDataCacheUtil.putBaseDataList(baseDataTypes);
				// baseCache.put(BASE_DATA_TYPE_MAP, baseDataTypes);
			}
		}
	}

	/**
	 * 删除
	 * 
	 * @param dataCodes
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	@SuppressWarnings("unchecked")
	public static void delete(String... dataCodes) throws Exception {
		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");
		Map<String, BaseData> dbMapClone = (Map<String, BaseData>) ((HashMap<String, BaseData>) servletContext.getAttribute("baseDataMap")).clone();

		Map<String, List<BaseData>> baseDataTypes = baseDataCacheUtil.getBaseDataList();
		Map<String, String> baseDataTypeIdToCodes = baseDataCacheUtil.getBaseDataTypeIdToTypeCodes();
		// Map<String, List<BaseData>> baseDataTypes = (Map<String,
		// List<BaseData>>) baseCache.get(BASE_DATA_TYPE_MAP);

		// Map<Integer, String> baseDataTypeIdToCodes = (Map<Integer, String>)
		// baseCache.get(BASE_DATA_TYPE_ID_TO_CODE);

		for (String dataCode : dataCodes) {
			String dataId = dbMapClone.get(dataCode).getDataID();
			dbMapClone.remove(dataCode);
			if (baseDataTypes.containsKey(dataCode)) {
				baseDataTypes.remove(dataCode);
				baseDataTypeIdToCodes.remove(dataId);
			}
		}
		// baseCache = (IBaseCache) ac.getBean("cacheFactory");// 获取服务器缓存
		baseDataCacheUtil.putBaseData(dbMapClone);// 同步数据到服务器缓存
		baseDataCacheUtil.putBaseDataList(baseDataTypes);
		baseDataCacheUtil.putBaseDataTypeIdToTypeCodes(baseDataTypeIdToCodes);
		// baseCache.put(BASE_DATA_MAP, dbMapClone);
		// baseCache.put(BASE_DATA_TYPE_MAP, baseDataTypes);
		// baseCache.put(BASE_DATA_TYPE_ID_TO_CODE, baseDataTypeIdToCodes);
		servletContext.setAttribute("baseDataMap", dbMapClone);

	}

	/**
	 * 当字典内容批量变化时更新
	 * 
	 * @param baseDatas
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static void discardOrReuseBaseData(List<BaseData> baseDatas) throws Exception {
		Map<String, BaseData> dbMapClone = (Map<String, BaseData>) ((HashMap<String, BaseData>) servletContext.getAttribute("baseDataMap")).clone();
		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");

		Map<String, List<BaseData>> baseDataTypes = baseDataCacheUtil.getBaseDataList();// 缓存中数据字典基础类别与其对应的基础数据集合的Map
		Map<String, String> baseDataTypeIdToCodes = baseDataCacheUtil.getBaseDataTypeIdToTypeCodes();// 缓存中数据字典类别的ID与它的Code对应的Map
		for (BaseData bd : baseDatas) {
			List<BaseData> bds = new ArrayList<BaseData>();
			if (bd.getBaseDataType() != null && !"".equals(bd.getBaseDataType())) {// 判空
				String dataId = bd.getBaseDataType().getTypeID();// 该基础数据对应的类别的ID
				if (baseDataTypeIdToCodes.containsKey(dataId)) {// 判空
					bds = baseDataTypes.get(baseDataTypeIdToCodes.get(dataId));
					if (bd.getActiveState().intValue() == BaseData.IS_ACTIVE_Y.intValue()) {// 启用时往缓存中增加
						bds.add(bd);
						bd.getBaseDataType().setTypeCode(baseDataTypeIdToCodes.get(dataId));
						dbMapClone.put(bd.getDataCode(), bd);
					} else if (bd.getActiveState().intValue() == BaseData.IS_ACTIVE_N.intValue()) {// 删除时从缓存中移除
						Iterator<BaseData> ite = bds.iterator();
						while (ite.hasNext()) {
							BaseData baseData = (BaseData) ite.next();
							if (baseData.getDataCode().equals(bd.getDataCode())) {
								ite.remove();
								dbMapClone.remove(baseData.getDataCode());
							}
						}
					}
					baseDataTypes.put(baseDataTypeIdToCodes.get(dataId), bds);// 同步该类型下的数据集合
					baseDataCacheUtil.putBaseDataList(baseDataTypes);
				}
			}
		}
		baseDataCacheUtil.putBaseData(dbMapClone);// 同步数据到服务器缓存
		servletContext.setAttribute("baseDataMap", dbMapClone);
	}

	/**
	 * 初始化服务器缓存,将基础类型及其包含的基础数据集合的Map和基础类型的ID与其Code对应的Map存入缓存,
	 * 
	 * @throws Exception
	 * @author RongLT
	 * @date 2011-12-14
	 */
	public void initBaseDataListMap() throws Exception {
		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");
		// 数据字典中基本类型的TypeCode与该基本类型下的基础数据对应的Map<String,List<BaseData>>
		Map<String, List<BaseData>> baseDataListMap = new HashMap<String, List<BaseData>>();
		// 数据字典中基本类型的TypeID与其TypeCode对应的Map<Integer,String>
		Map<String, String> baseDataTypeIdToCodes = new HashMap<String, String>();
		baseDataTypeService = (IBaseDataTypeService) ac.getBean("cn.hs.module.basedata.service.impl.BaseDataTypeServiceImpl");
		baseDataService = (IBaseDataService) ac.getBean("cn.hs.module.basedata.service.impl.BaseDataServiceImpl");
		// 查询数据字典中的所有数据类型
		List<BaseDataType> bdts = baseDataTypeService.findBaseDataList(new BaseDataTypeCondition());

		Map<String, BaseData> dbsMap = new HashMap<String, BaseData>();

		if (bdts != null && bdts.size() > 0) {
			BaseDataCondition baseDataCondition = new BaseDataCondition();
			for (BaseDataType bdt : bdts) {
				baseDataCondition.setSearchActiveState(BaseData.IS_ACTIVE_Y + "");
				baseDataCondition.setSearchDataTypeID(bdt.getTypeID());
				List<BaseData> baseDataLists = baseDataService.getBaseDataList(baseDataCondition);// 查询该数据类型包含的数据集合
				if (baseDataLists == null || "".equals(baseDataLists)) {
					// 如果该数据类型不包含数据,是其数据集合为空集合
					baseDataLists = new ArrayList<BaseData>();
				} else {
					for (BaseData baseData : baseDataLists) {
						baseData.setBaseDataType(bdt);
						dbsMap.put(baseData.getDataCode(), baseData);
					}
				}
				baseDataListMap.put(bdt.getTypeCode(), baseDataLists);
				baseDataTypeIdToCodes.put(bdt.getTypeID(), bdt.getTypeCode());
			}
		}
		// 将数据类型与 其对应数据集合的Map存入服务器缓存
		baseDataCacheUtil.putBaseDataList(baseDataListMap);
		baseDataCacheUtil.putBaseDataTypeIdToTypeCodes(baseDataTypeIdToCodes);
		baseDataCacheUtil.putBaseData(dbsMap);
		servletContext.setAttribute("baseDataMap", dbsMap);
	}

	/**
	 * 通过基础数据编码获得该基础数据对象
	 * 
	 * @param dataCode
	 *            基础数据编码
	 * @return 该基础数据编码对应的基础数据对象
	 * @author RongLT
	 * @date 2011-12-15
	 */
	public static BaseData getBaseDataType(String dataCode) throws Exception {
		// baseCache = (IBaseCache) ac.getBean("cacheFactory");
		baseDataCacheUtil = (BaseDataCacheUtil) ac.getBean("cn.hs.module.basedata.service.cache.BaseDataCacheUtil");
		Map<String, BaseData> baseDatas = baseDataCacheUtil.getBaseData();
		// Map<String, BaseData> baseDatas = (Map<String, BaseData>)
		// baseCache.get(BASE_DATA_MAP);// 获得缓存中基础数据编码及其对应的基础数据对象的Map
		BaseData baseData = new BaseData();
		if (baseDatas.containsKey(dataCode)) {
			baseData = baseDatas.get(dataCode);
		}
		return baseData;
	}
}
