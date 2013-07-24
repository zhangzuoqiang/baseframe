/*
 *$Log: BaseDao.java,v $
 *Revision 1.1  2012/05/23 09:27:47  guor
 *初次提交
 *
 */
package cn.hs.core.basedao.base.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import cn.hs.core.basedao.base.querybean.BaseBean;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: BaseDao<br>
 * Description: <br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * 
 * @author GuoR
 * @createDate 2012-5-21
 * @version $Revision: 1.1 $
 */
@Repository(value = "cn.hs.core.basedao.base.impl.BaseDao")
public class BaseDao<T> extends OldBaseDao<T> {

	/**
	 * 根据ID加载PO实例
	 * 
	 * @param id
	 * @return 返回相应的持久化PO实例
	 */
	@Override
	public T loadObject(Class<T> clazz, Serializable id) {
		return (T) getHibernateTemplate().load(clazz, id);
	}

	/**
	 * 根据ID获取PO实例
	 * 
	 * @param id
	 *            实体ID
	 * @return 返回相应的持久化PO实例
	 */
	@Override
	public T findObject(Class<T> clazz, Serializable id) {
		return (T) getHibernateTemplate().get(clazz, id);
	}

	/**
	 * 获取PO的所有对象
	 * 
	 * @return
	 */
	@Override
	public List<T> loadAll(Class<T> clazz) {
		return getHibernateTemplate().loadAll(clazz);
	}

	/**
	 * 对延迟加载的实体PO执行初始化
	 * 
	 * @param entity
	 */
	@Override
	public void initialize(Object entity) {
		this.getHibernateTemplate().initialize(entity);
	}

	/**
	 * 使用hql查询count
	 * 
	 * @param condition
	 *            中包含sql和parameterList
	 * @return 返回count数值
	 */
	@Override
	public long countQuery(BaseCondition condition) {
		Query query = createQuery(condition.getSql(), condition.getParameterList());
		return (Long) query.uniqueResult();
	}

	/**
	 * 执行特定的HQL
	 * 
	 * @param condition
	 *            中包含sql和parameterList
	 * @return
	 */
	@Override
	public int executeUpdate(BaseCondition condition) {
		Query query = createQuery(condition.getSql(), condition.getParameterList());
		return query.executeUpdate();
	}

