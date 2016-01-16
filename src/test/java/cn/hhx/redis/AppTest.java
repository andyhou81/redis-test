package cn.hhx.redis;

import java.util.HashSet;
import java.util.Set;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * Unit test for simple App.
 */
public class AppTest  extends TestCase
{
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( AppTest.class );
//    }

    public void testRedis(){
    	JedisUtil.createJedis().set("test", "111");
    	System.out.println(JedisUtil.createJedis().get("test"));
    }
    public void testRedisPool(){
    	Jedis jedis = JedisPoolUtils.getJedis();
    	
    	jedis.set("test", "111");
    	System.out.println(jedis.get("test"));
    	//jedis.lset("myset", 1, "set1");
    	//jedis.lset("myset", 2, "set2");
    	//jedis.getset
    }
    public void testCluster(){
    	
    	
//    	Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
//    	//Jedis Cluster will attempt to discover cluster nodes automatically
//    	jedisClusterNodes.add(new HostAndPort("127.0.0.1", 7379));
//    	JedisCluster jc = new JedisCluster(jedisClusterNodes);
//    	jc.set("foo", "bar");
//    	String value = jc.get("foo");
    }
    
}
