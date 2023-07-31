package com.mapper;

import java.util.List;

import com.entity.User;

/**
 * �û����
 */
public interface UserMapper {
	/**
	 * �����û�������������¼
	 * @param s �û���������
	 * @return �û���Ϣ
	 */
	User loginByNameOrEmail(String s);

	void addUser(User user);

	List<User> listUser();

	User getUerById(Integer userId);
	
}
