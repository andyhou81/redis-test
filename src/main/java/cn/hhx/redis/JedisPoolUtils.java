/**
 * 
 */
package cn.hhx.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author andy_hou
 *
 */
public class JedisPoolUtils {
	
	private static JedisPool pool;
	private static final String PASSWORD = "happy8zx";
	private static final String HOST = "127.0.0.1";
	private static final int PORT = 6379;
	private static final int CONNECTION_TIMEOUT = 10000;//10s

    /**
     * 建立连接池 真实环境，一般把配置参数缺抽取出来。
     * 
     */
    private static void createJedisPool() {

    
        // 建立连接池配置参数
        JedisPoolConfig config = new JedisPoolConfig();
        // 设置最大连接数
        config.setMaxTotal(100);

        // 设置最大阻塞时间，记住是毫秒数milliseconds
        config.setMaxWaitMillis(2000);

        // 设置空间连接
        config.setMaxIdle(10);

        // 创建连接池
        pool = new JedisPool(config, HOST, PORT,CONNECTION_TIMEOUT,PASSWORD);
     

    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
        if (pool == null)
            createJedisPool();
    }

    /**
     * 获取一个jedis 对象
     * 
     * @return
     */
    public static Jedis getJedis() {

        if (pool == null)
            poolInit();
        return pool.getResource();
    }

    /**
     * 归还一个连接
     * 
     * @param jedis
     */
    public static void returnRes(Jedis jedis) {
        pool.returnResource(jedis);
        
    }

}
