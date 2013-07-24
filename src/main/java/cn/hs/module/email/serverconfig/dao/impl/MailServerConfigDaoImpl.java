package cn.hs.module.email.serverconfig.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.email.serverconfig.dao.IMailServerConfigDao;
import cn.hs.module.email.serverconfig.dao.require.MailServerConfigRequire;
import cn.hs.module.email.serverconfig.domain.MailServerConfig;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;

/**
 * Title: MailServerConfigDaoImpl<br>
 * Description: 邮件服务器配置数据接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-01
 * @version $Revision$
 */
@Repository(value = "cn.hs.module.email.serverconfig.dao.impl.MailServerConfigDaoImpl")
public class MailServerConfigDaoImpl implements IMailServerConfigDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<MailServerConfig> baseDao;
	// 初始化mailServerConfigRequire
	@Autowired
	private MailServerConfigRequire mailServerConfigRequire;

	/**
	 * 根据ID数组批量作废或启用邮件服务器配置
	 * 
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public void discardOrReuseMailServerConfig(MailServerConfigCondition condition) throws Exception {
		baseDao.executeUpdate(mailServerConfigRequire.discardOrReuseMailServerConfigHQL(condition));
	}

	/**
	 * 根据Id查询邮件服务器配置
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig getMailServerConfig(MailServerConfigCondition condition) throws Exception {
		return baseDao.findObject(MailServerConfig.class, condition.getSearchConfigID());
	}

	/**
	 * 查询邮件服务器配置集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailServerConfig> getMailServerConfigList(MailServerConfigCondition condition) throws Exception {
		mailServerConfigRequire.listMailServerConfig(condition);
		// 设置不分页查询
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<MailServerConfig>) baseDao.pagedQuery(condition);
	}

	/**
	 * 更新邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public void updateMailServerConfig(MailServerConfig mailServerConfig) throws Exception {
		baseDao.updateEntityByPK(mailServerConfig);
	}

	/**
	 * 添加邮件服务器配置
	 * 
	 * @param mailServerConfig
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public void addMailServerConfig(MailServerConfig mailServerConfig) throws Exception {
		baseDao.addEntity(mailServerConfig);
	}

	/**
	 * 根据邮件服务器配置编码查询邮件服务器配置信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig viewMailServerConfig(MailServerConfigCondition condition) throws Exception {
		mailServerConfigRequire.listMailServerConfig(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (MailServerConfig) result.get(0);
		}
		return null;
	}

	/**
	 * 查询邮件服务器配置集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailServerConfig> listMailServerConfig(MailServerConfigCondition condition) throws Exception {
		mailServerConfigRequire.listMailServerConfig(condition);
		return (List<MailServerConfig>) baseDao.pagedQuery(condition);
	}

	/**
	 * count邮件服务器配置类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public Long listMailServerConfigCount(MailServerConfigCondition condition) throws Exception {
		mailServerConfigRequire.countMailServerConfigHql(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 根据ID获得邮件服务器配置 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@Override
	public MailServerConfig getMailServerConfigByID(String id) throws Exception {
		MailServerConfig mailServerConfig = baseDao.findObject(MailServerConfig.class, id);
		baseDao.getHibernateSession().clear();
		return mailServerConfig == null ? new MailServerConfig() : mailServerConfig;
	}

	/**
	 * 根据ID数组获得邮件服务器配置 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailServerConfig> getMailServerConfigListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = mailServerConfigRequire.getMailServerConfigListByIDArrayHQL(idArray);
		return (List<MailServerConfig>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

}
