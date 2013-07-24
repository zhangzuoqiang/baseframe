package cn.hs.core.cache;

import java.util.Date;
import java.util.Set;

/**
 * 
 * Title: IBaseCache<br>
 * Description: 缓存基类接口<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * @author HuangS
 * @createDate Sep 22, 2011
 * @version $Revision: 1.2 $
 */
public interface IBaseCache {

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 * @author HuangS
	 * @date Sep 22, 2011
	 */
	public boolean put(String key, Object value);

	/**
	 * 写入缓存，包括过期时间
	 * 
	 * @param key
	 * @param value
	 * @param date
	 * @return
	 * @author HuangS
	 * @date Sep 22, 2011
	 */
	public boolean put(String key, Object value, Date date);

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 * @author HuangS
	 * @date Sep 22, 2011
	 */
	public Object get(String key);

	/**
	 * 删除缓存
	 * 
	 * @param key
	 * @return
	 * @author HuangS
	 * @date Sep 22, 2011
	 */
	public boolean remove(String key);

	/**
	 * 清空缓存
	 * 
	 * @return
	 * @author HuangS
	 * @date Sep 22, 2011
	 */
	public boolean removeAll();

	/**
	 * 判断对象是否包含key
	 * 
	 * @param key
	 * @return
	 * @author RongLT
	 * @date 2011-10-14
	 */
	public boolean containsKey(String key);
	
	/**
	 * 获取缓存中的所有key
	 * 
	 * @return
	 * @author HuangS
	 * @date Jan 10, 2012
	 */
	public Set<String> getAllKey();
}
