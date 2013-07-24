/*
 * $Log: SystemParameterServiceImpl.java,v $
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
package cn.hs.module.systemparameter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.annotation.BusinessLogForBatchAdd;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.module.systemparameter.dao.ISystemParameterDao;
import cn.hs.module.systemparameter.domain.SystemParameter;
import cn.hs.module.systemparameter.service.ISystemParameterCacheService;
import cn.hs.module.systemparameter.service.ISystemParameterService;

/**
 * Title: SystemParameterServiceImpl<br>
 * Description: 系统参数业务层接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-11
 * @version $Revision: 11 $
 */
@Service(value = "cn.hs.module.systemparameter.service.impl.SystemParameterServiceImpl")
public class SystemParameterServiceImpl implements ISystemParameterService {

	// 系统参数持久层接口
	@Autowired
	private ISystemParameterDao systemParameterDao;
	// 系统参数缓存业务层接口
	@Autowired
	private ISystemParameterCacheService systemParameterCacheService;

	@Autowired
	private ILog4jManager log4jManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.hs.module.systemparameter.service.ISystemParameterService#getSystemParameter(java.lang.String)
	 */
	@Override
	public String getSystemParameter(String sysParaKey) {
		String value = systemParameterCacheService.getSystemParameter(sysParaKey);
		if (value == null) {
			try {
				SystemParameter systemParameter = systemParameterDao.getSystemParameterByKey(sysParaKey);
				value = systemParameter.getSystemParameterValue();
			} catch (Exception e) {
				log4jManager.debugCustomLog(SystemParameterServiceImpl.class.getName(), "getSystemParameter", "数据库中未存在 " + sysParaKey + " 的值");
			}
		}
		return value;
	}

	/**
	 * 保存系统参数
	 * 
	 * @param systemParameterList
	 *            系统参数实体对象集合
	 * @throws Exception
	 */
	@BusinessLogForBatchAdd(operationModule = "系统参数")
	public synchronized void saveSystemParameter(List<SystemParameter> systemParameterList) throws Exception {
		// modify by RongLT at 2012-03-06 页面点击保存时批量更新系统参数,不再清除整张表的内容 ---start
		if (systemParameterList.size() > 0) {
			// 比较数据库中的系统参数与页面上的系统参数的值，将有变化的系统参数筛选出来
			List<SystemParameter> sysParameters = systemParameterDao.getAllSystemParameter();// 数据库中的系统参数集合
			List<SystemParameter> systemParameters = new ArrayList<SystemParameter>();// 用来装有变化的系统参数的集合
			for (SystemParameter sysParameter : sysParameters) {
				for (SystemParameter systemParameter : systemParameterList) {
					if (systemParameter.getSystemParameterName().equals(sysParameter.getSystemParameterName())) {
						if (!systemParameter.getSystemParameterValue().equals(sysParameter.getSystemParameterValue())) {
							SystemParameter sysPara = new SystemParameter();
							sysPara.setSystemParameterID(sysParameter.getSystemParameterID());
							sysPara.setSystemParameterName(sysParameter.getSystemParameterName());
							sysPara.setSystemParameterValue(systemParameter.getSystemParameterValue());
							systemParameters.add(sysPara);// 将有变化的系统参数装进新集合中
						}
					}
				}
			}
			if (systemParameters != null && systemParameters.size() > 0) {// 如果有更新过的系统参数
				// 将待更新的系统参数更新到数据库
				systemParameterDao.batchUpdateSysParameter(systemParameters);
			}
		}
		// ---end
		// 同步缓存
		systemParameterCacheService.synchronizeCacheByParameter(systemParameterList);
	}

	/**
	 * 获得所有系统参数
	 * 
	 * @return 系统参数实体对象集合List<SystemParameter>
	 * @throws Exception
	 */
	public synchronized List<SystemParameter> getAllSystemParameter() throws Exception {
		return systemParameterDao.getAllSystemParameter();
	}
}
