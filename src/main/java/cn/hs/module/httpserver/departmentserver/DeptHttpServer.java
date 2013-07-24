package cn.hs.module.httpserver.departmentserver;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.utils.JSONUtils;
import cn.hs.commons.utils.Md5Util;
import cn.hs.commons.utils.PropertyUtil;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;
import cn.hs.module.department.service.IDepartmentService;
import cn.hs.module.httpserver.departmentserver.domain.DeptHttpServerCommond;

/**
 * 
 * Title: DeptHttpServer<br>
 * Description: 部门httpServer<br>
 * Company: HsS<br>
 * Copyright @ 2013 HsS .All rights reserved.<br>
 * @author HuangS
 * @createDate May 13, 2013
 * @version $Revision:$
 */
@Controller
@RequestMapping(value = DeptHttpServer.modulePath)
public class DeptHttpServer {

	protected static final String modulePath = "/httpserver/deptserver";

	@Autowired
	private IDepartmentService departmentService;

	/**
	 * 同步部门
	 * 
	 * @param department
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date May 13, 2013
	 */
	@RequestMapping(value = "/syncDepartment")
	public @ResponseBody
	JSONObject syncDepartment(DeptHttpServerCommond commond) throws Exception {
		JSONObject object = new JSONObject();
		String time = commond.getTime();
		String md5 = commond.getMd5();
		String sid = commond.getSid();
		if (!PropertyUtil.objectNotEmpty(time) || !PropertyUtil.objectNotEmpty(md5) || !PropertyUtil.objectNotEmpty(sid)) {
			object.setSuccess(false);
			object.setMsg("验证信息为空！");
		} else {
			String deptStr = commond.getDeptStr();
			deptStr = URLDecoder.decode(deptStr, "UTF-8");
			Department department = JSONUtils.jsonToObj(deptStr, Department.class);
			String tempMD5 = Md5Util.getMd5(department.getDeptID() + sid + time);
			if (tempMD5.equals(md5)) {
				DepartmentCondition condition = new DepartmentCondition();
				condition.setSearchDepID(department.getDeptID());
				Department dbDept = departmentService.getDepartment(condition);
				if (dbDept != null) {
					departmentService.updateDepartment(department);
				} else {
					List<Department> deptList = new ArrayList<Department>();
					deptList.add(department);
					departmentService.sysDeptWithSys(deptList);
				}
				object.setSuccess(true);
				object.setMsg("同步部门成功！");
			} else {
				object.setSuccess(false);
				object.setMsg("验证失败！");
			}
		}
		return object;
	}

	/**
	 * 部门启用作废同步
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date May 20, 2013
	 */
	@RequestMapping(value = "/syncDepartmentDiscardOrReuse")
	public @ResponseBody
	JSONObject syncDepartmentDiscardOrReuse(DeptHttpServerCommond commond) throws Exception {
		JSONObject object = new JSONObject();
		String time = commond.getTime();
		String md5 = commond.getMd5();
		String sid = commond.getSid();
		if (!PropertyUtil.objectNotEmpty(time) || !PropertyUtil.objectNotEmpty(md5) || !PropertyUtil.objectNotEmpty(sid)) {
			object.setSuccess(false);
			object.setMsg("验证信息为空！");
		} else {
			String[] searchDepIDs = commond.getSearchDeptIDs();
			String tempMD5 = Md5Util.getMd5(searchDepIDs.length + sid + time);
			if (tempMD5.equals(md5)) {
				DepartmentCondition condition = new DepartmentCondition();
				condition.setSearchDepIDs(searchDepIDs);
				condition.setSearchActiveState(commond.getSearchActiveState());
				departmentService.discardOrReuseDepartment(condition);
				object.setSuccess(true);
				object.setMsg("同步部门成功！");
			} else {
				object.setSuccess(false);
				object.setMsg("验证失败！");
			}
		}
		return object;
	}

}
