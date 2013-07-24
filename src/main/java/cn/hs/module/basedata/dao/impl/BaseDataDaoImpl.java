package cn.hs.module.basedata.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.basedata.dao.IBaseDataDao;
import cn.hs.module.basedata.dao.require.BaseDataRequire;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * 
 * Title: BaseDataDaoImpl<br>
 * Description:基础数据类别Dao接口实现 <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 11 $
 */
@Repository(value = "cn.hs.module.basedata.dao.impl.BaseDataDaoImpl")
public class BaseDataDaoImpl implements IBaseDataDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<BaseData> baseDao;
	// 初始化baseDataRequire
	@Autowired
	private BaseDataRequire baseDataRequire;

	/**
	 * 根据ID数组批量作废或启用基础数据
	 * 
	 * @param condition
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public void discardOrReuseBaseData(BaseDataCondition condition) throws Exception {
		baseDao.executeUpdate(baseDataRequire.discardOrReuseBaseDataHQL(condition));
	}

	/**
	 * 根据Id查询基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public BaseData getBaseData(BaseDataCondition condition) throws Exception {
		return baseDao.findObject(BaseData.class, condition.getSearchDataId());
	}

	/**
	 * 根据基础数据类别编码查询基础数据集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getBaseDataList(BaseDataCondition condition) throws Exception {
		baseDataRequire.getBaseDataListHql(condition);
		// 设置不分页查询
		return (List<BaseData>) baseDao.find(condition);
	}

	/**
	 * 更新数据类型
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public void updateBaseData(BaseData baseData) throws Exception {
		baseDao.updateEntityByPK(baseData);
	}

	/**
	 * 添加数据类型
	 * 
	 * @param baseData
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public void addBaseData(BaseData baseData) throws Exception {
		baseDao.addEntity(baseData);
	}

	/**
	 * 新增和更新基础数据前，查看数据编码是否有重复
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public boolean checkDataCodeIsRepeat(BaseData baseData) throws Exception {
		boolean repeat = false;
		Session session = baseDao.getHibernateSession();
		Query query = session.createQuery(baseDataRequire.checkDataCodeIsRepeatHql(baseData));
		baseDataRequire.checkDataCodeIsRepeatParameter(baseData, query);
		Long validation = (Long) query.uniqueResult();
		if (validation.intValue() > 0) {
			repeat = true;
		}
		return repeat;
	}

	/**
	 * 根据基础数据编码查询基础数据信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 23, 2011
	 */
	@Override
	public BaseData viewBaseData(BaseDataCondition condition) throws Exception {
		baseDataRequire.viewBaseDataHql(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (BaseData) result.get(0);
		}
		return null;
	}

	/**
	 * 查询数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> listBaseData(BaseDataCondition condition) throws Exception {
		baseDataRequire.listBaseData(condition);
		return (List<BaseData>) baseDao.pagedQuery(condition);
	}

	/**
	 * count基础数据类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@Override
	public Long listBaseDataCount(BaseDataCondition condition) throws Exception {
		baseDataRequire.countBaseDataHql(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 根据TypeID查询该ID下的所有基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	@SuppressWarnings("unchecked")
	public List<BaseData> getAllBaseDataByTypeID(BaseDataTypeCondition condition) throws Exception {
		baseDataRequire.queryBaseDataByTypeID(condition);
		return (List<BaseData>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
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
		BaseData baseData = baseDao.findObject(BaseData.class, id);
		baseDao.getHibernateSession().clear();
		return baseData == null ? new BaseData() : baseData;
	}

	/**
	 * 根据ID数组获得基础数据 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseData> getBaseDataListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = baseDataRequire.getBaseDataListByIDArrayHQL(idArray);
		return (List<BaseData>) baseDao.find(condition);
	}

}
