package cn.hs.module.basedata.ui.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.hs.commons.DateUtil;
import cn.hs.core.page.commond.JSONObject;
import cn.hs.core.util.token.IToken;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.service.IBaseDataService;
import cn.hs.module.basedata.service.IBaseDataTypeService;
import cn.hs.module.basedata.ui.commond.BaseDataCommond;
import cn.hs.module.basedata.ui.commond.BaseDataValidCommond;

/**
 * 
 * Title: BaseDataController<br>
 * Description: 基础数据类型Action<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 73 $
 */
@Controller
@RequestMapping(value = BaseDataController.modulePath)
public class BaseDataController {
	// 模块页面根路径
	public static final String modulePath = "/module/basedata";
	// 基础数据类型接口
	@Autowired
	private IBaseDataService iBaseDataService;

	@Autowired
	private IBaseDataTypeService baseDataTypeService;

	// token接口
	@Autowired
	private IToken token;

	/**
	 * 列表查询基础数据
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @author FanKY
	 * @throws Exception
	 * @date Aug 31, 2011
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/listBaseData")
	public @ResponseBody
	JSONObject listBaseData(BaseDataCommond baseDataCommond) throws Exception {
		BaseDataCondition condition = new BaseDataCondition();
		BeanUtils.copyProperties(baseDataCommond, condition);
		if (condition.getSearchActiveState() == null || "".equals(condition.getSearchActiveState())) {
			// 列表默认查询启用数据
			condition.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		}
		JSONObject result = iBaseDataService.doProcess(baseDataCommond, condition);
		return result;
	}

	/**
	 * 根据基础数据类别编码查询基础数据集合
	 * 
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Jul 26, 2012
	 */
	@RequestMapping(value = "/listBaseDataByTypeCode")
	public @ResponseBody
	JSONObject listBaseDataByTypeCode(BaseDataCommond baseDataCommond) throws Exception {
		// 根据类型编码查询该编码类型下级的数据类型
		BaseDataTypeCondition baseDataTypeCondition = new BaseDataTypeCondition();
		baseDataTypeCondition.setSearchParentTypeCode(baseDataCommond.getSearchBaseTypeCode());
		List<BaseDataType> bdtList = baseDataTypeService.listBaseDataType(baseDataTypeCondition);
		JSONObject result = new JSONObject();
		// 默认查询启用的
		baseDataCommond.setSearchActiveState(BaseData.IS_ACTIVE_Y.toString());
		BaseDataCondition condition = new BaseDataCondition();
		if (bdtList != null && bdtList.size() > 0) {
			String[] baseDateTypeIds = new String[bdtList.size()];
			for (int i = 0; i < bdtList.size(); i++) {
				baseDateTypeIds[i] = bdtList.get(i).getTypeID();
			}
			condition.setSearchDataTypeIDs(baseDateTypeIds);
		} else {
			BeanUtils.copyProperties(baseDataCommond, condition);
		}
		List<BaseData> list = iBaseDataService.getBaseDataList(condition);
		List<BaseData> resultList = new ArrayList<BaseData>();
		// 组装基础时间有多个的基础类型的数据
		for (BaseData baseData : list) {
			for (BaseDataType bdt : bdtList) {
				if (bdt.getTypeID() == baseData.getBaseDataType().getTypeID()) {
					baseData.setDataName(bdt.getTypeName() + "-" + baseData.getDataName());
				}
			}
			baseData.setBaseDataType(null);
			resultList.add(baseData);
		}
		result.setData(resultList);
		result.setSuccess(true);
		return result;
	}

	/**
	 * 加入token令牌
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Mar 17, 2012
	 */
	@RequestMapping(value = "/saveToken")
	public @ResponseBody
	String preAddBaseData(HttpServletRequest request) throws Exception {
		String tokenStr = token.saveToken(request);
		return tokenStr;
	}

