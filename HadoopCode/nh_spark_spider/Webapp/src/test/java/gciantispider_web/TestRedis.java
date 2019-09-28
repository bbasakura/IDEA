package gciantispider_web;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class TestRedis {
@SuppressWarnings("resource")
public static void main(String[] args) {
	Jedis jedis1 = new Jedis("192.168.56.204",7001);
	Jedis jedis2 = new Jedis("192.168.56.204",7002);
	Jedis jedis3 = new Jedis("192.168.56.204",7003);
	Set<String> keys1 = jedis1.keys("CSANTI_MONITOR_DP*");
	Set<String> keys2 = jedis2.keys("CSANTI_MONITOR_DP*");
	Set<String> keys3 = jedis3.keys("CSANTI_MONITOR_DP*");
	for (String string : keys1) {
		jedis1.del(string);
	}
	for (String string : keys2) {
		jedis2.del(string);
	}
	for (String string : keys3) {
		jedis3.del(string);
	}
	jedis1.close();
	jedis2.close();
	jedis3.close();
}
}
