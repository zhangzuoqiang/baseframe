package cn.hs.module.email.sendlog.dao;

import java.util.List;

import cn.hs.module.email.sendlog.domain.MailSendLog;
import cn.hs.module.email.sendlog.domain.MailSendLogCondition;

/**
 * Title: IMailSendLogDao<br>
 * Description: 邮件发送日志数据接口<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-08-02
 * @version $Revision$
 */
public interface IMailSendLogDao {
	/**
	 * 新增邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public void addMailSendLog(MailSendLog mailSendLog) throws Exception;

	/**
	 * 根据ID数组批量作废或启用邮件发送日志
	 * 
	 * @param condition
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public void discardOrReuseMailSendLog(MailSendLogCondition condition) throws Exception;

	/**
	 * 根据Id查询邮件发送日志
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public MailSendLog getMailSendLog(MailSendLogCondition condition) throws Exception;

	/**
	 * 查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public List<MailSendLog> getMailSendLogList(MailSendLogCondition condition) throws Exception;

	/**
	 * 更新邮件发送日志
	 * 
	 * @param mailSendLog
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public void updateMailSendLog(MailSendLog mailSendLog) throws Exception;

	/**
	 * 根据邮件发送日志编码查询邮件发送日志信息
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public MailSendLog viewMailSendLog(MailSendLogCondition condition) throws Exception;

	/**
	 * 查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public List<MailSendLog> listMailSendLog(MailSendLogCondition condition) throws Exception;

	/**
	 * count查询邮件发送日志集合
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public Long listMailSendLogCount(MailSendLogCondition condition) throws Exception;

	/**
	 * 根据ID数组获得邮件发送日志 批量操作时记录业务日志必须实现该方法，该方法将返回批量操作前数据内容
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public List<MailSendLog> getMailSendLogListByIDArray(String[] idArray) throws Exception;

	/**
	 * 根据ID获得邮件发送日志 更新操作时记录业务日志必须实现该方法，该方法将返回更新操作前数据内容
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 * @author LiuHG
	 * @date 2012-08-02
	 */
	public MailSendLog getMailSendLogByID(String id) throws Exception;

}
