package com.mapper;

import java.util.List;

import com.entity.User;

/**
 * 用户相关
 */
public interface UserMapper {
	/**
	 * 根据用户名或邮箱名登录
	 * @param s 用户名或邮箱
	 * @return 用户信息
	 */
	User loginByNameOrEmail(String s);

	void addUser(User user);

	List<User> listUser();

	User getUerById(Integer userId);
	
}
