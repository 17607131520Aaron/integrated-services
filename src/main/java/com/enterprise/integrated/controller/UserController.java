package com.enterprise.integrated.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.enterprise.integrated.common.Result;
import com.enterprise.integrated.dto.UserDTO;
import com.enterprise.integrated.entity.User;
import com.enterprise.integrated.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户管理控制器
 */
@Tag(name = "用户管理", description = "用户相关接口")
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "创建用户", description = "创建新用户")
    @PostMapping
    public Result<Long> createUser(@Valid @RequestBody UserDTO userDTO) {
        Long userId = userService.createUser(userDTO);
        return Result.success("用户创建成功", userId);
    }

    @Operation(summary = "获取用户详情", description = "根据用户ID获取用户详细信息")
    @GetMapping("/{id}")
    public Result<User> getUserById(@Parameter(description = "用户ID") @PathVariable @NotNull Long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    @Operation(summary = "更新用户信息", description = "更新用户基本信息")
    @PutMapping
    public Result<Void> updateUser(@Valid @RequestBody UserDTO userDTO) {
        boolean success = userService.updateUser(userDTO);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户更新失败");
        }
    }

    @Operation(summary = "删除用户", description = "根据用户ID删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@Parameter(description = "用户ID") @PathVariable @NotNull Long id) {
        boolean success = userService.deleteUser(id);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户删除失败");
        }
    }

    @Operation(summary = "批量删除用户", description = "根据用户ID列表批量删除用户")
    @DeleteMapping("/batch")
    public Result<Void> deleteUsers(@Parameter(description = "用户ID列表") @RequestBody @NotEmpty List<Long> ids) {
        boolean success = userService.deleteUsers(ids);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户批量删除失败");
        }
    }

    @Operation(summary = "分页查询用户", description = "分页查询用户列表，支持条件筛选")
    @GetMapping("/page")
    public Result<IPage<User>> getUserPage(
            @Parameter(description = "页码") @RequestParam(defaultValue = "1") Long current,
            @Parameter(description = "每页大小") @RequestParam(defaultValue = "10") Long size,
            @Parameter(description = "用户名") @RequestParam(required = false) String username,
            @Parameter(description = "真实姓名") @RequestParam(required = false) String realName,
            @Parameter(description = "状态") @RequestParam(required = false) Integer status) {
        
        Page<User> page = new Page<>(current, size);
        IPage<User> userPage = userService.getUserPage(page, username, realName, status);
        return Result.success(userPage);
    }

    @Operation(summary = "根据部门查询用户", description = "根据部门ID查询用户列表")
    @GetMapping("/dept/{deptId}")
    public Result<List<User>> getUsersByDeptId(@Parameter(description = "部门ID") @PathVariable @NotNull Long deptId) {
        List<User> users = userService.getUsersByDeptId(deptId);
        return Result.success(users);
    }

    @Operation(summary = "根据角色查询用户", description = "根据角色ID查询用户列表")
    @GetMapping("/role/{roleId}")
    public Result<List<User>> getUsersByRoleId(@Parameter(description = "角色ID") @PathVariable @NotNull Long roleId) {
        List<User> users = userService.getUsersByRoleId(roleId);
        return Result.success(users);
    }

    @Operation(summary = "更新用户状态", description = "启用或禁用用户")
    @PutMapping("/{id}/status")
    public Result<Void> updateUserStatus(
            @Parameter(description = "用户ID") @PathVariable @NotNull Long id,
            @Parameter(description = "状态：0-禁用，1-启用") @RequestParam @NotNull Integer status) {
        
        boolean success = userService.updateUserStatus(id, status);
        if (success) {
            return Result.success();
        } else {
            return Result.error("用户状态更新失败");
        }
    }

    @Operation(summary = "重置用户密码", description = "重置用户密码")
    @PutMapping("/{id}/password")
    public Result<Void> resetPassword(
            @Parameter(description = "用户ID") @PathVariable @NotNull Long id,
            @Parameter(description = "新密码") @RequestParam @NotNull String newPassword) {
        
        boolean success = userService.resetPassword(id, newPassword);
        if (success) {
            return Result.success();
        } else {
            return Result.error("密码重置失败");
        }
    }

    @Operation(summary = "检查用户名是否存在", description = "检查用户名是否已被使用")
    @GetMapping("/check/username")
    public Result<Boolean> checkUsername(@Parameter(description = "用户名") @RequestParam String username) {
        boolean exists = userService.existsByUsername(username);
        return Result.success(exists);
    }

    @Operation(summary = "检查邮箱是否存在", description = "检查邮箱是否已被使用")
    @GetMapping("/check/email")
    public Result<Boolean> checkEmail(@Parameter(description = "邮箱") @RequestParam String email) {
        boolean exists = userService.existsByEmail(email);
        return Result.success(exists);
    }

    @Operation(summary = "检查手机号是否存在", description = "检查手机号是否已被使用")
    @GetMapping("/check/phone")
    public Result<Boolean> checkPhone(@Parameter(description = "手机号") @RequestParam String phone) {
        boolean exists = userService.existsByPhone(phone);
        return Result.success(exists);
    }
}
