package cn.hs.module.basedata.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.core.basedao.condition.BaseTreeCondition;
import cn.hs.core.servicetemplate.treetemplate.TreeTemplate;
import cn.hs.core.servicetemplate.treetemplate.jsonbean.JsonTreeBean;
import cn.hs.module.basedata.dao.IBaseDataTypeDao;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;
import cn.hs.module.basedata.service.IBaseDataService;
import cn.hs.module.basedata.service.IBaseDataTypeService;

/**
 * 
 * Title: IBaseDataTypeServiceImpl<br>
 * Description: 基础数据类别接口实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 6, 2011
 * @version $Revision: 73 $
 */
@Service(value = "cn.hs.module.basedata.service.impl.BaseDataTypeServiceImpl")
public class BaseDataTypeServiceImpl extends TreeTemplate implements IBaseDataTypeService {

	// 基础数据类别DAO
	@Autowired
	private IBaseDataTypeDao baseDataTypeDaoImpl;

	// 基础数据manager
	@Autowired
	private IBaseDataService baseDataService;

	/**
	 * 调用模板
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 15, 2011
	 */
	public List<JsonTreeBean> doProcess(BaseTreeCondition condition) throws Exception {
		return process(condition);
	}

	/**
	 * 查询下一节点集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 15, 2011
	 */
	protected List<BaseDataType> findNextNodeList(BaseTreeCondition condition) throws Exception {
		return baseDataTypeDaoImpl.listBaseDataType((BaseDataTypeCondition) condition);
	}

	/**
	 * 查询是否为叶子节点
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 15, 2011
	 */
	protected boolean isLeapNode(BaseTreeCondition condition) throws Exception {
		if (condition.getBasebean() != null) {
			condition.setBasebean(null);
		}
		return baseDataTypeDaoImpl.isLeapNode((BaseDataTypeCondition) condition);
	}

	/**
	 * 获取树所需参数
	 * 
	 * @param treeNodeList
	 * @return
	 * @throws Exception
	 * @author FanKY
	 * @date Sep 15, 2011
	 */
	protected List<JsonTreeBean> objListToJsonList(List<?> treeNodeList) throws Exception {
		List<JsonTreeBean> resultList = new ArrayList<JsonTreeBean>();
		for (Object obj : treeNodeList) {
			BaseDataType baseDataType = (BaseDataType) obj;
			JsonTreeBean jtBean = new JsonTreeBean();
			jtBean.setId(baseDataType.getTypeID().toString());
			jtBean.setText(baseDataType.getTypeName());
			resultList.add(jtBean);
		}
		return resultList;
	}

	/**
	 * 新增基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public String addBaseType(BaseDataType baseDataType) throws Exception {
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		condition.setSearchTypeID(baseDataType.getParentID());
		BaseDataType parentBase = baseDataTypeDaoImpl.getBaseDataType(condition);
		// 编码不重复
		baseDataTypeDaoImpl.addBaseDataType(baseDataType);
		// 设置新增加的节点的treepath
		baseDataType.setTreepath(parentBase.getTreepath() + "\\" + baseDataType.getTypeID());
		// 更新新增节点treepath
		baseDataTypeDaoImpl.updateBaseDataType(baseDataType);
		// 返回新增节点的ID
		String result = baseDataType.getTypeID().toString() + "," + baseDataType.getTypeName();
		return result;
	}

	/**
	 * 根据基础数据类别ID查询是否存在子基础数据类别或基础数据
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public boolean checkIsNotExistsDataOrDataType(BaseDataTypeCondition condition) throws Exception {
		// 得到父ID下的所有子节点
		if (!baseDataTypeDaoImpl.isLeapNode(condition)) {
			// 说明有子节点
			return true;
		} else {
			List<BaseData> baseDataList = baseDataService.getAllBaseDataByTypeID(condition);
			if (baseDataList != null && baseDataList.size() > 0) {
				// 说明含有基础数据
				return true;
			}
		}
		return false;
	}

	/**
	 * 新增和更新数据类别前，查看类别代码是否有重复
	 * 
	 * @param baseDataType
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public boolean checkTypeCodeIsRepeat(BaseDataType baseDataType) throws Exception {
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		if (baseDataType.getTypeID() != null) {
			condition.setSearchTypeID(baseDataType.getTypeID());
		}
		condition.setTypeCode(baseDataType.getTypeCode());
		List<BaseDataType> list = baseDataTypeDaoImpl.getBaseDataTypeByTypeCode(condition);
		if (list != null && list.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 根据ID数组作废或启用基础数据类别
	 * 
	 * @param condition
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void discardOrReuseBaseDataType(BaseDataTypeCondition condition) throws Exception {
		BaseDataType baseDataType = baseDataTypeDaoImpl.getBaseDataType(condition);
		baseDataType.setActiveState(BaseDataType.IS_ACTIVE_N);
		baseDataTypeDaoImpl.updateBaseDataType(baseDataType);
	}

	/**
	 * 根据ID查询基础数据类别
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataType getBaseDataType(BaseDataTypeCondition condition) throws Exception {
		return baseDataTypeDaoImpl.getBaseDataType(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.hs.module.basedata.service.IBaseDataTypeService#findBaseDataList(cn.hs.module.basedata.domain.BaseDataTypeCondition)
	 */
	@Override
	public List<BaseDataType> findBaseDataList(BaseDataTypeCondition condition) throws Exception {
		return baseDataTypeDaoImpl.listBaseDataType((BaseDataTypeCondition) condition);
	}

	/**
	 * 更新基础数据类别
	 * 
	 * @param baseDataType
	 * @throws Exception
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void updateBaseDataType(BaseDataType baseDataType) throws Exception {
		baseDataTypeDaoImpl.updateBaseDataType(baseDataType);
	}

	@Override
	public List<BaseDataType> listBaseDataType(BaseDataTypeCondition condition) throws Exception {
		return baseDataTypeDaoImpl.listBaseDataType(condition);
	}

	@Override
	public List<BaseDataType> getBaseDataTypeByTypeCode(BaseDataTypeCondition condition) throws Exception {
		return baseDataTypeDaoImpl.getBaseDataTypeByTypeCode(condition);
	}

}
