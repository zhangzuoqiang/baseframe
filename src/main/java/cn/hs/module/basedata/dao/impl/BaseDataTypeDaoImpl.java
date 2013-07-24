package cn.hs.module.basedata.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.basedata.dao.IBaseDataTypeDao;
import cn.hs.module.basedata.dao.require.BaseDataTypeRequire;
import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * 
 * Title: IBaseDataTypeDaoImpl<br>
 * Description: 基础数据类别Dao接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author FanKY
 * @createDate Sep 5, 2011
 * @version $Revision: 72 $
 */
@Repository(value = "cn.hs.module.basedata.dao.impl.BaseDataTypeDaoImpl")
public class BaseDataTypeDaoImpl implements IBaseDataTypeDao {
	// 实例化baseDao
	@Autowired
	private IBaseDao<BaseDataType> baseDao;
	// 实例化baseDataTypeRequire
	@Autowired
	private BaseDataTypeRequire baseDataTypeRequire;

	@Override
	public void updateTreeOrderNum(UpdateOrderParameter updateOrderParameter) throws Exception {
		BaseDataTypeCondition condition = baseDataTypeRequire.updateTreeOrderNumHQL(updateOrderParameter);
		baseDao.executeUpdate(condition);
	}

	@Override
	public void updateTreeParentID(BaseDataTypeCondition condition) throws Exception {
		baseDataTypeRequire.updateTreeParentIDHQL(condition);
		baseDao.executeUpdate(condition);
	}

	/**
	 * 是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	@Override
	public boolean isLeapNode(BaseDataTypeCondition condition) throws Exception {
		boolean result = true;
		baseDataTypeRequire.isLeapNode(condition);
		long countQuery = baseDao.countQuery(condition);
		if (countQuery > 0) {
			result = false;
		}
		return result;
	}

	/**
	 * 查询基础数据类别列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDataType> listBaseDataType(BaseDataTypeCondition condition) throws Exception {
		baseDataTypeRequire.listBaseDataTypeHQL(condition);
		List<BaseDataType> list = (List<BaseDataType>) baseDao.find(condition);
		return list;
	}

	/**
	 * 添加基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void addBaseDataType(BaseDataType baseDataType) throws Exception {
		baseDao.addEntity(baseDataType);
		baseDao.getHibernateSession().flush();
	}

	/**
	 * 根据ID查询基础数据类别
	 * 
	 * @param condition
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataType getBaseDataType(BaseDataTypeCondition condition) throws Exception {
		return (BaseDataType) baseDao.findObject(BaseDataType.class, condition.getSearchTypeID());
	}

	/**
	 * 更新基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void updateBaseDataType(BaseDataType baseDataType) throws Exception {
		baseDao.updateEntityByPK(baseDataType);
	}

	/**
	 * 根据基础数据类别编码查询
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	@SuppressWarnings("unchecked")
	public List<BaseDataType> getBaseDataTypeByTypeCode(BaseDataTypeCondition condition) throws Exception {
		baseDataTypeRequire.queryByTypeCode(condition);
		return (List<BaseDataType>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

	/**
	 * 根据行政区编码查询所有的地址
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author XiangBin
	 * @date Aug 31, 2012
	 */
	@SuppressWarnings("unchecked")
	public List<BaseDataType> listBaseDataTypeByParenCode(BaseDataAddressTreeCondition condition) throws Exception {
		baseDataTypeRequire.queryByTypeParenCode(condition);
		return (List<BaseDataType>) baseDao.find(condition);
	};
}