	/**
	 * 添加基础数据
	 * 
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@RequestMapping(value = "/addBaseData")
	public @ResponseBody
	JSONObject addBaseData(HttpServletRequest request, BaseDataValidCommond baseDataValidCommond) throws Exception {
		BaseData baseData = null;
		JSONObject result = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			baseData = new BaseData();
			// 基础数据类别ID是否为空
			if (baseDataValidCommond.getSearchDataTypeID() != null) {
				BeanUtils.copyProperties(baseDataValidCommond, baseData);
				BaseDataType baseDataType = new BaseDataType();
				baseDataType.setTypeID(baseDataValidCommond.getSearchDataTypeID());
				baseData.setBaseDataType(baseDataType);
				baseData.setCreateTime(DateUtil.getCurrentTimeLong());
				iBaseDataService.addBaseData(baseData);
				result.setSuccess(true);
				result.setMsg("添加成功！");
			} else {
				result.setSuccess(false);
				result.setMsg("添加失败！");
			}
		}
		return result;
	}

	/**
	 * 预更新数据
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@RequestMapping(value = "/preUpdateBaseData")
	public @ResponseBody
	JSONObject preUpdateBaseData(BaseDataValidCommond baseDataValidCommond) throws Exception {
		BaseDataCondition baseDataCondition = null;
		BaseData baseData = null;
		JSONObject object = new JSONObject();
		// 基础数据类型Id集合是否为空
		if (baseDataValidCommond.getSearchDataId() != null && !"".equals(baseDataValidCommond.getSearchDataId())) {
			baseDataCondition = new BaseDataCondition();
			baseDataCondition.setSearchDataId(baseDataValidCommond.getSearchDataId());
			baseData = iBaseDataService.getBaseData(baseDataCondition);
			BeanUtils.copyProperties(baseData, baseDataValidCommond);
			if (baseData.getBaseDataType() != null) {
				baseDataValidCommond.setSearchDataTypeID(baseData.getBaseDataType().getTypeID());
			}
			// 如果逻辑正确，必须加success为true
			object.setSuccess(true);
			object.setData(baseDataValidCommond);
		}
		return object;
	}

	/**
	 * 更新基础数据
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@RequestMapping(value = "/updataBaseData")
	public @ResponseBody
	JSONObject updataBaseData(HttpServletRequest request, BaseDataValidCommond baseDataValidCommond) throws Exception {
		BaseData baseData = null;
		JSONObject object = new JSONObject();
		// 用来判断是否为重复提交
		if (token.isTokenValid(request, true)) {
			baseData = new BaseData();
			BeanUtils.copyProperties(baseDataValidCommond, baseData);
			if (baseDataValidCommond.getSearchDataTypeID() != null) {
				BaseDataType baseDataType = new BaseDataType();
				baseDataType.setTypeID(baseDataValidCommond.getSearchDataTypeID());
				baseData.setBaseDataType(baseDataType);
				iBaseDataService.updateBaseData(baseData);
				object.setSuccess(true);
				object.setMsg("修改成功！");
			} else {
				object.setSuccess(false);
				object.setMsg("基础数据类别为空！");
			}
		}
		return object;
	}

	/**
	 * 用来验证数据编码是否重复
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@RequestMapping(value = "/checkDataCodeIsRepeat")
	public @ResponseBody
	JSONObject checkDataCodeIsRepeat(BaseDataValidCommond baseDataValidCommond) throws Exception {
		BaseData baseData;
		JSONObject object = new JSONObject();
		object.setSuccess(false);
		if (baseDataValidCommond.getDataCode() != null && !"".equals(baseDataValidCommond.getDataCode())) {
			baseData = new BaseData();
			baseData.setDataCode(baseDataValidCommond.getDataCode());
			baseData.setDataID(baseDataValidCommond.getDataID());
			boolean check = iBaseDataService.checkDataCodeIsRepeat(baseData);
			if (check) {
				// 重复
				object.setMsg("编码重复");
			} else {
				object.setSuccess(true);
			}
		} else {
			object.setMsg("查询数据编码为空！");
		}
		return object;
	}

	/**
	 * 根据ID数组批量作废或启用基础数据
	 * 
	 * @param model
	 * @param request
	 * @param baseDataCommond
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 7, 2011
	 */
	@RequestMapping(value = "/discardOrReuseBaseData")
	public @ResponseBody
	JSONObject discardOrReuseBaseData(HttpServletRequest request, BaseDataCommond baseDataCommond) throws Exception {
		JSONObject object = new JSONObject();
		if (baseDataCommond.getSearchActive() != null && !"".equals(baseDataCommond.getSearchActive()) && baseDataCommond.getBaseDataIds() != null && baseDataCommond.getBaseDataIds().length > 0) {
			BaseDataCondition baseDataCondition = new BaseDataCondition();
			// baseDataCondition.setBaseDataIds(baseDataCommond.getBaseDataIds());
			// baseDataCondition.setSearchActive(baseDataCommond.getSearchActive());
			BeanUtils.copyProperties(baseDataCommond, baseDataCondition);
			iBaseDataService.discardOrReuseBaseData(baseDataCondition);
			object.setSuccess(true);
			object.setMsg("修改成功！");
		} else {
			object.setSuccess(false);
			object.setMsg("为空");
		}
		return object;
	}
}
