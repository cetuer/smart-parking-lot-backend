package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.api.domain.User;

/**
 * 用户操作数据层
 *
 * @author Cetuer
 * @date 2021/12/17 17:36
 */
public interface UserMapper {

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 用户
     */
    User selectUserByUsername(String username);

    /**
     * 根据用户ID查找用户
     * @param userId 用户ID
     * @return 用户
     */
    User selectUserByUserId(Integer userId);
}