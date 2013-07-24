/*
 * $Log: BaseDataTypeController.java,v $
 * Revision 1.1  2012/05/23 09:27:52  guor
 * 初次提交
 *
 */
package cn.hs.module.basedata.ui.controller;

import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.service.IBaseDataTypeService;
import cn.hs.module.basedata.ui.commond.BaseDataTypeCommond;

/**
 * Title: BaseDataTreeController<br>
 * Description: 基础数据类别Action<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 4, 2011
 * @version $Revision: 73 $
 */
@Controller
@RequestMapping(value = BaseDataTypeController.modulePath)
public class BaseDataTypeController {
	public static final String modulePath = "/module/basedata";

	@Autowired
	private IBaseDataTypeService baseDataTypeService;

	/**
	 * 查询部门tree下一级节点集合
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Jul 24, 2012
	 */
	@RequestMapping(value = "/findBaseDataType")
	public @ResponseBody
	List<JsonTreeBean> findBaseDataType(BaseDataTypeCommond commond) throws Exception {
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		BeanUtils.copyProperties(condition, commond);
		condition.setFindLeapNode(true);
		List<JsonTreeBean> list = baseDataTypeService.doProcess(condition);
		return list;
	}

	/**
	 * 添加基础数据类别
	 * 
	 * @param commond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Jul 24, 2012
	 */
	@RequestMapping(value = "/addBaseDataType")
	public @ResponseBody
	JSONObject addBaseDataType(BaseDataTypeCommond commond) throws Exception {
		JSONObject result = new JSONObject();
		String msg = "";
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		condition.setSearchTypeID(commond.getParentID());
		// 根据ID查询出需要增加的父项
		BaseDataType parentBase = baseDataTypeService.getBaseDataType(condition);
		if (BaseDataType.DATA_DICTIONARY_ID.equals(parentBase.getTypeID())) {
			// 数据字典节点下不能添加子节点
			result.setSuccess(false);
			result.setMsg("数据字典节点下不能添加子节点");
			return result;
		} else {
			// 非数据字典节点下添加子节点
			// 设置基础数据类别值
			BaseDataType baseDataType = new BaseDataType();
			baseDataType.setParentID(commond.getParentID());
			baseDataType.setTypeCode(commond.getTypeCode());
			baseDataType.setTypeName(commond.getTypeName());
			baseDataType.setActiveState(BaseDataType.IS_ACTIVE_Y);
			if (baseDataTypeService.checkTypeCodeIsRepeat(baseDataType)) {
				// 编码不重复
				baseDataTypeService.addBaseType(baseDataType);
				result.setSuccess(true);
				result.setMsg("添加成功");
				result.setData(baseDataType);
			} else {
				msg = "编码重复";
				result.setMsg(msg);
				result.setSuccess(false);
			}
		}
		return result;
	}

	/**
	 * 预更新
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 8, 2011
	 */
	@RequestMapping(value = "/preUpdateBaseDataType")
	public @ResponseBody
	JSONObject preUpdateBaseDataType(BaseDataTypeCommond commond) throws Exception {
		JSONObject result = new JSONObject();
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		condition.setSearchTypeID(commond.getTypeID());
		// 根据ID查询出基础数据类别
		BaseDataType baseDataType = baseDataTypeService.getBaseDataType(condition);
		// 数据字典,公共字典,本系统字典三个节点不能修改
		if (baseDataType.getTypeID() == BaseDataType.DATA_DICTIONARY_ID) {
			baseDataType = null;
			result.setSuccess(false);
			result.setMsg("数据字典不能修改");
		} else if (baseDataType.getTypeID() == BaseDataType.PUBLIC_DICTIONARY_ID) {
			baseDataType = null;
			result.setSuccess(false);
			result.setMsg("公共字典不能修改");
		} else if (baseDataType.getTypeID() == BaseDataType.SYSTEM_DICTIONARY_ID) {
			baseDataType = null;
			result.setSuccess(false);
			result.setMsg("本系统字典不能修改");
			throw new Exception("测试");
		} else {
			result.setSuccess(true);
		}
		result.setData(baseDataType);
		return result;
	}

	/**
	 * 更新基础数据类别
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	@RequestMapping(value = "/updateBaseDataType")
	public @ResponseBody
	JSONObject updateBaseDataType(BaseDataTypeCommond commond) throws Exception {
		JSONObject result = new JSONObject();
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		condition.setSearchTypeID(commond.getTypeID());
		// 根据ID查询出基础数据类别
		BaseDataType baseDataType = baseDataTypeService.getBaseDataType(condition);
		BaseDataType temp = new BaseDataType();
		temp.setTypeID(commond.getTypeID());
		temp.setTypeCode(commond.getTypeCode());
		if (baseDataTypeService.checkTypeCodeIsRepeat(temp)) {
			// 设置基础数据类别值
			baseDataType.setTypeCode(commond.getTypeCode());
			baseDataType.setTypeName(commond.getTypeName());
			// 编码不重复，修改
			baseDataTypeService.updateBaseDataType(baseDataType);
			result.setMsg("修改成功");
			result.setSuccess(true);
			result.setData(baseDataType);
		} else {
			result.setMsg("编码重复");
			result.setSuccess(false);
			result.setData(baseDataType);
		}
		return result;
	}

	/**
	 * 作废基础数据类别
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	@RequestMapping(value = "/discardBaseDataType")
	public @ResponseBody
	JSONObject discardBaseDataType(BaseDataTypeCommond commond) throws Exception {
		JSONObject result = new JSONObject();
		result.setSuccess(false);
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		condition.setSearchTypeID(commond.getTypeID());
		condition.setNode(commond.getTypeID().toString());
		// 数据字典,公共字典,本系统字典三个节点不能移除
		if (commond.getTypeID() == BaseDataType.DATA_DICTIONARY_ID) {
			result.setMsg("数据字典不能删除");
			return result;
		} else if (commond.getTypeID() == BaseDataType.PUBLIC_DICTIONARY_ID) {
			result.setMsg("公共字典不能删除");
			return result;
		} else if (commond.getTypeID() == BaseDataType.SYSTEM_DICTIONARY_ID) {
			result.setMsg("本系统字典不能删除");
			return result;
		}
		// 不是数据字典,公共字典,本系统字典三个节点再判断是否有子节点和和基础数据
		if (!baseDataTypeService.checkIsNotExistsDataOrDataType(condition)) {
			baseDataTypeService.discardOrReuseBaseDataType(condition);
			result.setSuccess(true);
			result.setMsg("删除成功");
		} else {
			result.setMsg("存在子信息或基础数据");
		}
		return result;
	}
}
