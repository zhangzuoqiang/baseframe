/*
 * $Log: MaintenanceLogController.java,v $
 * Revision 1.1  2012/05/23 09:27:53  guor
 * 初次提交
 *
 */
package cn.hs.module.logmanagement.maintenancelog.ui.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLog;
import cn.hs.module.logmanagement.maintenancelog.domain.MaintenanceLogCondition;
import cn.hs.module.logmanagement.maintenancelog.service.IMaintenanceLogService;
import cn.hs.module.logmanagement.maintenancelog.ui.commond.MaintenanceLogCommond;

/**
 * Title: MaintenanceLogController<br>
 * Description: 维护日志控制器<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-9-23
 * @version $Revision: 11 $
 */
@Controller
@RequestMapping(value = MaintenanceLogController.modulePath)
public class MaintenanceLogController {

	// 模块页面根路径
	public static final String modulePath = "/module/logmanagement/maintenancelog";
	// 维护日志业务层接口
	@Autowired
	private IMaintenanceLogService maintenanceLogService;

	/**
	 * 分页查询维护日志信息
	 * 
	 * @param model
	 * @param request
	 * @param maintenanceLogCommond
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/listMaintenanceLog")
	public @ResponseBody
	JSONObject listMaintenanceLog(Model model, HttpServletRequest request, MaintenanceLogCommond maintenanceLogCommond) throws Exception {
		MaintenanceLogCondition condition = new MaintenanceLogCondition();
		BeanUtils.copyProperties(maintenanceLogCommond, condition);
		JSONObject result = maintenanceLogService.doProcess(maintenanceLogCommond, condition);
		return result;
	}

	/**
	 * 查看维护日志
	 * 
	 * @param model
	 * @param request
	 * @param maintenanceLogCommond
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/viewMaintenanceLog")
	public @ResponseBody
	JSONObject viewMaintenanceLog(Model model, HttpServletRequest request, MaintenanceLogCommond maintenanceLogCommond) throws Exception {
		JSONObject object = new JSONObject();
		MaintenanceLogCondition condition = new MaintenanceLogCondition();
		MaintenanceLog maintenanceLog = null;
		// 基础数据类型Id集合是否为空
		if (maintenanceLogCommond.getSearchMaintenanceLogID() != null && !"".equals(maintenanceLogCommond.getSearchMaintenanceLogID())) {
			condition.setSearchMaintenanceLogID(maintenanceLogCommond.getSearchMaintenanceLogID());
			maintenanceLog = maintenanceLogService.getMaintenanceLog(condition);
			BeanUtils.copyProperties(maintenanceLog, maintenanceLogCommond);
			object.setSuccess(true);
			object.setData(maintenanceLog);
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		// return MaintenanceLogController.modulePath +
		// "/viewMaintenanceLog.jsp";
		return object;
	}

}
