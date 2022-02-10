package com.cetuer.parking.admin.mapper;

import com.cetuer.parking.admin.domain.UserRole;

import java.util.List;

/**
* 用户角色数据层
* 
* @author Cetuer
* @date 2022/2/8 10:07
*/
public interface UserRoleMapper {

    /**
     * 根据用户ids批量删除相关数据
     * @param ids 用户id列表
     * @return 删除个数
     */
    Integer deleteByUserIds(Integer[] ids);

    /**
     * 插入用户与角色关联数据
     * @param userRoleList 数据列表
     */
    void insertUserRole(List<UserRole> userRoleList);



}