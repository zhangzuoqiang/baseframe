/*
 * $Log: SessionConstant.java,v $
 * Revision 1.1  2012/05/23 09:27:49  guor
 * 初次提交
 *
 */
package cn.hs.core.springext.mvc;

/**
 * Title: SessionConstant<br>
 * Description: session中KEY编码实体<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author GuoR
 * @createDate 2011-9-29
 * @version $Revision: 1.1 $
 */
public class SessionConstant {
	/**
	 * 用户信息:<br>
	 * CasUser
	 */
	public static String USER_INFO = "userInfo";
	public static String VENDOR_USER_INFO = "vendorUserInfo";
	/**
	 * 部门信息: <br>
	 * List<CasDepartment>
	 */
	public static String CURRENT_DEPARTMENT = "currentDepartment";
	/**
	 * 系统信息: <br>
	 * List<CasSystems>
	 */
	public static String SYSTEMS = "systems";
	public static String SCOPES = "scopes";
	public static String CURRENT_SCOPE = "currentScope";
	/**
	 * 当前用户角色用户的资源:<br>
	 * List<Resource>
	 */
	public static String RESOURCE_SCOPE = "resourceScopes";
	/**
	 * 角色信息:<br>
	 * List<CasRole>
	 */
	public static String ROLES = "roles";
	/**
	 * 当前选中角色:<br>
	 * CasRole
	 */
	public static String CURRENT_ROLE = "currentRole";

	/**
	 * 平台编码:<br>
	 * String
	 */
	public static String CURRENT_PLATFORM_CODE = "syscode";

	/**
	 * 当前登陆人的功能权限:<br>
	 * JSONObject
	 */
	public static final String CURRENT_PERMISSIONS = "currentPermissions";

}
