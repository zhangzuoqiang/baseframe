/*
 * $Log: SystemParameterDaoImpl.java,v $
 * Revision 1.2  2012/06/01 09:19:58  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.2  2012/06/01 09:15:50  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.1  2012/05/31 04:28:19  ronglt
 * 迁移系统参数到资源展现平台
 *
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:50  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.module.systemparameter.dao.ISystemParameterDao;
import cn.hs.module.systemparameter.domain.SystemParameter;

/**
 * Title: SystemParameterDaoImpl<br>
 * Description: 系统参数持久层接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-11
 * @version $Revision: 11 $
 */
@Repository(value = "cn.hs.module.systemparameter.dao.impl.SystemParameterDaoImpl")
public class SystemParameterDaoImpl implements ISystemParameterDao {

	// 初始化baseDao
	@Autowired
	private IBaseDao<SystemParameter> baseDao;

	/**
	 * 批量插入系统参数
	 * 
	 * @param systemParameterList
	 *            系统参数实体对象集合
	 * @throws Exception
	 */
	public void batchAddSystemParameter(List<SystemParameter> systemParameterList) throws Exception {
		baseDao.batchAddEntities(systemParameterList);
	}

	/**
	 * 删除所有系统参数
	 * 
	 * @throws Exception
	 */
	public void deleteAllSystemParameter() throws Exception {
		Session session = baseDao.getHibernateSession();
		Query query = session.createQuery("delete SystemParameter ");
		query.executeUpdate();
	}

	/**
	 * 获得所有系统参数
	 * 
	 * @return 系统参数实体对象集合List<SystemParameter>
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<SystemParameter> getAllSystemParameter() throws Exception {
		Session session = baseDao.getHibernateSession();
		Query query = session.createQuery(" from SystemParameter ");
		return (List<SystemParameter>) query.list();
	}

	/**
	 * 批量更新系统参数
	 * 
	 * @param sysParameters
	 * @throws Exception
	 * @author RongLT
	 * @date 2012-3-6
	 */
	public void batchUpdateSysParameter(List<SystemParameter> sysParameters) throws Exception {
		for (SystemParameter sysParameter : sysParameters) {
			baseDao.updateEntityByPK(sysParameter);
		}
	}

	/**
	 * 根据KEY值获取系统参数
	 * 
	 * @param sysParaKey
	 *            系统参数KEY
	 * @return 系统参数对象
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public SystemParameter getSystemParameterByKey(String sysParaKey) throws Exception {
		Session session = baseDao.getHibernateSession();
		Query query = session.createQuery(" from SystemParameter where systemParameterName =:systemParameterName");
		query.setParameter("systemParameterName", sysParaKey);
		List<SystemParameter> result = (List<SystemParameter>) query.list();
		if (result != null && !result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
}
