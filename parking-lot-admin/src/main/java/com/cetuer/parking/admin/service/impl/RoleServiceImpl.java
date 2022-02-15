package com.cetuer.parking.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.cetuer.parking.admin.domain.Role;
import com.cetuer.parking.admin.domain.RoleMenu;
import com.cetuer.parking.admin.mapper.RoleMapper;
import com.cetuer.parking.admin.mapper.RoleMenuMapper;
import com.cetuer.parking.admin.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色操作 业务实现层
 *
 * @author Cetuer
 * @date 2022/1/21 22:11
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImpl implements RoleService {
    private final RoleMapper roleMapper;
    private final RoleMenuMapper roleMenuMapper;

    @Override
    public List<Role> selectRoleAll() {
        return roleMapper.selectRoleAll();
    }

    @Override
    public List<Integer> selectRoleIdsByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId).stream().map(Role::getId).collect(Collectors.toList());
    }

    @Override
    public String selectRoleGroupByUserId(Integer userId) {
        return roleMapper.selectRolesByUserId(userId).stream().map(Role::getName).collect(Collectors.joining(","));
    }

    @Override
    public List<Role> selectRoleListByPage(Role role) {
        return roleMapper.selectRoleList(role);
    }

    @Override
    public Role selectRoleByRoleName(String roleName) {
        return roleMapper.selectRoleByRoleName(roleName);
    }

    @Override
    @Transactional
    public void insertRole(Role role) {
        roleMapper.insertRole(role);
        insertRoleMenu(role.getId(), role.getMenuIds());
    }

    @Override
    public Role selectRoleByRoleId(Integer roleId) {
        return roleMapper.selectRolesByRoleId(roleId);
    }

    @Override
    public void updateRole(Role role) {
        roleMapper.updateRole(role);
        roleMenuMapper.deleteByRoleIds(new Integer[]{role.getId()});
        insertRoleMenu(role.getId(), role.getMenuIds());
    }

    private void insertRoleMenu(Integer roleId, Set<Integer> menuIds) {
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<RoleMenu> roleMenuList = new ArrayList<>();
            for (Integer menuId : menuIds) {
                roleMenuList.add(new RoleMenu(roleId, menuId));
            }
            if (roleMenuList.size() > 0) {
                roleMenuMapper.insertRoleMenu(roleMenuList);
            }
        }
    }
}
