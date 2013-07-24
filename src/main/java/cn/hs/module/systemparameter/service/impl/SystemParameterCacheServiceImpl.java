/*
 * $Log: SystemParameterCacheServiceImpl.java,v $
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.module.systemparameter.cache.SystemParameterCacheManager;
import cn.hs.module.systemparameter.domain.SystemParameter;
import cn.hs.module.systemparameter.service.ISystemParameterCacheService;
import cn.hs.module.systemparameter.service.ISystemParameterService;

/**
 * Title: SystemParameterCacheServiceImpl<br>
 * Description: 系统参数缓存业务层接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-12
 * @version $Revision: 11 $
 */
@Service(value = "cn.hs.module.systemparameter.service.impl.SystemParameterCacheServiceImpl")
public class SystemParameterCacheServiceImpl implements ISystemParameterCacheService {

	// 系统参数缓存管理
	@Autowired
	private SystemParameterCacheManager systemParameterCacheManager;

	// 系统参数业务层接口
	@Autowired
	private ISystemParameterService systemParameterService;

	/**
	 * 同步缓存
	 * 
	 * @param systemParameterList
	 *            系统参数实体对象集合
	 * @throws Exception
	 */
	public void synchronizeCacheByParameter(List<SystemParameter> systemParameterList) throws Exception {
		systemParameterCacheManager.synchronizeCache(systemParameterList);
	}

	/**
	 * 根据数据库同步缓存
	 * 
	 * @throws Exception
	 */
	public synchronized void synchronizeCacheByDataBase() throws Exception {
		systemParameterCacheManager.synchronizeCache(systemParameterService.getAllSystemParameter());
	}

	public String getSystemParameter(String sysParaKey) {
		return systemParameterCacheManager.getSystemParameterValue(sysParaKey);
	}
}
