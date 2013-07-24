/*
 * $Log: SystemParameterConstant.java,v $
 * Revision 1.2  2012/06/01 09:19:58  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.2  2012/06/01 09:15:50  guor
 * 增加根据系统参数KEY获取系统参数VALUE的方法
 *
 * Revision 1.1  2012/05/31 04:28:19  ronglt
 * 迁移系统参数到资源展现平台
 *
 * Revision 1.1  2012/05/25 06:07:21  ronglt
 * 增加系统参数缓存
 *
 * Revision 1.1  2012/04/13 08:41:49  guor
 * *** empty log message ***
 *
 */
package cn.hs.module.systemparameter;

/**
 * Title: SystemParameterConstant<br>
 * Description: 系统参数常量类<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author MengW
 * @createDate 2011-10-12
 * @version $Revision: 11 $
 */
public class SystemParameterConstant {

	public static String LOGPARA_ADDMETHOD_WITHLOG_PARANAME = "SysPara_AddMethodWithLog";//增加方法开启日志参数名
	
	public static String LOGPARA_UPDATEMETHOD_WITHLOG_PARANAME = "SysPara_UpdateMethodWithLog";//更新方法开启日志参数名
	
	public static String LOGPARA_BATCHADDMETHOD_WITHLOG_PARANAME = "SysPara_BatchAddMethodWithLog";//批量增加方法开启日志参数名
	
	public static String LOGPARA_BATCHUPDATEMETHOD_WITHLOG_PARANAME = "SysPara_BatchUpdateMethodWithLog";//批量更新方法开启日志参数名
	
	public static String LOGPARA_BATCHUPDATESTATEMETHOD_WITHLOG_PARANAME = "SysPara_BatchUpdateStateMethodWithLog";//批量更新状态方法开启日志参数名
	
	public static String LOGPARA_BATCHDELETEMETHOD_WITHLOG_PARANAME = "SysPara_BatchDeleteMethodWithLog";//批量删除方法开启日志参数名
	
	public static String COMMONPARA_DATEPATTERN_PARANAME = "SysPara_CommonDatePattern";//日期格式参数名
	
//	public static String RES_MULTI_APPRAISE ="SysPara_multiAppraise";//资源多次评价  by Liy at 2012-03-06
//	
//	public static String RES_MULTI_COMMENT="SysPara_multiComment";//资源多次评论 by Liy at 2012-03-06
	
	public static String WEBSERVICE_USER = "SysPara_WebServiceUser";//webService访问用户名 by GuoR 2012-5-31
	
	public static String WEBSERVICE_PASSWORD = "SysPara_WebServicePassword";//webService访问密码 by GuoR 2012-5-31
	
	public static String WSBSERVICE_URL = "SysPara_WebServiceURL";//webService访问地址 by GuoR 2012-5-31
	
	public static String IMG_EXT = "SysPara_Img_Ext"; // 图片类型 create by HuangS at 2012-09-14
	
	public static String IMG_MAX_SIZE = "SysPara_Img_Max_Size"; // 图片大小 create by HuangS at 2012-09-14
	
	public static String FOLDER_BASE_DIR = "SysPara_File_Folder"; // 文件上传根路径 create by HuangS at 2012-09-14
}
