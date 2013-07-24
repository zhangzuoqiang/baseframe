/*
 * $Log: ISystemParameterService.java,v $
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
package cn.hs.module.systemparameter.service;

import java.util.List;

import cn.hs.module.systemparameter.domain.SystemParameter;

/**
 * Title: ISystemParameter<br>
 * Description: 系统参数业务层接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-10
 * @version $Revision: 11 $
 */
public interface ISystemParameterService {

	/**
	 * 保存系统参数
	 * 
	 * @param systemParameterList
	 *            系统参数实体对象集合
	 * @throws Exception
	 */
	public void saveSystemParameter(List<SystemParameter> systemParameterList)
			throws Exception;

	/**
	 * 获得所有系统参数
	 * 
	 * @return 系统参数实体对象集合List<SystemParameter>
	 * @throws Exception
	 */
	public List<SystemParameter> getAllSystemParameter() throws Exception;
	/**
	 * 根据KEY值获取系统参数
	 * @param sysParaKey 系统参数KEY
	 * @return 系统参数值
	 * @author GuoR
	 */
	public String getSystemParameter(String sysParaKey);
}
