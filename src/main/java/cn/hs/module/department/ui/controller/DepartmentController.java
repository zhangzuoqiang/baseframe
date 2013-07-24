package cn.hs.module.department.ui.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.DateUtil;
import cn.hs.commons.utils.HttpUtil;
import cn.hs.commons.utils.JSONUtils;
import cn.hs.commons.utils.Md5Util;
import cn.hs.commons.utils.PropertysUtil;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.Constants;
import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.jsonbean.AddressJsonTreeBean;
import cn.hs.module.basedata.service.IBaseDataAddressTreeService;
import cn.hs.module.department.domain.Department;
import cn.hs.module.department.domain.DepartmentCondition;
import cn.hs.module.department.service.IDepartmentService;
import cn.hs.module.department.ui.commond.DepartmentCommond;
import cn.hs.module.department.ui.commond.DepartmentValidCommond;

/**
 * Title: DepartmentController<br>
 * Description: 部门管理控制器<br>
 * Company: goldgov<br>
 * Copyright @ 2012 goldgov .All rights reserved.<br>
 * @author Mill
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Controller
@RequestMapping(value = DepartmentController.modulePath)
public class DepartmentController {
	// 模块页面根路径
	public static final String modulePath = "/module/department";
	// 部门接口
	@Autowired
	private IDepartmentService iDepartmentService;

	// 行政区域tree接口
	@Autowired
	private IBaseDataAddressTreeService addressTreeService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询部门
	 * 
	 * @param model
	 * @param request
	 * @param departmentCommond
	 * @return
	 * @author Mill
	 * @throws Exception
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/listDepartment")
	public @ResponseBody
	JSONObject listDepartment(DepartmentCommond departmentCommond) throws Exception {
		DepartmentCondition condition = new DepartmentCondition();
		BeanUtils.copyProperties(departmentCommond, condition);
		condition.setSearchParentID(departmentCommond.getSearchDepID());
		// if (condition.getSearchActiveState() == null ||
		// "".equals(condition.getSearchActiveState())) {
		// 列表默认查询启用数据
		// condition.setSearchActiveState("1");
		// }
		JSONObject result = iDepartmentService.doProcess(departmentCommond, condition);
		return result;
	}

	/**
	 * 添加部门
	 * 
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/addDepartment")
	public @ResponseBody
	JSONObject addDepartment(HttpServletRequest request, DepartmentValidCommond departmentValidCommond) throws Exception {
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			final Department department = new Department();
			BeanUtils.copyProperties(departmentValidCommond, department);
			department.setActiveState(Department.IS_ACTIVE_Y);
			if (iDepartmentService.checkDeptKindCodeIsRepeat(department)) {
				iDepartmentService.addDepartment(department);
				result.setSuccess(true);
				result.setMsg("添加成功！");
				result.setData(department);

				this.sysDept(request, department);
			} else {
				result.setSuccess(false);
				result.setMsg("编码重复！");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("重复提交！");
		}
		return result;
	}

	/**
	 * 预更新部门
	 * 
	 * @param model
	 * @param request
	 * @param departmentCommond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/preUpdateDepartment")
	public @ResponseBody
	JSONObject preUpdateDepartment(DepartmentValidCommond departmentValidCommond) throws Exception {
		DepartmentCondition departmentCondition = null;
		Department department = null;
		JSONObject object = new JSONObject();
		// 部门Id是否为空
		if (departmentValidCommond.getSearchDepID() != null && !"".equals(departmentValidCommond.getSearchDepID())) {
			departmentCondition = new DepartmentCondition();
			departmentCondition.setSearchDepID(departmentValidCommond.getSearchDepID());
			department = iDepartmentService.getDepartment(departmentCondition);
			BeanUtils.copyProperties(department, departmentValidCommond);
			object.setSuccess(true);
			object.setData(departmentValidCommond);
		} else {
			object.setSuccess(false);
			object.setMsg("需要查询的部门ID为空");
		}
		return object;
	}

	/**
	 * 更新部门
	 * 
	 * @param model
	 * @param request
	 * @param departmentCommond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/updataDepartment")
	public @ResponseBody
	JSONObject updataDepartment(HttpServletRequest request, DepartmentValidCommond departmentValidCommond) throws Exception {
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			final Department department = new Department();
			BeanUtils.copyProperties(departmentValidCommond, department);
			if (iDepartmentService.checkDeptKindCodeIsRepeat(department)) {
				iDepartmentService.updateDepartment(department);
				result.setSuccess(true);
				result.setMsg("修改成功！");
				result.setData(department);

				this.sysDept(request, department);
			} else {
				result.setSuccess(false);
				result.setMsg("编码重复！");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("重复提交！");
		}
		return result;
	}

	/**
	 * 同步部门
	 * 
	 * @param request
	 * @param department
	 * @throws IOException
	 * @author HuangS
	 * @date May 20, 2013
	 */
	private void sysDept(HttpServletRequest request, final Department department) throws IOException {

		int serverPort = request.getServerPort();
		String port = PropertysUtil.getContextProperty("serverPort");
		if (port != null) {
			port = port.trim();
			if (!"".equals(port)) {
				List<String> list = new ArrayList<String>(Arrays.asList(port.split(",")));
				if (list.contains(serverPort + "")) {
					serverPort = 80;
				}
			}
		}
		final String requestURL = request.getServerName() + ":" + serverPort + request.getContextPath();

		final String systemPath = PropertysUtil.getContextProperty(Constants.SYSTEM_PATH);
		// 开启线程同步
		new Thread() {
			@Override
			public void run() {
				String[] sysPaths = systemPath.split(",");
				for (String string : sysPaths) {
					if (string.indexOf(requestURL) == -1) {
						String serverUrl = string + "/httpserver/deptserver/syncDepartment.json?";
						String sid = "1234";
						String time = DateUtil.getCurrentTimeLong().toString();
						String md5 = Md5Util.getMd5(department.getDeptID() + sid + time);
						String deptStr = null;
						try {
							deptStr = JSONUtils.objToJson(department);
							deptStr = URLEncoder.encode(deptStr, "UTF-8");
							serverUrl += "deptStr=" + deptStr;
							serverUrl += "&md5=" + md5;
							serverUrl += "&time=" + time;
							serverUrl += "&sid=" + sid;
							System.out.println(serverUrl);
							String json = HttpUtil.getHttpUrlContent(serverUrl, "utf8", true, "");
							System.out.println(json);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}.start();
	}

	/**
	 * 根据ID数组批量作废或启用部门
	 * 
	 * @param departmentCommond
	 * @return
	 * @throws Exception
	 * @author Mill
	 * @date 2012-08-01
	 */
	@RequestMapping(value = "/discardOrReuseDepartment")
	public @ResponseBody
	JSONObject discardOrReuseDepartment(DepartmentCommond departmentCommond, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		if (departmentCommond.getSearchActiveState() != null && !"".equals(departmentCommond.getSearchActiveState()) && departmentCommond.getSearchDepIDs() != null && departmentCommond.getSearchDepIDs().length > 0) {
			final DepartmentCondition departmentCondition = new DepartmentCondition();
			departmentCondition.setSearchDepIDs(departmentCommond.getSearchDepIDs());
			departmentCondition.setSearchActiveState(departmentCommond.getSearchActiveState());

			if (Department.IS_ACTIVE_N.equals(departmentCondition.getSearchActiveState())) {
				// FIXME 停用公司验证 需要自定义实现
				// VerifyResult verifyResult =
				// iDepartmentService.batchDiscardDepartment(departmentCondition);
				StringBuilder msgStringBuilder = new StringBuilder();
				// if (verifyResult.isFailed()) {
				// String msg = verifyResult.getAllMessagesSplitByBR();
				// msgStringBuilder.append(msg);
				// }else{
				iDepartmentService.discardOrReuseDepartment(departmentCondition);
				result.setSuccess(true);
				msgStringBuilder.append("停用成功");
				// }
				// result.setSuccess(verifyResult.isSuccessful());
				result.setMsg(msgStringBuilder.toString());
			} else {
				iDepartmentService.discardOrReuseDepartment(departmentCondition);
				result.setSuccess(true);
				result.setMsg("启用成功！");
			}
			if (result.isSuccess()) {
				int serverPort = request.getServerPort();
				String port = PropertysUtil.getContextProperty("serverPort");
				if (port != null) {
					port = port.trim();
					if (!"".equals(port)) {
						List<String> list = new ArrayList<String>(Arrays.asList(port.split(",")));
						if (list.contains(serverPort + "")) {
							serverPort = 80;
						}
					}
				}
				final String requestURL = request.getServerName() + ":" + serverPort + request.getContextPath();
				final String systemPath = PropertysUtil.getContextProperty(Constants.SYSTEM_PATH);
				// 开启线程同步
				new Thread() {
					@Override
					public void run() {
						String[] sysPaths = systemPath.split(",");
						for (String string : sysPaths) {
							if (string.indexOf(requestURL) == -1) {
								String serverUrl = string + "/httpserver/deptserver/syncDepartmentDiscardOrReuse.json?";
								String sid = "1234";
								String time = DateUtil.getCurrentTimeLong().toString();
								String[] searchDeptIDs = departmentCondition.getSearchDepIDs();
								Integer searchActiveState = departmentCondition.getSearchActiveState();
								String md5 = Md5Util.getMd5(searchDeptIDs.length + sid + time);
								try {
									serverUrl += "searchActiveState=" + searchActiveState;
									for (String deptID : searchDeptIDs) {
										serverUrl += "&searchDeptIDs=" + deptID;
									}
									serverUrl += "&md5=" + md5;
									serverUrl += "&time=" + time;
									serverUrl += "&sid=" + sid;
									System.out.println(serverUrl);
									String json = HttpUtil.getHttpUrlContent(serverUrl, "utf8", true, "");
									System.out.println(json);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
				}.start();
			}
		} else {
			result.setSuccess(false);
			result.setMsg("未选择！");
		}
		return result;
	}

	/**
	 * 检查部门编码是否重复
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 6, 2012
	 */
	@RequestMapping(value = "/checkDeptCodeIsRepeat")
	public @ResponseBody
	JSONObject checkDeptCodeIsRepeat(DepartmentCommond commond) throws Exception {
		JSONObject result = new JSONObject();
		if (commond.getSearchDeptCode() != null && !"".equals(commond.getSearchDeptCode())) {
			Department department = new Department();
			department.setDeptID(commond.getSearchDepID());
			department.setDeptCode(commond.getSearchDeptCode());
			if (iDepartmentService.checkDeptKindCodeIsRepeat(department)) {
				result.setSuccess(true);
			} else {
				result.setSuccess(false);
				result.setMsg("编码重复！");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("未输入编码！");
		}
		return result;
	}

	/**
	 * 获取选择所属区域树
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 6, 2012
	 */
	@RequestMapping(value = "/getAddressTree")
	public @ResponseBody
	List<AddressJsonTreeBean> getAddressTree(DepartmentCommond commond) throws Exception {
		BaseDataAddressTreeCondition condition = new BaseDataAddressTreeCondition();
		// 部门ID不为空，查询部门所属区域
		if (commond.getSearchDepID() != null && !"".equals(commond.getSearchDepID())) {
			DepartmentCondition departmentCondition = new DepartmentCondition();
			departmentCondition.setSearchDepID(commond.getSearchDepID());
		}
		List<AddressJsonTreeBean> result = addressTreeService.listBaseDataAddressTree(condition);
		return result;
	}
}
