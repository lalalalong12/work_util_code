package common.util.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author yangWenlong
 * @date 2020/12/1- ${time}
 */

public class EhCacheUtil {

    private static final Logger log = LoggerFactory.getLogger(EhCacheUtil.class);

    private CacheManager manager;

    private static EhCacheUtil ehCache;

    /**
     *数据上报下发数量缓存数据
     */
    public static final String DATA_MONITOR_CACHE = "DataMonitor";

    /**
     * 网关及设备状态缓存数据
     */
    public static final String STATUS_MONITORING_CACHE = "statusMonitoringCache";

    /**
     * 初始信息缓存
     */
    public static final String INIT_INFO = "initInfo";

    private static InputStream getInputStream() {
        ClassPathResource resource = new ClassPathResource("ehcache.xml");
        try {
            return resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获得缓存配置管理
     *
     * @param
     */
    private EhCacheUtil() {
        try {
            InputStream inputStream = getInputStream();
            manager = CacheManager.create(inputStream);
            System.setProperty(CacheManager.ENABLE_SHUTDOWN_HOOK_PROPERTY,"true");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取配置文件错误：cause[{}]", e.getMessage());
        }
    }

    /**
     * 初始化缓存管理类
     *
     * @return
     */
    public static EhCacheUtil getInstance() {
        try {
            if (ehCache == null) {
                ehCache = new EhCacheUtil();
                log.info("缓存初始化成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化错误：cause[{}]", e.getMessage());
        }
        return ehCache;
    }

    /**
     * 获取Cache类
     *
     * @param cacheName
     * @return
     */
    public Cache getCaches(String cacheName) {
        try {
            return manager.getCache(cacheName);
        } catch (IllegalStateException | ClassCastException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加缓存数据
     *
     * @param cacheName
     * @param key
     * @param value
     */
    public void put(String cacheName, String key, Object value) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = new Element(key, value);
            cache.put(element);
            cache.flush();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("添加缓存失败：cause[{}],cacheName[{}],key[{}],Object[{}]", e.getMessage(),cacheName, key, value);
        }
    }

    /**
     * 获取缓存数据
     *
     * @param cacheName
     * @param key
     * @return
     */
    public Object get(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            Element element = cache.get(key);
            cache.flush();
            return element == null ? null : element.getObjectValue();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取缓存数据失败：cause[{}],cacheName[{}],key[{}]", e.getMessage(),cacheName,key);
            return null;
        }
    }

    /**
     * 删除缓存数据
     *
     * @param cacheName
     * @param key
     */
    public void delete(String cacheName, String key) {
        try {
            Cache cache = manager.getCache(cacheName);
            cache.remove(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("删除缓存数据失败：cause[{}],cacheName[{}],key[{}]", e.getMessage(),cacheName,key);
        }
    }



    void destroy() {
        // 关闭tomcat时，执行相应的关闭
        ehCache.manager.shutdown();
    }

}