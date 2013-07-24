/* $Id$ */
package cn.hs.core.basedao.base;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.hs.core.basedao.base.querybean.BaseBean;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.core.basedao.condition.BasePageCondition;

/**
 * Title: IBaseDao<br>
 * Description: 持久化层基类接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author WangWB
 * @createDate Aug 1, 2011
 * @version $Revision: 35 $
 */
public interface IBaseDao<T> {
    /**
     * 获得Session
     * 
     * @return
     * @author WangWB
     * @date Aug 1, 2011
     */
    public Session getHibernateSession() throws Exception;

    /**
     * 获得Connection
     * 
     * @return
     * @author WangWB
     * @date Aug 1, 2011
     */
    public Connection getJDBCConnection() throws Exception;

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
    public void releaseJDBCConnection(ResultSet rSet, Statement statement, Connection connection) throws Exception;

    /**
     * 获得HibernateTemplate对象
     * 
     * @return
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public HibernateTemplate getSpringHibernateTemplate() throws Exception;

    /**
     * 单个实体入库
     * 
     * @param entity
     *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性无值
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public void addEntity(T entity) throws Exception;

    /**
     * 多个同类型实体批量入库
     * 
     * @param entities
     *            实体对象集合，必须保证每个实体对象都带有@Entity标识，并且保证保证被@Id注解的属性无值
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public void batchAddEntities(Collection<T> entities) throws Exception;

    /**
     * 根据主键批量删除
     * 
     * @param entities
     *            实体对象集合，必须保证每个实体对象都带有@Entity标识，并且保证被@Id注解的属性有值
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public void batchDeleteEntities(Collection<T> entities) throws Exception;

    /**
     * 根据主键更新
     * 
     * @param entity
     *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性有值
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public void updateEntityByPK(T entity) throws Exception;

    /**
     * 根据主键删除
     * 
     * @param entity
     *            必须保证entity为带有@Entity标识的对象，并且保证被@Id注解的属性有值
     * @throws Exception
     * @author WangWB
     * @date Aug 1, 2011
     */
    public void deleteEntityByPK(T entity) throws Exception;

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
    public Collection<?> pagedQueryJDBC(BasePageCondition condition) throws Exception;

    /**
     * 分页查询
     * 
     * @param condition
     *            中需要包含sql ,startRow和rows为-1时，不分页查询
     * @return 返回查询结果，如果设置了BaseBean属性，返回BaseBean属性的集合
     * @throws Exception
     */
    public Collection<?> pagedQuery(BasePageCondition condition);

    /**
     * 使用JDBC查询Count
     * 
     * @param condition
     *            中包含sql和parameterList
     * @return 返回count数值
     * @throws Exception
     */
    public long countQueryJDBC(BaseCondition condition) throws Exception;

    /**
     * 使用hql查询count
     * 
     * @param condition
     *            中包含sql和parameterList
     * @return 返回count数值
     */
    public long countQuery(BaseCondition condition);

    /**
     * 对延迟加载的实体PO执行初始化
     * 
     * @param entity
     */
    public void initialize(Object entity);

    /**
     * 根据ID获取PO实例,如果对象不存在会抛出异常
     * 
     * @param clazz
     *            实体类
     * @param id
     *            实体ID
     * @return 返回相应的持久化PO实例
     */
    public T loadObject(Class<T> clazz, Serializable id);

    /**
     * 根据ID获取PO实例
     * 
     * @param clazz
     *            实体类
     * @param id
     *            实体ID
     * @return 返回相应的持久化PO实例
     */
    public T findObject(Class<T> clazz, Serializable id);

    /**
     * 获取PO的所有对象
     * 
     * @param clazz
     *            实体类
     * @return 返回相应的持久化PO实例集合
     */
    public List<T> loadAll(Class<T> clazz);

    /**
     * 执行特定的HQL
     * 
     * @param condition
     *            中包含sql和parameterList
     * @return
     */
    public int executeUpdate(BaseCondition condition);

    /**
     * 执行带参的HQL查询,已折旧参见 find(BaseCondition condition)
     * 
     * @param sql
     * @param params
     * @return 查询结果
     */
    @Deprecated
    public List<?> find(String hql, Object... params);

    /**
     * 执行带参的HQL查询
     * 
     * @param condition
     * @return 查询结果
     */
    public List<?> find(BaseCondition condition);

    /**
     * 执行SQL查询
     * 
     * @param beanClass
     * @param sql
     * @param params
     * @return
     */
    public List<?> findJDBC(Class<? extends BaseBean> beanClass, String sql, Object... params) throws Exception;

}
