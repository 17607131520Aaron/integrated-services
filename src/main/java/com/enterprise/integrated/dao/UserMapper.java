package com.enterprise.integrated.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enterprise.integrated.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户数据访问层
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询用户
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 分页查询用户列表
     */
    IPage<User> selectUserPage(Page<User> page, @Param("username") String username, 
                              @Param("realName") String realName, @Param("status") Integer status);

    /**
     * 根据部门ID查询用户列表
     */
    List<User> selectByDeptId(@Param("deptId") Long deptId);

    /**
     * 根据角色ID查询用户列表
     */
    List<User> selectByRoleId(@Param("roleId") Long roleId);

    /**
     * 更新用户最后登录时间
     */
    int updateLastLoginTime(@Param("userId") Long userId);
}
