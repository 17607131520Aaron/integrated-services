package com.enterprise.integrated.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enterprise.integrated.common.ResultCode;
import com.enterprise.integrated.dao.UserMapper;
import com.enterprise.integrated.dto.UserDTO;
import com.enterprise.integrated.entity.User;
import com.enterprise.integrated.exception.BusinessException;
import com.enterprise.integrated.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户服务实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public Long createUser(UserDTO userDTO) {
        logger.info("创建用户: {}", userDTO.getUsername());
        
        // 检查用户名是否已存在
        if (existsByUsername(userDTO.getUsername())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (StrUtil.isNotBlank(userDTO.getEmail()) && existsByEmail(userDTO.getEmail())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "邮箱已存在");
        }
        
        // 检查手机号是否已存在
        if (StrUtil.isNotBlank(userDTO.getPhone()) && existsByPhone(userDTO.getPhone())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "手机号已存在");
        }

        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        
        // 加密密码
        if (StrUtil.isNotBlank(userDTO.getPassword())) {
            user.setPassword(BCrypt.hashpw(userDTO.getPassword()));
        }
        
        // 设置默认值
        if (user.getStatus() == null) {
            user.setStatus(1); // 默认启用
        }
        if (user.getGender() == null) {
            user.getGender(); // 默认未知
        }

        int result = userMapper.insert(user);
        if (result > 0) {
            logger.info("用户创建成功，ID: {}", user.getId());
            return user.getId();
        }
        
        throw new BusinessException("用户创建失败");
    }

    @Override
    public User getUserById(Long id) {
        if (id == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(ResultCode.DATA_NOT_EXIST, "用户不存在");
        }
        
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户名不能为空");
        }
        
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean updateUser(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        
        logger.info("更新用户信息: {}", userDTO.getId());
        
        User existUser = getUserById(userDTO.getId());
        
        // 检查用户名是否被其他用户使用
        if (!existUser.getUsername().equals(userDTO.getUsername()) && existsByUsername(userDTO.getUsername())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "用户名已存在");
        }
        
        // 检查邮箱是否被其他用户使用
        if (StrUtil.isNotBlank(userDTO.getEmail()) && 
            !userDTO.getEmail().equals(existUser.getEmail()) && existsByEmail(userDTO.getEmail())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "邮箱已存在");
        }
        
        // 检查手机号是否被其他用户使用
        if (StrUtil.isNotBlank(userDTO.getPhone()) && 
            !userDTO.getPhone().equals(existUser.getPhone()) && existsByPhone(userDTO.getPhone())) {
            throw new BusinessException(ResultCode.DATA_ALREADY_EXIST, "手机号已存在");
        }

        User user = new User();
        BeanUtil.copyProperties(userDTO, user);
        
        // 如果密码不为空，则加密
        if (StrUtil.isNotBlank(userDTO.getPassword())) {
            user.setPassword(BCrypt.hashpw(userDTO.getPassword()));
        } else {
            user.setPassword(null); // 不更新密码
        }

        int result = userMapper.updateById(user);
        return result > 0;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (id == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        
        logger.info("删除用户: {}", id);
        
        getUserById(id); // 验证用户是否存在
        int result = userMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public boolean deleteUsers(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID列表不能为空");
        }
        
        logger.info("批量删除用户: {}", ids);
        
        int result = userMapper.deleteBatchIds(ids);
        return result > 0;
    }

    @Override
    public IPage<User> getUserPage(Page<User> page, String username, String realName, Integer status) {
        return userMapper.selectUserPage(page, username, realName, status);
    }

    @Override
    public List<User> getUsersByDeptId(Long deptId) {
        if (deptId == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "部门ID不能为空");
        }
        
        return userMapper.selectByDeptId(deptId);
    }

    @Override
    public List<User> getUsersByRoleId(Long roleId) {
        if (roleId == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "角色ID不能为空");
        }
        
        return userMapper.selectByRoleId(roleId);
    }

    @Override
    public boolean updateUserStatus(Long id, Integer status) {
        if (id == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        if (status == null || (status != 0 && status != 1)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "状态值不正确");
        }
        
        logger.info("更新用户状态: {} -> {}", id, status);
        
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        
        int result = userMapper.updateById(user);
        return result > 0;
    }

    @Override
    public boolean resetPassword(Long id, String newPassword) {
        if (id == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        if (StrUtil.isBlank(newPassword)) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "新密码不能为空");
        }
        
        logger.info("重置用户密码: {}", id);
        
        User user = new User();
        user.setId(id);
        user.setPassword(BCrypt.hashpw(newPassword));
        
        int result = userMapper.updateById(user);
        return result > 0;
    }

    @Override
    public boolean updateLastLoginTime(Long userId) {
        if (userId == null) {
            throw new BusinessException(ResultCode.BAD_REQUEST, "用户ID不能为空");
        }
        
        int result = userMapper.updateLastLoginTime(userId);
        return result > 0;
    }

    @Override
    public boolean existsByUsername(String username) {
        if (StrUtil.isBlank(username)) {
            return false;
        }
        
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return userMapper.selectCount(wrapper) > 0;
    }

    @Override
    public boolean existsByEmail(String email) {
        if (StrUtil.isBlank(email)) {
            return false;
        }
        
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getEmail, email);
        return userMapper.selectCount(wrapper) > 0;
    }

    @Override
    public boolean existsByPhone(String phone) {
        if (StrUtil.isBlank(phone)) {
            return false;
        }
        
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getPhone, phone);
        return userMapper.selectCount(wrapper) > 0;
    }
}
