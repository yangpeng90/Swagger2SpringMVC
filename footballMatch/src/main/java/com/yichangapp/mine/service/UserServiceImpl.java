package com.yichangapp.mine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yichangapp.mine.mapper.UserMapper;
import com.yichangapp.po.response.Page;
import com.yichangapp.po.userInfo.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;
	
	@Override
	public User getUserById(String id) {
		
		return userMapper.selectByPrimaryKey(id);
		
	}

	@Override
	public void insertUser(User user) {
		
		userMapper.insertSelective(user);
		
	}

	@Override
	public List<User> userList(Page page) {
		
		return userMapper.queryUserList(page);
		
	}

}
