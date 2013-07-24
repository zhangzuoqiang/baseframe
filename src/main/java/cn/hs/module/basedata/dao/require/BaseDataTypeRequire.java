package cn.hs.module.basedata.dao.require;

import org.springframework.stereotype.Component;

import cn.hs.core.servicetemplate.treetemplate.jsonbean.jsonnodeparameters.UpdateOrderParameter;
import cn.hs.module.basedata.domain.BaseDataAddressTreeCondition;
import cn.hs.module.basedata.domain.BaseDataType;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * 
 * Title: BaseDataTypeRequire<br>
 * Description: <br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author FanKY
 * @createDate Sep 5, 2011
 * @version $Revision: 72 $
 */
@Component(value = "cn.hs.module.basedata.dao.require.BaseDataTypeRequire")
public class BaseDataTypeRequire {

	/**
	 * 查询基础数据类别
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataTypeCondition listBaseDataTypeHQL(BaseDataTypeCondition condition) {
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select t from BaseDataType t where t.activeState = ?");
		condition.addParameter(BaseDataType.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			sqlBuffer.append(" and t.parentID= ?");
			condition.addParameter(condition.getNode());
		}
		if (condition.getTypeCode() != null && !"".equals(condition.getTypeCode())) {
			sqlBuffer.append(" and t.typeCode = ?");
			condition.addParameter(condition.getTypeCode());
		}
		if (condition.getSearchParentTypeCode() != null && !"".equals(condition.getSearchParentTypeCode())) {
			sqlBuffer.append(" and t.parentID in (select b.typeID from BaseDataType b where b.typeCode =?)");
			condition.addParameter(condition.getSearchParentTypeCode());
		}
		sqlBuffer.append("  order by t.orderNum,t.typeCode");
		condition.setSql(sqlBuffer.toString());
		return condition;
	}

	/**
	 * 查询基础数据类别
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataTypeCondition isLeapNode(BaseDataTypeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select count(t.typeID) from BaseDataType t where t.activeState= ?");
		condition.addParameter(BaseDataType.IS_ACTIVE_Y);
		if (condition.getNode() != null && !"".equals(condition.getNode())) {
			hqlBuffer.append(" and t.parentID= ?");
			condition.addParameter(condition.getNode());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据基础数据类别编码查询语句
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataTypeCondition queryByTypeCode(BaseDataTypeCondition condition) {
		String result = "select t from BaseDataType t where 1=1";
		if (condition.getSearchTypeID() != null && !"".equals(condition.getSearchTypeID())) {
			result += " and t.typeID!= ?";
			condition.addParameter(condition.getSearchTypeID());
		}
		if (condition.getTypeCode() != null && !"".equals(condition.getTypeCode())) {
			result += " and t.typeCode= ?";
			condition.addParameter(condition.getTypeCode());
		}
		condition.setSql(result);
		return condition;
	}

	/**
	 * 根据基础数据类别编码查询语句
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataAddressTreeCondition queryByTypeParenCode(BaseDataAddressTreeCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer("select t from BaseDataType t where t.activeState = ?");
		condition.addParameter(BaseDataType.IS_ACTIVE_Y);
		if (condition.getSearchTypeCode() != null && !"".equals(condition.getSearchTypeCode())) {
			hqlBuffer.append(" and t.parentID in (select bdt.typeID from BaseDataType bdt where bdt.typeCode = ?)");
			condition.addParameter(condition.getSearchTypeCode());
		}
		hqlBuffer.append(" ORDER BY t.orderNum,t.typeCode");
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	public BaseDataTypeCondition updateTreeOrderNumHQL(UpdateOrderParameter updateOrderParameter) {
		BaseDataTypeCondition condition = new BaseDataTypeCondition();
		String hql = "";
		if (updateOrderParameter.getUpObjID() != null && !"".equals(updateOrderParameter.getUpObjID()) && updateOrderParameter.getUpOrderNumValue() != null && !"".equals(updateOrderParameter.getUpOrderNumValue())) {
			hql = "update BaseDataType bt set bt.orderNum=? where bt.typeID=?";
			condition.addParameter(new Integer(updateOrderParameter.getUpOrderNumValue()));
			condition.addParameter(new Integer(updateOrderParameter.getUpObjID()));
		}
		condition.setSql(hql);
		return condition;
	}

	public BaseDataTypeCondition updateTreeParentIDHQL(BaseDataTypeCondition condition) {
		String hql = "";
		if (condition.getTreeNodeID() != null && !"".equals(condition.getTreeNodeID()) && condition.getParentNodeID() != null && !"".equals(condition.getParentNodeID())) {
			hql = "update BaseDataType bt set bt.parentID=? where bt.typeID=?";
			condition.addParameter(new Integer(condition.getParentNodeID()));
			condition.addParameter(new Integer(condition.getTreeNodeID()));
		}
		condition.setSql(hql);
		return condition;
	}
}
