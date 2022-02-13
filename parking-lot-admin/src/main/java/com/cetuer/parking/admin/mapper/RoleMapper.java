package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.domain.Role;

import java.util.List;
import java.util.Set;

/**
 * 角色 数据层
 *
 * @author Cetuer
 * @date 2022/1/22 10:39
 */
public interface RoleMapper {

    /**
     * 查找所有角色信息
     * @return 角色列表
     */
    List<Role> selectRoleAll();

    /**
     * 根据用户id查找其角色id列表
     * @param userId 用户id
     * @return 角色id列表
     */
    List<Role> selectRolesByUserId(Integer userId);

    /**
     * 根据用户id判断是否是超级管理员角色
     * @param userId 用户id
     * @return true->是；false->否
     */
    Boolean isAdminRole(Integer userId);
}
