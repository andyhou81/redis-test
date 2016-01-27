package cn.hhx.redis;

import java.util.HashSet;
import java.util.List;
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

    public void testRedisPool(){
    	Jedis jedis = JedisPoolUtils.getJedis();
    	
    	//jedis.set("test", "111");
    	
    	System.out.println(jedis.get("test"));
    	
    	System.out.println(jedis.incr("test"));
    	
    	System.out.println(jedis.decr("test"));
    	
    	jedis.set("TAGCODE_BSID_Test","1","NX","EX",10000);
    	
    	//jedis.lrang
    	//jedis.lset("myset", 1, "set1");
    	//jedis.lset("myset", 2, "set2");
    	//jedis.getset
    }
    
    public void testPushData(){
    
    	Jedis jedis = JedisPoolUtils.getJedis();
    	for(int i=0;i<5000;i++){
			jedis.lpush("latest.comments", "comments_"+i);
    	}
    	
    	jedis.close();
     
    }
    
    public void testMget(){
        
    	Jedis jedis = JedisPoolUtils.getJedis();
		List<String> list = jedis.mget("TAGCODE_BSID_Test","test");//("latest.comments", "comments_"+i);
    	for(String comment: list){
    		System.out.println(comment);
    	}
    	
    	jedis.close();
     
    }
    
    public void testRedisGetLatest5000ID(){
    	Jedis jedis = JedisPoolUtils.getJedis();
    	
    	
    	jedis.ltrim("latest.comments", 0, 100);
    	
    	//jedis.lrang
    	//jedis.lset("myset", 1, "set1");
    	//jedis.lset("myset", 2, "set2");
    	//jedis.getset
    }
    
    public void testExistsKey(){
    	
    	Jedis jedis = JedisPoolUtils.getJedis();
    	
    	long TIMEOUT = 5 ; // 5s
    	
    	for(int i=0;i<20;i++){
    		boolean exists = jedis.exists("TAGCODE_BSID");
    		if(!exists){
    			System.out.println(i);
    			jedis.set("TAGCODE_BSID","1","NX","EX",TIMEOUT);
    		
    		}
    		try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	
    	JedisPoolUtils.returnRes(jedis);
    	
    	
    	

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
