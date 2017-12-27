package com.yichangapp.mine.service;

import java.util.List;

import com.yichangapp.po.response.Page;
import com.yichangapp.po.userInfo.User;

public interface UserService {

	User getUserById(String id);

	void insertUser(User user);

	List<User> userList(Page page);

}
