/*
 * $Log: BaseCondition.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.condition;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;


import cn.hs.core.basedao.base.querybean.BaseBean;

/**
 * Title: ICondition<br>
 * Description: 持久化层数据参数基类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
public class BaseCondition {
	/**
	 * Hibernate query
	 */
	private Query query;

	/**
	 * JDBC statement
	 */
	private Statement stmt;
	/**
	 * JDBC SQL语句
	 */
	private String sql;
	/**
	 * 下拉复选树需要设置树形根节点
	 */
	private String rootID;
	/**
	 * 查询结果封装bean，Hibernate、JDBC通用
	 */
	private Class<? extends BaseBean> basebean;
	/**
	 * JDBC 传递过来的sql已使用占位符个数（对内）
	 */
	private int charSum;
	/**
	 * SQL参数集合
	 */
	private List<Object> parameterList;
	
	/**
	 * @return the parameterList
	 */
	public List<Object> getParameterList() {
		if(parameterList==null) {
			this.parameterList = new ArrayList<Object>();
		}
		return parameterList;
	}
	/**
	 * @return the parameterList
	 */
	public Object[] getParameterArray() {
		if(parameterList==null) {
			this.parameterList = new ArrayList<Object>();
		}
		return parameterList.toArray();
	}
	/**
	 * @param parameterList the parameterList to set
	 */
	public void setParameterList(List<Object> parameterList) {
		this.parameterList = parameterList;
	}
	/**
	 * 增加SQL参数。注意增加参数的顺序
	 * @param parameter
	 */
	public void addParameter(Object parameter) {
		getParameterList().add(parameter);
	}
	/**
	 * 增加数组型SQL参数
	 * @param parameters
	 */
	public void addParameterList(Object[] parameters) {
		if(parameters!=null) {
			for (int i = 0; i < parameters.length; i++) {
				addParameter(parameters[i]);
			}
		}
	}
	public String assemblyParameterListSQL(Object[] parameters) {
		StringBuilder result = new StringBuilder();
		if(parameters!=null) {
			for (int i = 0; i < parameters.length; i++) {
				result.append("?,");
			}
		}
		return result.substring(0, result.length()-1);
	}
	/**
	 * 已折旧
	 * @return
	 */
	@Deprecated
	public Query getQuery() {
		return query;
	}
	/**
	 * 已折旧
	 */
	@Deprecated
	public void setQuery(Query query) {
		this.query = query;
	}

	public Class<? extends BaseBean> getBasebean() {
		return basebean;
	}

	public void setBasebean(Class<? extends BaseBean> basebean) {
		this.basebean = basebean;
	}
	/**
	 * 已折旧
	 * @return
	 */
	@Deprecated
	public Statement getStmt() {
		return stmt;
	}
	/**
	 * 已折旧
	 */
	@Deprecated
	public void setStmt(Statement stmt) {
		this.stmt = stmt;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	/**
	 * 根据树表名，节点ID字段名，父节点ID字段名，节点名称字段名称，是否为叶子节点字段名称（可选字段） 及查询范围里的根父节点ID返回相应sql语句
	 * 
	 * @param treeTableName
	 *            树表名
	 * @param nodeID
	 *            节点ID字段名
	 * @param nodeParentID
	 *            父节点ID字段名
	 * @param nodeText
	 *            节点名称字段名称
	 * @param nodeState
	 *            是否为叶子节点字段名称
	 * @param rootId
	 *            根节点ID
	 * @return
	 * @author Liy
	 * @date 2011-11-9
	 */
	public String getFindNextNodeListSelectTreeSQL(String treeTableName,
			String nodeID, String nodeParentID, String nodeText,
			String nodeState) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (select ");
		sql.append(nodeID + " as id, ");
		sql.append(nodeParentID + " as parentId, ");
		sql.append(nodeText + " as text ");
		if (nodeState != null && "".equals(nodeState)) {
			sql.append(", " + nodeState + " as state ");
		}
		sql.append("from " + treeTableName + ") t");
		sql.append(" start with t.id =" + getRootID());
		sql.append(" connect by prior t.id = t.parentId");
		return sql.toString();
	}

	public String getRootID() {
		return rootID;
	}

	public void setRootID(String rootID) {
		this.rootID = rootID;
	}

	public int getCharSum() {
		return charSum;
	}

	public void setCharSum(int charSum) {
		this.charSum = charSum;
	}
}
