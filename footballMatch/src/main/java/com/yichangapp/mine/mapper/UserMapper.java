package com.yichangapp.mine.mapper;

import java.util.List;

import com.yichangapp.po.response.Page;
import com.yichangapp.po.userInfo.User;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> queryUserList(Page page);
}