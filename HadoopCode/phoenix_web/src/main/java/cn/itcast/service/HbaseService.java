package cn.itcast.service;

import java.util.List;

import cn.itcast.pojo.User;

public interface HbaseService {
		
	/**
	 * 通过userId查询用户
	 * @param UserId
	 * @return List<User>
	 */
	public User queryUserByUserId(String UserId);
	
	/**
	 * 根据条件查询用户
	 * @return List<User>
	 */
	public List<User> queryUserByCondition(User user);
}
