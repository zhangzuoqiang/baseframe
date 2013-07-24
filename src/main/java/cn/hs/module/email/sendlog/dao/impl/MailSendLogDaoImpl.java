package cn.hs.module.email.sendlog.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.hs.core.basedao.base.IBaseDao;
import cn.hs.core.basedao.condition.BaseCondition;
import cn.hs.module.email.sendlog.dao.IMailSendLogDao;
import cn.hs.module.email.sendlog.dao.require.MailSendLogRequire;
import cn.hs.module.email.sendlog.domain.MailSendLog;
import cn.hs.module.email.sendlog.domain.MailSendLogCondition;

/**
 * Title: MailSendLogDaoImpl<br>
 * Description: 邮件发送日志数据接口实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
@Repository(value = "cn.hs.module.email.sendlog.dao.impl.MailSendLogDaoImpl")
public class MailSendLogDaoImpl implements IMailSendLogDao {
	// 初始化baseDao
	@Autowired
	private IBaseDao<MailSendLog> baseDao;
	// 初始化mailSendLogRequire
	@Autowired
	private MailSendLogRequire mailSendLogRequire;

	/**
	 * 根据ID数组批量作废或启用邮件发送日志
	 * 
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public void discardOrReuseMailSendLog(MailSendLogCondition condition) throws Exception {
		baseDao.executeUpdate(mailSendLogRequire.discardOrReuseMailSendLogHQL(condition));
	}

	/**
	 * 根据Id查询邮件发送日志
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog getMailSendLog(MailSendLogCondition condition) throws Exception {
		return baseDao.findObject(MailSendLog.class, condition.getSearchLogID());
	}

	/**
	 * 查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailSendLog> getMailSendLogList(MailSendLogCondition condition) throws Exception {
		mailSendLogRequire.listMailSendLog(condition);
		// 设置不分页查询
		condition.setStart(-1);
		condition.setRows(-1);
		return (List<MailSendLog>) baseDao.pagedQuery(condition);
	}

	/**
	 * 更新邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public void updateMailSendLog(MailSendLog mailSendLog) throws Exception {
		baseDao.updateEntityByPK(mailSendLog);
	}

	/**
	 * 添加邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public void addMailSendLog(MailSendLog mailSendLog) throws Exception {
		baseDao.addEntity(mailSendLog);
	}

	/**
	 * 根据邮件发送日志编码查询邮件发送日志信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog viewMailSendLog(MailSendLogCondition condition) throws Exception {
		mailSendLogRequire.listMailSendLog(condition);
		List<?> result = baseDao.find(condition.getSql(), condition.getParameterList().toArray());
		if (result != null && result.size() > 0) {
			return (MailSendLog) result.get(0);
		}
		return null;
	}

	/**
	 * 查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailSendLog> listMailSendLog(MailSendLogCondition condition) throws Exception {
		mailSendLogRequire.listMailSendLog(condition);
		return (List<MailSendLog>) baseDao.pagedQuery(condition);
	}

	/**
	 * count邮件发送日志类型集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public Long listMailSendLogCount(MailSendLogCondition condition) throws Exception {
		mailSendLogRequire.countMailSendLogHql(condition);
		return baseDao.countQuery(condition);
	}

	/**
	 * 根据ID获得邮件发送日志 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@Override
	public MailSendLog getMailSendLogByID(String id) throws Exception {
		MailSendLog mailSendLog = baseDao.findObject(MailSendLog.class, id);
		baseDao.getHibernateSession().clear();
		return mailSendLog == null ? new MailSendLog() : mailSendLog;
	}

	/**
	 * 根据ID数组获得邮件发送日志 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MailSendLog> getMailSendLogListByIDArray(String[] idArray) throws Exception {
		baseDao.getHibernateSession().clear();
		BaseCondition condition = mailSendLogRequire.getMailSendLogListByIDArrayHQL(idArray);
		return (List<MailSendLog>) baseDao.find(condition.getSql(), condition.getParameterList().toArray());
	}

}