	/**
	 * 使用JDBC查询Count
	 * 
	 * @param condition
	 *            中包含sql和parameterList
	 * @return 返回count数值
	 * @throws Exception
	 */
	@Override
	public long countQueryJDBC(BaseCondition condition) throws Exception {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		Long result = null;
		try {
			connection = getJDBCConnection();
			prepareStatement = connection.prepareStatement(condition.getSql());
			if (!condition.getParameterList().isEmpty()) {
				for (int i = 0; i < condition.getParameterList().size(); i++) {
					prepareStatement.setObject(i + 1, condition.getParameterList().get(i));
				}
			}
			resultSet = prepareStatement.executeQuery();
			while (resultSet.next()) {
				result = resultSet.getLong(1);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			releaseJDBCConnection(resultSet, prepareStatement, connection);
		}
		return result;
	}

	/**
	 * 分页查询
	 * 
	 * @param condition
	 *            中需要包含sql ,startRow和rows为-1时，不分页查询
	 * @return 返回查询结果，如果设置了BaseBean属性，返回BaseBean属性的集合
	 * @throws Exception
	 */
	@Override
	public Collection<?> pagedQuery(BasePageCondition condition) {
		Collection<?> result = null;
		// 翻页起始点
		int startRow = condition.getStart();
		// 翻页查询长度
		int rows = condition.getRows();

		Query query = createQuery(condition.getSql(), condition.getParameterList());

		// 设置查询结果封装bean，用于多表连查，封装各表中查出的属性
		if (condition.getBasebean() != null) {
			query.setResultTransformer(Transformers.aliasToBean(condition.getBasebean()));
		}
		if (startRow != -1)
			// 用于EXT
			query.setFirstResult(startRow);
		// query.setFirstResult(startRow - 1);
		if (rows != -1)
			// 用于EXT
			query.setMaxResults(rows);
		// query.setMaxResults(rows - startRow + 1);
		result = query.list();
		return result;
	}

	/**
	 * 使用JDBC查询结果
	 * 
	 * @param condition
	 *            中需要包含sql ,startRow和rows<strong>同时为-1时</strong>，不分页查询；<br>
	 *            分页查询时condition中的parameterList最后两个值分别为<strong>行数（rows）,起始行（
	 *            start）</strong>
	 * @return
	 * @throws Exception
	 */
	@Override
	public Collection<?> pagedQueryJDBC(BasePageCondition condition) throws Exception {
		Collection<?> result = null;
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		String sql = null;
		try {
			// 翻页起始点
			int startRow = condition.getStart();
			// 翻页查询长度
			int rows = condition.getRows();
			if (startRow != -1 && rows != -1) {
				// modify by HuangS at 2012-09-11 修改查询起始值,并赋值,根据翻页长度计算
				condition.setStart(startRow + 1);
				condition.setRows(startRow + rows);
				condition.addParameter(condition.getRows());
				condition.addParameter(condition.getStart());
				StringBuffer pageSQL = baseRequire.buildJDBCPageSQL(condition.getSql(), condition);
				sql = pageSQL.toString();
			} else {
				sql = condition.getSql();
			}
			connection = getJDBCConnection();
			prepareStatement = connection.prepareStatement(sql);
			if (!condition.getParameterList().isEmpty()) {
				for (int i = 0; i < condition.getParameterList().size(); i++) {
					prepareStatement.setObject(i + 1, condition.getParameterList().get(i));
				}
			}
			resultSet = prepareStatement.executeQuery();
			ResultSetMetaData resultsetmetadata = resultSet.getMetaData();
			// 反射bean
			result = aliasToJDBCBean(resultsetmetadata, resultSet, condition);
		} catch (Exception e) {
			e.printStackTrace();
			log4jManager.saveExceptionLog(getClass().getName(), "findResultSetWithJDBCPage", "sql为空", e);
			throw new Exception("查询JDBC结果集，带分页异常");
		} finally {
			releaseJDBCConnection(resultSet, prepareStatement, connection);
		}
		return result;
	}

	/**
	 * 执行SQL查询
	 * 
	 * @param beanClass
	 * @param sql
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<?> findJDBC(Class<? extends BaseBean> beanClass, String sql, Object... params) throws Exception {
		// ResultTransformer aliasToBean = Transformers.aliasToBean(beanClass);
		BasePageCondition condition = new BasePageCondition();
		condition.setBasebean(beanClass);
		condition.setSql(sql);
		condition.setParameterList(Arrays.asList(params));
		condition.setStart(-1);
		condition.setRows(-1);
		// Query query = getSession().createSQLQuery(sql);
		// query.setResultTransformer(aliasToBean);
		// for (int i = 0; i < params.length; i++) {
		// query.setParameter(i, params[i]);
		// }
		List<?> pagedQueryJDBC = (List<?>) pagedQueryJDBC(condition);
		condition.setParameterList(null);
		return pagedQueryJDBC;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.hs.core.basedao.base.IBaseDao#find(cn.hs.core.basedao.condition.BaseCondition)
	 */
	@Override
	public List<?> find(BaseCondition condition) {
		return this.getHibernateTemplate().find(condition.getSql(), condition.getParameterArray());
	}

	/**
	 * 执行带参的HQL查询
	 * 
	 * @param sql
	 * @param params
	 * @return 查询结果
	 */
	@Override
	public List find(String hql, Object... params) {
		return this.getHibernateTemplate().find(hql, params);
	}

	/**
	 * 创建PreparedStatement对象
	 * 
	 * @param sql
	 *            sql语句
	 * @param parameterList
	 *            sql语句中的参数
	 * @return
	 * @throws Exception
	 */
	// public PreparedStatement createPreparedStatement(String sql,
	// List<Object> parameterList) throws Exception {
	// PreparedStatement prepareStatement = getJDBCConnection()
	// .prepareStatement(sql);
	// if (!parameterList.isEmpty()) {
	// for (int i = 0; i < parameterList.size(); i++) {
	// prepareStatement.setObject(i + 1, parameterList.get(i));
	// }
	// }
	// return prepareStatement;
	// }
	/**
	 * 创建Query对象.
	 * 对于需要first,max,fetchsize,cache,cacheRegion等诸多设置的函数,可以在返回Query后自行设置.
	 * 留意可以连续设置,如下：
	 * 
	 * <pre>
	 * dao.getQuery(hql).setMaxResult(100).setCacheable(true).list();
	 * </pre>
	 * 
	 * 调用方式如下：
	 * 
	 * @param values
	 *            可变参数.可为数组
	 */
	public Query createQuery(String hql, List<Object> parameterList) {
		Assert.hasText(hql);
		Query query = getSession().createQuery(hql);
		if (!parameterList.isEmpty()) {
			for (int i = 0; i < parameterList.size(); i++) {
				query.setParameter(i, parameterList.get(i));
			}
		}
		return query;
	}

	/**
	 * 去除hql的select 子句，未考虑union的情况,用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	protected static String removeSelect(String hql) {
		Assert.hasText(hql);
		int beginPos = hql.toLowerCase().indexOf("from");
		Assert.isTrue(beginPos != -1, " hql : " + hql + " must has a keyword 'from'");
		return hql.substring(beginPos);
	}

	/**
	 * 去除hql的orderby 子句，用于pagedQuery.
	 * 
	 * @see #pagedQuery(String,int,int,Object[])
	 */
	protected static String removeOrders(String hql) {
		Assert.hasText(hql);
		Pattern p = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(hql);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "");
		}
		m.appendTail(sb);
		return sb.toString();
	}

}
