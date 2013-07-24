package cn.hs.core.cache.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.hs.core.cache.IBaseCache;

/**
 * 
 * Title: BaseCache<br>
 * Description: 缓存的memCached实现<br>
 * Company: ORCHIS<br>
 * Copyright @ 2011 ORCHIS .All rights reserved.<br>
 * 
 * @author HuangS
 * @createDate Sep 22, 2011
 * @version $Revision: 1.2 $
 */
@SuppressWarnings("deprecation")
public class MemCachedBaseCache implements IBaseCache {

    // FIXME 需要改变实现方式
    // // 创建全局的唯一实例
    // protected static MemCachedClient mcc = new MemCachedClient();
    protected static MemCachedBaseCache memCached = new MemCachedBaseCache();

    //
    // // 设置与缓存服务器的连接池
    // static {
    //
    // // 获取properties文件
    // InputStream in = MemCachedBaseCache.class.getClassLoader()
    // .getResourceAsStream("memCached.properties");
    // // 创建一个Properties对象
    // Properties prop = new Properties();
    // try {
    // prop.load(in);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // // 获取socke连接池的实例对象
    // SockIOPool pool = SockIOPool.getInstance();
    //
    // // 获取配置文件的keys
    // Set<String> propNames = prop.stringPropertyNames();
    //
    // for (String string : propNames) {
    // try {
    // BeanUtils.setProperty(pool, string, prop.getProperty(string)
    // .split(","));
    // } catch (IllegalAccessException e) {
    // e.printStackTrace();
    // } catch (InvocationTargetException e) {
    // e.printStackTrace();
    // }
    // }
    // // 初始化连接池
    // pool.initialize();
    //
    // String compressEnable = prop.getProperty("compressEnable");
    // String compressThreshold = prop.getProperty("compressThreshold");
    // String primitiveAsString = prop.getProperty("primitiveAsString");
    // String defaultEncoding = prop.getProperty("defaultEncoding");
    // // 设定是否压缩放入cache中的数据
    // if (compressEnable != null && !"".equals(compressEnable)) {
    // mcc.setCompressEnable(Boolean.parseBoolean(compressEnable));
    // }
    // // 设定需要压缩的cache数据的阈值
    // if (compressThreshold != null && !"".equals(compressEnable)) {
    // mcc.setCompressThreshold(Integer.parseInt(compressThreshold));
    // }
    // // 设置cache数据的原始类型是String
    // if (primitiveAsString != null && !"".equals(primitiveAsString)) {
    // mcc.setPrimitiveAsString(Boolean.parseBoolean(primitiveAsString));
    // }
    // // 当primitiveAsString为true时使用的编码转化格式,默认值是utf-8
    // if (defaultEncoding != null && !"".equals(defaultEncoding)) {
    // mcc.setDefaultEncoding(defaultEncoding);
    // }
    // }

    /**
     * 保护型构造方法，不允许实例化！
     * 
     * @author HuangS
     * @date Sep 22, 2011
     */
    protected MemCachedBaseCache() {

    }

    /**
     * 获取唯一实例
     * 
     * @return
     * @author HuangS
     * @date Sep 22, 2011
     */
    public static MemCachedBaseCache getInstance() {
        return memCached;
    }

    /**
     * 写入缓存
     * 
     * @param key
     * @param value
     * @return
     * @author HuangS
     * @date Sep 22, 2011
     */
    @Override
    public boolean put(String key, Object value) {
        return false; // mcc.set(key, value);
    }

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
    @Override
    public boolean put(String key, Object value, Date date) {
        return false; // mcc.set(key, value, date);
    }

    /**
     * 读取缓存
     * 
     * @param key
     * @return
     * @author HuangS
     * @date Sep 22, 2011
     */
    @Override
    public Object get(String key) {
        // Object result = mcc.get(key);
        // if (result != null) {
        // return result;
        // }
        return null;
    }

    /**
     * 删除缓存
     * 
     * @param key
     * @return
     * @author HuangS
     * @date Sep 22, 2011
     */
    @Override
    public boolean remove(String key) {
        return false;// mcc.delete(key);
    }

    /**
     * 清空缓存
     * 
     * @return
     * @author HuangS
     * @date Sep 22, 2011
     */
    @Override
    public boolean removeAll() {
        return false;// mcc.flushAll();
    }

    @Override
    public boolean containsKey(String key) {
        return false;// mcc.keyExists(key);
    }

    /**
     * 获取缓存中的所有key
     * 
     * @return
     * @author HuangS
     * @date Jan 10, 2012
     */
    @Override
    public Set<String> getAllKey() {
        // Iterator<Map<String, String>> iterSlabs =
        // mcc.statsItems().values().iterator();
        // Set<String> indexSet = new HashSet<String>();
        Set<String> result = new HashSet<String>();
        // while (iterSlabs.hasNext()) {
        // Map<String, String> slab = iterSlabs.next();
        // for (String key : slab.keySet()) {
        // String index = key.split(":")[1];
        // indexSet.add(index);
        // }
        // }
        // for (String index : indexSet) {
        // Map<String, Map<String, String>> allMap =
        // mcc.statsCacheDump(Integer.parseInt(index), 0);
        // for (String string : allMap.keySet()) {
        // Map<String, String> cacheMap = allMap.get(string);
        // for (String key : cacheMap.keySet()) {
        // result.add(key);
        // // System.out.println(key);
        // }
        // }
        // }
        return result;
    }

}
