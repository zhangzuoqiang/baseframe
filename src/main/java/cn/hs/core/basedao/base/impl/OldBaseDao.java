/*
 * $Log: OldBaseDao.java,v $
 * Revision 1.1  2012/05/23 09:27:47  guor
 * 初次提交
 *
 */
package cn.hs.core.basedao.base.impl;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.base.querybean.BaseBean;
import cn.hs.core.basedao.base.require.BaseRequire;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.log4j.ILog4jManager;

/**
 * Title: BaseDao<br>
 * Description: 持久化层基类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 1.1 $
 */
public abstract class OldBaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	// 初始化日志接口
	@Autowired
	protected ILog4jManager log4jManager;
	// 初始化hql/sql语句拼写类
	@Autowired
	protected BaseRequire baseRequire;

	/**
	 * 对sessionFactory的注入，若使用HibernateDaoSupport，则必须写，否则报错
	 * 
	 * @param sessionFactory
	 * @author WangWB
	 * @date Aug 3, 2011
	 */
	@Resource(name = "sessionFactory")
	public void setSF(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	/**
	 * 获得Session
	 * 
	 * @return
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	public Session getHibernateSession() throws Exception {
		return super.getSession();
	}

	/**
	 * 单个实体入库
	 * 
	 * @param entity
	 *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性无值
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public void addEntity(T entity) throws Exception {
		if (entity != null) {
			if (checkAnnotation(entity)) {
				getHibernateTemplate().save(entity);
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "addEntity", "entity为空");
		}
	}

	/**
	 * 判断entity中是否存在@Entity标识
	 * 
	 * @param entity
	 * @return
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	private boolean checkAnnotation(Object entity) throws Exception {
		boolean result = false;
		Class<?> clazz = entity.getClass();
		// 查看该类上方是否存在Entity注解
		boolean isExtendsEntity = clazz.isAnnotationPresent(Entity.class);
		if (isExtendsEntity) {
			// Method[] method = clazz.getMethods();
			// int sum = 1;
			// for (int i = 0; i < method.length; i++) {
			// // 找到该类中被Id注解了的方法
			// if (method[i].isAnnotationPresent(Id.class)) {
			// // 判断该方法是否无值
			// if (method[i].invoke(clazz, null) == null) {
			// result = true;
			// } else {
			// log4jManager.saveCustomLog(getClass().getName(),
			// "checkAnnotationForAdd", "该类中被Id注解了的方法有值");
			// }
			// break;
			// } else {
			// sum++;
			// }
			// }
			// if (sum == method.length) {
			// log4jManager.saveCustomLog(getClass().getName(),
			// "checkAnnotationForAdd", "该类中没有被Id注解了的方法");
			// }
			result = true;
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "checkAnnotation", "该类上方没有定义Entity注解");
		}
		return result;
	}

	/**
	 * 多个同类型实体批量入库
	 * 
	 * @param entities
	 *            实体对象集合，必须保证每个实体对象都带有@Entity标识，并且保证保证被@Id注解的属性无值
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public void batchAddEntities(Collection<T> entities) throws Exception {
		if (entities != null && !entities.isEmpty()) {
			Iterator<T> entityIterator = entities.iterator();
			while (entityIterator.hasNext()) {
				Object entity = entityIterator.next();
				if (checkAnnotation(entity)) {
					getHibernateTemplate().save(entity);
				} else {
					log4jManager.errorCustomLog(getClass().getName(), "batchAddEntities", "该类中不存在Entity或Id注解");
				}
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "batchAddEntities", "entities集合为空");
		}
	}

	/**
	 * 根据主键批量删除
	 * 
	 * @param entities
	 *            实体对象集合，必须保证每个实体对象都带有@Entity标识，并且保证被@Id注解的属性有值
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public void batchDeleteEntities(Collection<T> entities) throws Exception {
		if (entities != null && !entities.isEmpty()) {
			Iterator<T> entityIterator = entities.iterator();
			while (entityIterator.hasNext()) {
				Object entity = entityIterator.next();
				if (checkAnnotation(entity)) {
					getHibernateTemplate().delete(entity);
				} else {
					log4jManager.errorCustomLog(getClass().getName(), "batchDeleteEntities", "该类中不存在Entity或Id注解");
				}
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "batchDeleteEntities", "entities集合为空");
		}
	}

	/**
	 * 根据主键删除
	 * 
	 * @param entity
	 *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性有值
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public void deleteEntityByPK(Object entity) throws Exception {
		if (entity != null) {
			if (checkAnnotation(entity)) {
				getHibernateTemplate().delete(entity);
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "deleteEntityByPK", "entity为空");
		}
	}

	/**
	 * 获得HibernateTemplate对象
	 * 
	 * @return
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public HibernateTemplate getSpringHibernateTemplate() throws Exception {
		return getHibernateTemplate();
	}

	/**
	 * 将查询结果封装置BaseBean中，查询语句查询字段需要使用别名，并且别名需和自己的BaseBean中字段名称、类型一致
	 * 
	 * @param resultsetmetadata
	 * @param condition
	 * @return
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	protected List<BaseBean> aliasToJDBCBean(ResultSetMetaData resultsetmetadata, ResultSet rs, BaseCondition condition) throws Exception {
		List<BaseBean> result = null;
		Class<? extends BaseBean> reflectionClass = condition.getBasebean();
		if (reflectionClass != null) {
			result = new ArrayList<BaseBean>();
			BaseBean baseBean = null;
			// 查询字段个数
			int columnCount = resultsetmetadata.getColumnCount();
			while (rs.next()) {
				// 初始化一个承载对象
				baseBean = reflectionClass.newInstance();
				// 循环每一条数据的字段个数
				for (int rsIndex = 1; rsIndex <= columnCount; rsIndex++) {
					// 每一个字段名称
					String columnName = resultsetmetadata.getColumnLabel(rsIndex);
					// 查询字段类型编号
					int columnTypeNum = resultsetmetadata.getColumnType(rsIndex);
					// 每一个字段的值
					Object columnValue = rs.getObject(rsIndex);

					// 修改为使用beantuils赋值 modify by HuangS at 2012-09-04
					try {
						if (columnTypeNum == Types.CLOB) {
							if (columnValue != null) {
								Clob clob = (Clob) columnValue;
								String clobStr = clob.getSubString(1, (int) clob.length());
								BeanUtils.setProperty(baseBean, columnName, clobStr);
							}
						} else {
							BeanUtils.setProperty(baseBean, columnName, columnValue);
						}
					} catch (Exception e) {
						log4jManager.errorCustomLog(getClass().getName(), "aliasToJDBCBean", "赋值异常！");
					}

					// // 将数据库字段类型转换成对应的java数据类型
					// Class<?> javaTypeClass =
					// DBDataTypeToJAVADataType(columnTypeNum,
					// resultsetmetadata.getScale(rsIndex));
					// // 获得每个字段名对应bean中的set方法名，并将字段首字母大写
					// String setMethodName = "set" + columnName.substring(0,
					// 1).toUpperCase() + columnName.substring(1,
					// columnName.length());
					// Method setMethod = null;
					// try {
					// setMethod = reflectionClass.getMethod(setMethodName,
					// javaTypeClass);
					// } catch (Exception e) {
					// log4jManager.errorCustomLog(getClass().getName(),
					// "aliasToJDBCBean", "没有" + setMethodName + "这个方法！");
					// }
					// // 调用对应的set方法，将这个字段的值封装到对象中
					// if (setMethod != null) {
					// setMethod.invoke(baseBean,
					// DBValueToJAVAValue(columnValue, javaTypeClass));
					// }
				}
				result.add(baseBean);
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "aliasToJDBCBean", "contion中的BaseBean类为空！");
		}
		return result;
	}

	/**
	 * 数据库类型转java类型
	 * 
	 * @param DBDataType
	 * @return
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	private Class<?> DBDataTypeToJAVADataType(int type, int scale) {
		Class<?> javaTypeClass = null;
		switch (type) {
		case Types.LONGVARCHAR: // -1
			javaTypeClass = Long.class;
			break;
		case Types.CHAR: // 1
			javaTypeClass = Character.class;
			break;
		case Types.NUMERIC: // 2
			switch (scale) {
			case 0:
				javaTypeClass = Integer.class;
				break;
			case 2:
				javaTypeClass = Double.class;
				break;
			case -127:
				javaTypeClass = Double.class;
				break;
			default:
				javaTypeClass = Integer.class;
			}
			break;
		case Types.INTEGER:// 4 增加该类型以适应mysql数据库 modify by GuoR 2012-7-15
			javaTypeClass = Integer.class;
			break;
		case Types.VARCHAR: // 12
			javaTypeClass = String.class;
			break;
		case Types.DATE: // 91
			javaTypeClass = Date.class;
			break;
		case Types.TIMESTAMP: // 93
			javaTypeClass = Date.class;
			break;
		case Types.BLOB:
			javaTypeClass = Blob.class;
			break;
		default:
			javaTypeClass = String.class;
		}
		return javaTypeClass;
	}

	/**
	 * 数据库中读取出来值的对象类型转换成java数据类型的值
	 * 
	 * @param DBValue
	 * @return
	 * @author WangWB
	 * @date Oct 28, 2011
	 */
	private Object DBValueToJAVAValue(Object dbValue, Class<?> javaTypeClass) {
		Object javaValue = null;
		if (dbValue != null) {
			// 若为数值型
			if (dbValue instanceof BigDecimal) {
				// Integer型
				if (javaTypeClass.equals(Integer.class)) {
					BigDecimal tempValue = (BigDecimal) dbValue;
					javaValue = tempValue.intValue();
				}
				// Double型
				else if (javaTypeClass.equals(Double.class)) {
					BigDecimal tempValue = (BigDecimal) dbValue;
					javaValue = tempValue.doubleValue();
				}
			} else {
				javaValue = dbValue;
			}
		}
		return javaValue;
	}

	/**
	 * 释放由于使用JDBC而开启的ResultSet,Statement,Connection
	 * 
	 * @param rSet
	 *            用于待释放的ResultSet
	 * @param statement
	 *            用于待释放的Statement或PrepareStatement
	 * @param connection
	 *            用于待释放的Connection
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 2, 2011
	 */
	@Override
	public void releaseJDBCConnection(ResultSet rSet, Statement statement, Connection connection) throws Exception {
		if (rSet != null) {
			rSet.close();
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "releaseJDBCConnection", "ResultSet为空或已经关闭");
		}
		if (statement != null) {
			statement.close();
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "releaseJDBCConnection", "Statement为空或已经关闭");
		}
		if (connection != null) {
			DataSourceUtils.releaseConnection(connection, SessionFactoryUtils.getDataSource(this.getSessionFactory()));
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "releaseJDBCConnection", "Connection为空或已经关闭");
		}
	}

	/**
	 * 根据主键更新
	 * 
	 * @param entity
	 *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性有值
	 * @throws Exception
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public void updateEntityByPK(T entity) throws Exception {
		if (entity != null) {
			if (checkAnnotation(entity)) {
				// 更新之前清除session中的托管态的entity类型对象清除掉，防止更新时session中出现重复托管态对象
				getHibernateTemplate().clear();
				getHibernateTemplate().update(entity);
			}
		} else {
			log4jManager.errorCustomLog(getClass().getName(), "updateEntityByPK", "entity为空");
		}
	}

	/**
	 * 获得Connection
	 * 
	 * @return
	 * @author WangWB
	 * @date Aug 1, 2011
	 */
	@Override
	public Connection getJDBCConnection() throws Exception {
		return DataSourceUtils.getConnection(SessionFactoryUtils.getDataSource(this.getSessionFactory()));
	}

}
