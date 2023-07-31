package com.service.impl;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.entity.User;
import com.mapper.UserMapper;
import com.service.UserService;

@Service  //千万不要忘了这个注解
public class UserServiceImpl implements UserService {

	@Resource  //还有这个注解,它的作用是让spring帮我们注入依赖对象
	private UserMapper userMapper;
	 
	public User loginByNameOrEmail(String s) {
		return userMapper.loginByNameOrEmail(s);
	}	
	
	public void addUser(User user) {
		 userMapper.addUser(user);
	}

	public List<User> listUser() {
		return userMapper.listUser();
	}

	@Override
	public User getUserById(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.getUerById(userId);
	}
}