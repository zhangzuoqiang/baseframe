package cn.hs.module.basedata.dao.require;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.hs.core.annotation.EscapeProperty;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.page.commond.SortInfoBean;
import cn.hs.module.basedata.domain.BaseData;
import cn.hs.module.basedata.domain.BaseDataCondition;
import cn.hs.module.basedata.domain.BaseDataTypeCondition;

/**
 * 
 * Title: BaseDataRequire<br>
 * Description: 基础数据类别hql拼装类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author FanKY
 * @createDate Aug 31, 2011
 * @version $Revision: 72 $
 */
@Component(value = "cn.hs.module.basedata.dao.require.BaseDataRequire")
public class BaseDataRequire {
	/**
	 * list基础数据类型集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@EscapeProperty(escapePropertyNames = "searchBaseName")
	public BaseDataCondition listBaseData(BaseDataCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select t from BaseData t join t.baseDataType bdt where 1=1");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState = ?");
			// 参数为整理，注意转类型
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		if (condition.getSearchBaseName() != null && !"".equals(condition.getSearchBaseName())) {
			hqlBuffer.append(" and t.dataName like ? escape '/'");
			condition.addParameter("%" + condition.getSearchBaseName() + "%");
		}
		if (condition.getSearchDataCode() != null && !"".equals(condition.getSearchDataCode())) {
			hqlBuffer.append(" and t.dataCode = ?");
			condition.addParameter(condition.getSearchDataCode());
		}
		if (condition.getSearchDataTypeID() != null) {
			hqlBuffer.append(" and bdt.typeID = ?");
			condition.addParameter(condition.getSearchDataTypeID());
		}
		// 拼装排序
		SortInfoBean sortInfo = condition.getSortInfo();
		if (sortInfo != null) {
			hqlBuffer.append(" order by " + sortInfo.getProperty() + " " + sortInfo.getDirection());
		} else {
			hqlBuffer.append(" order by t.orderNum,t.dataID");
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * count基础数据类型集合HQL
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	@EscapeProperty(escapePropertyNames = "searchBaseName")
	public BaseDataCondition countBaseDataHql(BaseDataCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select count(t.dataID) from BaseData t join t.baseDataType bdt where 1=1");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState = ?");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		if (condition.getSearchBaseName() != null && !"".equals(condition.getSearchBaseName())) {
			hqlBuffer.append(" and t.dataName like ?");
			condition.addParameter("%" + condition.getSearchBaseName() + "%");
		}
		if (condition.getSearchDataCode() != null && !"".equals(condition.getSearchDataCode())) {
			hqlBuffer.append(" and t.dataCode = ?");
			condition.addParameter(condition.getSearchDataCode());
		}
		if (condition.getSearchDataTypeID() != null) {
			hqlBuffer.append(" and bdt.typeID = ?");
			condition.addParameter(condition.getSearchDataTypeID());
		}
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 基础数据类型Parameter
	 * 
	 * @param condition
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public void baseDataParameter(BaseDataCondition condition) {
		if (condition.getSearchBaseName() != null && !"".equals(condition.getSearchBaseName())) {
			condition.getQuery().setParameter("dataName", "%" + condition.getSearchBaseName() + "%");
		}
		if (condition.getSearchDataCode() != null && !"".equals(condition.getSearchDataCode())) {
			condition.getQuery().setParameter("dataCode", condition.getSearchDataCode());
		}
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			condition.getQuery().setParameter("activeState", new Integer(condition.getSearchActiveState()));
		}
		if (condition.getSearchDataTypeID() != null) {
			condition.getQuery().setParameter("typeID", condition.getSearchDataTypeID());
		}
	}

	/**
	 * 根据基础数据编码查询基础数据信息
	 * 
	 * @param baseData
	 * @return
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public String checkDataCodeIsRepeatHql(BaseData baseData) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select count(t.dataID) from BaseData t where t.dataCode = :dataCode ");

		if (baseData.getDataID() != null) {
			hqlBuffer.append(" and t.dataID != :dataID");
		}
		return hqlBuffer.toString();
	}

	/**
	 * 根据基础数据编码查询基础数据信息Parameter
	 * 
	 * @param baseData
	 * @param query
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public void checkDataCodeIsRepeatParameter(BaseData baseData, Query query) {
		if (baseData.getDataCode() != null && !"".equals(baseData.getDataCode())) {
			query.setParameter("dataCode", baseData.getDataCode());
		}
		if (baseData.getDataID() != null) {
			query.setParameter("dataID", baseData.getDataID());
		}
	}

	/**
	 * 根据基础数据编码查询基础数据信息
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Aug 31, 2011
	 */
	public BaseDataCondition viewBaseDataHql(BaseDataCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append(" select t from BaseData t where t.dataCode = ?");
		condition.addParameter(condition.getSearchDataCode());
		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据基础数据类别编码查询 modify by HuangS at 2011-09-09
	 * 
	 * @param condition
	 * @return
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	public BaseDataCondition getBaseDataListHql(BaseDataCondition condition) {
		StringBuffer hqlBuffer = new StringBuffer();
		hqlBuffer.append("select t from BaseData t join t.baseDataType bdt where 1=1");
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			hqlBuffer.append(" and t.activeState= ?");
			condition.addParameter(new Integer(condition.getSearchActiveState()));
		}
		if (condition.getSearchBaseTypeCode() != null && !"".equals(condition.getSearchBaseTypeCode())) {
			hqlBuffer.append(" and bdt.typeCode= ?");
			condition.addParameter(condition.getSearchBaseTypeCode());
		}
		if (condition.getSearchDataTypeID() != null && !"".equals(condition.getSearchDataTypeID())) {
			hqlBuffer.append(" and bdt.typeID= ?");
			condition.addParameter(condition.getSearchDataTypeID());
		}
		if (condition.getSearchDataTypeIDs() != null && condition.getSearchDataTypeIDs().length > 0) {
			hqlBuffer.append(" and bdt.typeID in (" + condition.assemblyParameterListSQL(condition.getSearchDataTypeIDs()) + ")");
			condition.addParameterList(condition.getSearchDataTypeIDs());
		}
		hqlBuffer.append(" order by t.baseDataType.typeID,t.orderNum");

		condition.setSql(hqlBuffer.toString());
		return condition;
	}

