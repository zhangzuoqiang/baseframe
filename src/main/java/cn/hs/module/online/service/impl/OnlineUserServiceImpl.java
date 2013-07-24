package cn.hs.module.online.service.impl;

import java.util.Arrays;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hs.commons.utils.URLConnectionUtil;
import cn.hs.module.online.domain.OnlineUserCondition;
import cn.hs.module.online.domain.OnlineUserQB;
import cn.hs.module.online.service.IOnlineUserService;
import cn.hs.module.systemparameter.cache.SystemParameterCacheManager;

/**
 * Title: OnlineUserServiceImpl<br>
 * Description: 在线人数service实现<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 ORCHIS .All rights reserved.<br>
 * 
 * @author ZhangKW
 * @createDate 2012-5-29
 * @version $Revision: 11 $
 */
// 声明本类为Server实现类，声明bean名称
@Service(value = "cn.hs.module.online.service.impl.OnlineUserServiceImpl")
public class OnlineUserServiceImpl implements IOnlineUserService {

	@Autowired
	private SystemParameterCacheManager systemParameterCacheManager; // 系统参数缓存管理

	/**
	 * 查询在线人员列表
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 * @author ZhangKW
	 * @date 2012-5-29
	 */
	public List<OnlineUserQB> listOnlineUser(OnlineUserCondition condition) throws Exception {

		// 访问远程资源
		String contents = URLConnectionUtil.getInstanceByUrl(systemParameterCacheManager.getSystemParameterValue("SysPara_listOnlineUserURL")).readContents("UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		OnlineUserQB[] onlineUserArray = mapper.readValue(contents, OnlineUserQB[].class);

		List<OnlineUserQB> onlineUserList = Arrays.asList(onlineUserArray);

		return onlineUserList;
	}
}
