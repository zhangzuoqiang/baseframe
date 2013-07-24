package cn.hs.module.email.sender.service.impl;

import java.io.File;
import java.io.Reader;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cn.hs.core.log4j.Log4jLoggerFactory;
import cn.hs.module.email.sender.service.IMailSender;
import cn.hs.module.email.sender.service.bo.MailMessage;
import cn.hs.module.email.serverconfig.domain.MailServerConfig;
import cn.hs.module.email.serverconfig.domain.MailServerConfigCondition;
import cn.hs.module.email.serverconfig.service.IMailServerConfigService;
import cn.hs.module.email.template.domain.MailTemplate;
import cn.hs.module.email.template.domain.MailTemplateCondition;
import cn.hs.module.email.template.service.IMailTemplateService;
import cn.hs.module.email.template.util.MarkUtils;

/**
 * Title: MailSender<br>
 * Description: 邮件发送<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author LiuHG
 * @createDate 2012-8-2
 * @version $Revision:$
 */
@Service(value = "cn.hs.module.email.sender.service.impl.MailSender")
public class MailSender implements IMailSender {

	private Logger logger = Log4jLoggerFactory.getInstance().getLogger();
	public static final int SEND_STATE_SUCCESS = 1;
	public static final int SEND_STATE_FAIL = 2;

	// private ExecutorService threadPoolExecutor =
	// Executors.newCachedThreadPool();

	private String charset = "UTF-8";

	@Autowired
	private JavaMailSenderImpl javaMailSender;

	@Autowired
	private TaskExecutor taskExecutor;// 注入Spring封装的异步执行器

	// 声明 MaintenanceLogService
	@Autowired
	private IMailTemplateService mailTemplateManager;

	// 邮件服务器配置接口
	@Autowired
	private IMailServerConfigService iMailServerConfigService;

	private List<DataSource> dataSourceList = new ArrayList<DataSource>();

	public void addAttachment(DataSource dataSource) {
		dataSourceList.add(dataSource);
	}

	public void addAttachment(File file) {
		dataSourceList.add(new FileDataSource(file));
	}

	public int send(MailMessage mailMessage) {
		if (mailMessage.getServerConfigAlias() == null || "".equals(mailMessage.getServerConfigAlias())) {
			// NO SERVER CONFIG
			logger.error("邮件发送失败，服务器配置别名不得为null");
			return SEND_STATE_FAIL;
		}

		MailServerConfig initConfig = initConfig(mailMessage.getServerConfigAlias());
		if (initConfig == null) {
			// NO SERVER CONFIG INIT FAIL
			logger.error("邮件服务器配置失败");
			return SEND_STATE_FAIL;
		}

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = null;
		try {
			helper = new MimeMessageHelper(mimeMessage, true, charset);
			helper.setFrom(initConfig.getEmail(), initConfig.getDisplayName());
			helper.setSubject(mailMessage.getSubject());
			if (mailMessage.getTo() == null && mailMessage.getCc() == null && mailMessage.getBcc() == null) {
				// NO RECEIVER
				logger.error("邮件配置失败，主送、抄送及密送至少给定一个");
				return SEND_STATE_FAIL;
			} else {
				if (mailMessage.getTo() != null) {
					helper.setTo(mailMessage.getTo());
				}
				if (mailMessage.getCc() != null) {
					helper.setCc(mailMessage.getCc());
				}
				if (mailMessage.getBcc() != null) {
					helper.setBcc(mailMessage.getBcc());
				}
			}

			if (mailMessage.getTemplateAlias() != null) {
				// 模板参数替换
				MailTemplateCondition condition = new MailTemplateCondition();
				condition.setSearchTemplateAlias(mailMessage.getTemplateAlias());
				MailTemplate mailTemplate = mailTemplateManager.viewMailTemplate(condition);
				// modify by HuangS at 2012-09-04 修改BaseClob中的Clob类型为String
				String content = mailTemplate.getContent().getContentClob();
				content = MarkUtils.replacePlaceholders(content, mailMessage.getProperties());

				helper.setText(content, true);
			} else {
				helper.setText(mailMessage.getContent(), true);
			}

			javaMailSender.send(mimeMessage);
		} catch (Exception e) {
			logger.error("邮件发送失败", e);
			return SEND_STATE_FAIL;
		}
		return SEND_STATE_SUCCESS;
	}

	/**
	 * 初始化邮件服务器配置
	 */
	private MailServerConfig initConfig(String serverConfigAlias) {
		MailServerConfigCondition mscCondition = new MailServerConfigCondition();
		mscCondition.setSearchConfigAlias(serverConfigAlias);
		try {
			MailServerConfig config = iMailServerConfigService.viewMailServerConfig(mscCondition);
			javaMailSender.setHost(config.getSmtpHost());
			javaMailSender.setPort(Integer.valueOf(config.getSmtpPort()));
			javaMailSender.setUsername(config.getUser());
			javaMailSender.setPassword(config.getPassword());
			Properties javaMailProperties = new Properties();
			// boolean auth = config.getNeedAuth().intValue() ==
			// MailServerConfig.NEED_AUTH_YES;
			javaMailProperties.put(MailServerConfig.MAIL_SMTP_AUTH, "true");
			javaMailSender.setJavaMailProperties(javaMailProperties);
			return config;
		} catch (Exception e) {
			logger.error("邮件服务器配置失败", e);
			return null;
		}
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	/**
	 * 读取clob字段的内容
	 * 
	 * @param clob
	 * @return
	 * @author XiangBin
	 * @date Aug 3, 2012
	 */
	public String readerClobToString(Clob clob) {
		StringBuffer strClob = new StringBuffer();
		try {
			Reader reader = clob.getCharacterStream();
			char[] buffer = new char[1024];
			int length = 0;
			while ((length = reader.read(buffer, 0, 1024)) != -1) {
				strClob.append(buffer, 0, length);
			}
		} catch (Exception e) {
			logger.error("邮件发送失败，从clob转换为string时出现异常", e);
		}
		return strClob.toString();
	}

	private class MailSendTask implements Runnable {

		private MailMessage mailMessage;

		private MailSendTask(MailMessage mailMessage) {
			this.mailMessage = mailMessage;
		}

		@Override
		public void run() {
			send(mailMessage);
		}

	}

	@Override
	public int send(MailMessage mailMessage, boolean async) {
		if (async) {
			Runnable command = new MailSendTask(mailMessage);
			// threadPoolExecutor.execute(command);
			taskExecutor.execute(command);
		} else {
			return this.send(mailMessage);
		}
		return SEND_STATE_SUCCESS;
	}

	// @PreDestroy
	// public void cleanup() {
	// threadPoolExecutor.shutdown();
	// }
}
