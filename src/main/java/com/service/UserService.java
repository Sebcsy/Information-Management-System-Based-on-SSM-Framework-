package com.service;
import java.util.List;

import com.entity.User;

/**
 * ҵ���ӿ�
 */
public interface UserService {
	/**
	 * �����û����������¼
	 * @param s �˺Ż�����
	 * @return �û���Ϣ
	 */
	User loginByNameOrEmail(String s);
	
	public void addUser(User user);

	List<User> listUser();

	User getUserById(Integer userId);


}