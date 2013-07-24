/*
 * $Log: BaseRequire.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.base.require;

import java.sql.PreparedStatement;

import org.springframework.stereotype.Component;

import cn.hs.commons.utils.StringTool;
import cn.hs.core.basedao.condition.BaseCondition;

/**
 * Title: BaseRequire<br>
 * Description: hql/sql语句拼写类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 2, 2011
 * @version $Revision: 1.1 $
 */
@Component(value = "cn.hs.core.basedao.base.require.BaseRequire")
public class BaseRequire {
	/**
	 * 查询JDBC结果集，带分页，拼接SQL，带占位符，该方法对外使用，每一个拼写sql的地方需要调用此方法
	 * 
	 * @param sql
	 * @return
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	public StringBuffer buildJDBCPageSQL(String sql, BaseCondition condition) {
		// 获得传过来的sql已使用占位符个数
		int charSum = StringTool.searchCharCountFromString('?', sql);
		condition.setCharSum(charSum);
		StringBuffer newSql = new StringBuffer();
		newSql.append("select * from" + " (select row_.*, rownum rownum_ from (" + sql + ") row_" + " where rownum <= ?)" + " where rownum_ >= ?");
		return newSql;
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
	public String getFindNextNodeListSelectTreeSQL(String treeTableName, String nodeID, String nodeParentID, String nodeText, String nodeState, String rootId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (select ");
		sql.append(nodeID + " as id, ");
		sql.append(nodeParentID + " as parentId, ");
		sql.append(nodeText + " as text ");
		if (nodeState != null && "".equals(nodeState)) {
			sql.append(", " + nodeState + " as state ");
		}
		sql.append("from " + treeTableName + ") t");
		sql.append(" start with t.id =" + rootId);
		sql.append(" connect by prior t.id = t.parentId");
		return sql.toString();
	}

	/**
	 * 查询JDBC结果集，带分页，拼接SQL，不带占位符
	 * 
	 * @param sql
	 * @param begin
	 * @param end
	 * @return
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	public StringBuffer findResultSetWithJDBCPageSQL(String sql, int begin, int end) {
		StringBuffer newSql = new StringBuffer();
		newSql.append("select * from" + " (select row_.*, rownum rownum_ from (" + sql + ") row_" + " where rownum <= " + end + ")" + " where rownum_ >= " + begin);
		return newSql;
	}

	/**
	 * 查询JDBC结果集，占位符拼接
	 * 
	 * @param begin
	 * @param end
	 * @throws Exception
	 * @author WangWB
	 * @date Dec 22, 2011
	 */
	public void findResultSetWithJDBCPageParameter(PreparedStatement pStatement, int begin, int end, int indexBegin) throws Exception {
		pStatement.setInt(indexBegin, end);
		pStatement.setInt(indexBegin + 1, begin);
	}

}
