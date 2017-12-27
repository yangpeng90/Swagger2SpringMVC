package com.yichangapp.mine.mapper;

import com.yichangapp.po.userInfo.FollowedAttention;

public interface FollowedAttentionMapper {
    int insert(FollowedAttention record);

    int insertSelective(FollowedAttention record);
}