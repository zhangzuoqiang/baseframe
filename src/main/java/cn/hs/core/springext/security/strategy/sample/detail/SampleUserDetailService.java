package cn.hs.core.springext.security.strategy.sample.detail;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cn.hs.commons.utils.Md5Util;
import cn.hs.core.log4j.ILog4jManager;
import cn.hs.module.user.domain.UserInfoCondition;
import cn.hs.module.user.service.IUserInfoService;

/**
 * Title: SampleUserDetailService<br>
 * Description: 本地用户验证样例实现类<br>
 * Company: GOLDGOV<br>
 * Copyright @ 2012 GOLDGOV .All rights reserved.<br>
 * @author GuoR
 * @createDate 2012-8-27
 * @version $Revision:$
 */
public class SampleUserDetailService implements UserDetailsService {

	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	protected ILog4jManager log4jManager;

	/*
	 * (non-Javadoc)为当前登录用户绑定权限
	 * 
	 * @seeorg.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {

		log4jManager.debugCustomLog(getClass().getName(), "loadUserByUsername", " User Message is " + loginName);
		if ((loginName == null || "".equals(loginName) || "null".equals(loginName)) && loginName.indexOf("#") != -1)
			return null;
		boolean enables = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		String password = "";
		String[] type = loginName.split("#");
		if (type[0].equals("inner")) {
			UserInfoCondition userCondition = new UserInfoCondition();
			userCondition.setSearchLoginID(type[1]);
			List<cn.hs.module.user.domain.User> userList = userInfoService.getUserInfo(userCondition);
			if (userList != null && userList.size() > 0) {
				cn.hs.module.user.domain.User user = userList.iterator().next();
				password = user.getPassWord();
				enables = user.getActiveState().equals(cn.hs.module.user.domain.User.IS_ACTIVE_Y);
			}
		}
		// 绑定权限
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(type[1]);
		// 把密码md5加密
		password = Md5Util.getMd5(password);
		// System.out.println(Md5Util.getMd5(password));

		User userdetail = new User(loginName, password, enables, accountNonExpired, credentialsNonExpired, accountNonLocked, grantedAuths);
		return userdetail;
	}

	// /**
	// * 得到md5 加密后 的字符串
	// * @param source
	// * @return
	// * @author Limk
	// * @createDate 2012-10-13
	// */
	// public static String getMD5(String string) {
	//		
	// if(string == null || string.equals("")){
	// return "";
	// }
	// byte[] source = string.getBytes();
	// String s = null;
	// char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
	// '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
	// 'e', 'f' };
	// try {
	// java.security.MessageDigest md = java.security.MessageDigest
	// .getInstance("MD5");
	// md.update(source);
	// byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
	// // 用字节表示就是 16 个字节
	// char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
	// // 所以表示成 16 进制需要 32 个字符
	// int k = 0; // 表示转换结果中对应的字符位置
	// for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
	// // 转换成 16 进制字符的转换
	// byte byte0 = tmp[i]; // 取第 i 个字节
	// str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
	// // >>>
	// // 为逻辑右移，将符号位一起右移
	// str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
	// }
	// s = new String(str); // 换后的结果转换为字符串
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return s;
	// }

	/**
	 * 取得用户的权限
	 * 
	 * @param user
	 * @return
	 * @author ZhangKW
	 * @date 2011-5-26
	 */
	private Set<GrantedAuthority> obtionGrantedAuthorities(String loginName) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		// 登录后的所有用户增加一个已登录资源访问权限
		authSet.add(new SimpleGrantedAuthority("IS_AUTHENTICATED_ANONYMOUSLY"));
		return authSet;
	}
}
