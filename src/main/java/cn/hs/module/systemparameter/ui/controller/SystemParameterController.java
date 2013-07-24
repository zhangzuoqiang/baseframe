/*
 * $Log: SystemParameterController.java,v $
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:50  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter.ui.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.utils.SpringBeanUtil;
import cn.hs.core.cache.IBaseCache;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.basedata.service.cache.BaseDataCacheUtil;
import cn.hs.module.basedata.service.impl.BaseDataUtil;
import cn.hs.module.systemparameter.domain.SystemParameter;
import cn.hs.module.systemparameter.service.ISystemParameterService;

/**
 * Title: SystemParameterController<br>
 * Description: 系统参数控制器<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-11
 * @version $Revision: 11 $
 */
@Controller
@RequestMapping(value = SystemParameterController.modulePath)
public class SystemParameterController {
	// 模块页面根路径
	protected static final String modulePath = "/module/systemparameter";

	// 系统参数业务层接口
	@Autowired
	private ISystemParameterService systemParameterService;

	// token接口
	@Autowired
	private IToken token;

	@Resource(name = "cacheFactory")
	private IBaseCache baseCache;

	// 数据字典缓存工具
	@Autowired
	private BaseDataCacheUtil baseDataCacheUtil;

	/**
	 * 预维护系统参数
	 * 
	 * @param model
	 * @param request
	 * @return 预维护系统参数页面跳转信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/preMaintainSystemParameter")
	public String preMaintainSystemParameter(Model model, HttpServletRequest request) throws Exception {
		token.saveToken(request);
		Set<String> keys = (Set<String>) baseCache.getAllKey();
		Iterator ite = keys.iterator();
		List<String> sysParas = new ArrayList<String>();
		Map<String, String> sysParameters = new HashMap<String, String>();
		return modulePath + "/maintainSystemParameter.jsp";
	}

	/**
	 * 保存系统参数
	 * 
	 * @param model
	 * @param request
	 * @return 保存系统参数页面跳转信息
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/saveSystemParameter")
	public String saveSystemParameter(Model model, HttpServletRequest request) throws Exception {
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			Enumeration enumeration = request.getParameterNames();
			String parameterName = null;
			List<SystemParameter> systemParameterList = new ArrayList<SystemParameter>();
			SystemParameter systemParameter = null;
			String[] valueArray = null;
			boolean flag = true;
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();
				if (parameterName.indexOf(SystemParameter.PROPERTY_STARTWITH) != -1) {
					valueArray = request.getParameterValues(parameterName);
					if (valueArray.length > 1) {
						flag = false;
						break;
					}
					systemParameter = new SystemParameter();
					systemParameter.setSystemParameterName(parameterName);
					systemParameter.setSystemParameterValue(valueArray[0]);
					systemParameterList.add(systemParameter);
				}
			}
			if (flag) {
				// 保存参数
				systemParameterService.saveSystemParameter(systemParameterList);
			} else {
				model.addAttribute("paraNameIsRepeat", "y");
			}
		}
		return modulePath + "/preMaintainSystemParameter.do";
	}

	/**
	 * 获得所有系统参数
	 * 
	 * @return void
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllSystemParameter")
	public void getAllSystemParameter(Model model) throws Exception {
		List<SystemParameter> systemParameterList = systemParameterService.getAllSystemParameter();
		if (systemParameterList != null && systemParameterList.size() > 0) {
			model.addAttribute("allSystemParameter", systemParameterList);
		}
	}

	/**
	 * 同步数据字典缓存
	 * 
	 * @param model
	 * @throws Exception
	 * @author HuangS
	 * @date Feb 8, 2012
	 */
	@RequestMapping(value = "/synBaseDataCache")
	public @ResponseBody
	JSONObject synBaseDataCache(HttpServletRequest request) throws Exception {
		JSONObject obj = new JSONObject();
		try {
			if (token.isTokenValid(request, true)) {
				// 三个数据字典的缓存全部清除
				baseDataCacheUtil.delBaseData();
				baseDataCacheUtil.delBaseDataList();
				baseDataCacheUtil.delBaseDataTypeIdToTypeCodes();
				// 同步缓存
				BaseDataUtil baseDataUtil = new BaseDataUtil(SpringBeanUtil.getApplicationContext(), request.getSession().getServletContext());
				baseDataUtil.initBaseDataListMap();
				obj.setSuccess(true);
				obj.setMsg("同步数据字典缓存完成");
			} else {
				obj.setSuccess(false);
				obj.setMsg("请勿重复提交");
			}
			return obj;
		} catch (Exception e) {
			throw new Exception("同步数据字典缓存异常");
		}
	}

}