	/**
	 * 根据基础数据类别编码查询参数
	 * 
	 * @param condition
	 * @author FanKY
	 * @date Sep 5, 2011
	 */
	public void getBaseDataListHqlQuery(BaseDataCondition condition) {
		if (condition.getSearchBaseTypeCode() != null && !"".equals(condition.getSearchBaseTypeCode())) {
			condition.getQuery().setParameter("typeCode", condition.getSearchBaseTypeCode());
		}
	}

	/**
	 * 根据基础数据类别编码查询基础数据集合HQL
	 * 
	 * @param condition
	 * @author FanKY
	 * @date Sep 7, 2011
	 */
	public void getBaseDataListHQL(BaseDataCondition condition) {
		String hql = "select bd from BaseData bd join BaseDataType bdt where 1=1";
		if (condition.getSearchBaseTypeCode() != null && !"".equals(condition.getSearchBaseTypeCode())) {
			hql += " and bdt.typeCode = :typeCode";
		}
	}

	/**
	 * 根据基础数据类别编码查询基础数据集合parameter modify by HuangS at 2011-09-09
	 * 
	 * @param condition
	 * @author FanKY
	 * @date Sep 7, 2011
	 */
	public void getBaseDataListParameter(BaseDataCondition condition) {
		if (condition.getSearchActiveState() != null && !"".equals(condition.getSearchActiveState())) {
			condition.getQuery().setParameter("activeState", Integer.parseInt(condition.getSearchActiveState()));
		}
		if (condition.getSearchBaseTypeCode() != null && !"".equals(condition.getSearchBaseTypeCode())) {
			condition.getQuery().setParameter("typeCode", condition.getSearchBaseTypeCode());
		}
	}

	/**
	 * 根据ID数组批量作废或启用基础数据HQL
	 * 
	 * @param condition
	 * @author FanKY
	 * @date Sep 7, 2011
	 */
	public BaseDataCondition discardOrReuseBaseDataHQL(BaseDataCondition condition) {
		String hql = "update BaseData bd set bd.activeState = ? where 1=1";
		condition.addParameter(new Integer(condition.getSearchActive()));
		if (condition.getBaseDataIds() != null && condition.getBaseDataIds().length > 0) {
			hql += " and bd.dataID in (" + condition.assemblyParameterListSQL(condition.getBaseDataIds()) + ")";
			condition.addParameterList(condition.getBaseDataIds());
		}
		condition.setSql(hql);
		return condition;
	}

	/**
	 * 根据基础数据类别ID查询基础数据HQL语句
	 * 
	 * @param condition
	 * @return
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public BaseDataTypeCondition queryBaseDataByTypeID(BaseDataTypeCondition condition) {
		String result = "select b from BaseDataType t, BaseData b where t.typeID = b.baseDataType.typeID and b.activeState= ?";
		condition.addParameter(BaseData.IS_ACTIVE_Y);
		if (condition.getSearchTypeID() != null && !"".equals(condition.getSearchTypeID())) {
			result += " and t.typeID= ?";
			condition.addParameter(condition.getSearchTypeID());
		}
		condition.setSql(result);
		return condition;
	}

	/**
	 * 设置基础数据需要查询的Parameter
	 * 
	 * @param condition
	 * @author HuangS
	 * @date Sep 7, 2011
	 */
	public void baseDataParameter(BaseDataTypeCondition condition) {
		condition.getQuery().setParameter("activeState", BaseData.IS_ACTIVE_Y);
		if (condition.getSearchTypeID() != null && !"".equals(condition.getSearchTypeID())) {
			condition.getQuery().setParameter("typeID", condition.getSearchTypeID());
		}
	}

	/**
	 * 根据ID获得基础数据HQL
	 * 
	 * @return
	 * @author WangWB
	 * @date Oct 10, 2011
	 */
	public String getBaseDataByIDHQL() {
		return "from BaseData where dataID = :dataID";
	}

	/**
	 * 根据ID数组获得基础数据 HQL
	 * 
	 * @return
	 * @author WangWB
	 * @date Oct 9, 2011
	 */
	public BaseCondition getBaseDataListByIDArrayHQL(String[] idArray) {
		BaseCondition condition = new BaseCondition();
		condition.setSql(" from BaseData where dataID in (" + condition.assemblyParameterListSQL(idArray) + ")");
		condition.addParameterList(idArray);
		return condition;
	}

}
