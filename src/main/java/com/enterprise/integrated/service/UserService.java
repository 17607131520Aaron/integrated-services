package com.enterprise.integrated.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enterprise.integrated.dto.UserDTO;
import com.enterprise.integrated.entity.User;

import java.util.List;

/**
 * 用户服务接口
 */
public interface UserService {

    /**
     * 创建用户
     */
    Long createUser(UserDTO userDTO);

    /**
     * 根据ID获取用户
     */
    User getUserById(Long id);

    /**
     * 根据用户名获取用户
     */
    User getUserByUsername(String username);

    /**
     * 更新用户信息
     */
    boolean updateUser(UserDTO userDTO);

    /**
     * 删除用户
     */
    boolean deleteUser(Long id);

    /**
     * 批量删除用户
     */
    boolean deleteUsers(List<Long> ids);

    /**
     * 分页查询用户列表
     */
    IPage<User> getUserPage(Page<User> page, String username, String realName, Integer status);

    /**
     * 根据部门ID查询用户列表
     */
    List<User> getUsersByDeptId(Long deptId);

    /**
     * 根据角色ID查询用户列表
     */
    List<User> getUsersByRoleId(Long roleId);

    /**
     * 启用/禁用用户
     */
    boolean updateUserStatus(Long id, Integer status);

    /**
     * 重置用户密码
     */
    boolean resetPassword(Long id, String newPassword);

    /**
     * 更新用户最后登录时间
     */
    boolean updateLastLoginTime(Long userId);

    /**
     * 检查用户名是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 检查邮箱是否存在
     */
    boolean existsByEmail(String email);

    /**
     * 检查手机号是否存在
     */
    boolean existsByPhone(String phone);
}
