-- 企业级集成服务初始化数据脚本

USE integrated_services_dev;

-- 插入部门数据
INSERT INTO `sys_dept` (`id`, `parent_id`, `dept_name`, `dept_code`, `leader`, `phone`, `email`, `status`, `sort_order`, `create_by`) VALUES
(1, 0, '总公司', 'ROOT', '张三', '15888888888', 'admin@company.com', 1, 0, 1),
(2, 1, '技术部', 'TECH', '李四', '15888888889', 'tech@company.com', 1, 1, 1),
(3, 1, '产品部', 'PRODUCT', '王五', '15888888890', 'product@company.com', 1, 2, 1),
(4, 1, '运营部', 'OPERATION', '赵六', '15888888891', 'operation@company.com', 1, 3, 1),
(5, 2, '后端开发组', 'BACKEND', '钱七', '15888888892', 'backend@company.com', 1, 1, 1),
(6, 2, '前端开发组', 'FRONTEND', '孙八', '15888888893', 'frontend@company.com', 1, 2, 1);

-- 插入角色数据
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `description`, `status`, `sort_order`, `create_by`) VALUES
(1, '超级管理员', 'SUPER_ADMIN', '超级管理员，拥有所有权限', 1, 0, 1),
(2, '系统管理员', 'ADMIN', '系统管理员，拥有系统管理权限', 1, 1, 1),
(3, '部门经理', 'MANAGER', '部门经理，拥有部门管理权限', 1, 2, 1),
(4, '普通用户', 'USER', '普通用户，拥有基本操作权限', 1, 3, 1);

-- 插入菜单数据
INSERT INTO `sys_menu` (`id`, `parent_id`, `menu_name`, `menu_type`, `path`, `component`, `permission`, `icon`, `status`, `sort_order`, `create_by`) VALUES
-- 一级菜单
(1, 0, '系统管理', 1, '/system', NULL, NULL, 'system', 1, 1, 1),
(2, 0, '用户管理', 1, '/user', NULL, NULL, 'user', 1, 2, 1),
(3, 0, '监控管理', 1, '/monitor', NULL, NULL, 'monitor', 1, 3, 1),

-- 系统管理子菜单
(101, 1, '部门管理', 2, '/system/dept', 'system/dept/index', 'system:dept:list', 'tree', 1, 1, 1),
(102, 1, '角色管理', 2, '/system/role', 'system/role/index', 'system:role:list', 'peoples', 1, 2, 1),
(103, 1, '菜单管理', 2, '/system/menu', 'system/menu/index', 'system:menu:list', 'tree-table', 1, 3, 1),

-- 用户管理子菜单
(201, 2, '用户列表', 2, '/user/list', 'user/list/index', 'user:list', 'user', 1, 1, 1),
(202, 2, '个人中心', 2, '/user/profile', 'user/profile/index', 'user:profile', 'user', 1, 2, 1),

-- 监控管理子菜单
(301, 3, '操作日志', 2, '/monitor/operlog', 'monitor/operlog/index', 'monitor:operlog:list', 'form', 1, 1, 1),
(302, 3, '登录日志', 2, '/monitor/logininfor', 'monitor/logininfor/index', 'monitor:logininfor:list', 'logininfor', 1, 2, 1),
(303, 3, '系统监控', 2, '/monitor/server', 'monitor/server/index', 'monitor:server:list', 'server', 1, 3, 1),

-- 按钮权限
(1001, 101, '部门查询', 3, '', '', 'system:dept:query', '', 1, 1, 1),
(1002, 101, '部门新增', 3, '', '', 'system:dept:add', '', 1, 2, 1),
(1003, 101, '部门修改', 3, '', '', 'system:dept:edit', '', 1, 3, 1),
(1004, 101, '部门删除', 3, '', '', 'system:dept:remove', '', 1, 4, 1),

(1011, 102, '角色查询', 3, '', '', 'system:role:query', '', 1, 1, 1),
(1012, 102, '角色新增', 3, '', '', 'system:role:add', '', 1, 2, 1),
(1013, 102, '角色修改', 3, '', '', 'system:role:edit', '', 1, 3, 1),
(1014, 102, '角色删除', 3, '', '', 'system:role:remove', '', 1, 4, 1),

(1021, 103, '菜单查询', 3, '', '', 'system:menu:query', '', 1, 1, 1),
(1022, 103, '菜单新增', 3, '', '', 'system:menu:add', '', 1, 2, 1),
(1023, 103, '菜单修改', 3, '', '', 'system:menu:edit', '', 1, 3, 1),
(1024, 103, '菜单删除', 3, '', '', 'system:menu:remove', '', 1, 4, 1),

(2001, 201, '用户查询', 3, '', '', 'user:query', '', 1, 1, 1),
(2002, 201, '用户新增', 3, '', '', 'user:add', '', 1, 2, 1),
(2003, 201, '用户修改', 3, '', '', 'user:edit', '', 1, 3, 1),
(2004, 201, '用户删除', 3, '', '', 'user:remove', '', 1, 4, 1),
(2005, 201, '重置密码', 3, '', '', 'user:resetPwd', '', 1, 5, 1);

-- 插入角色菜单关联数据
INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `create_by`) VALUES
-- 超级管理员拥有所有权限
(1, 1, 1, 1), (2, 1, 2, 1), (3, 1, 3, 1),
(4, 1, 101, 1), (5, 1, 102, 1), (6, 1, 103, 1),
(7, 1, 201, 1), (8, 1, 202, 1),
(9, 1, 301, 1), (10, 1, 302, 1), (11, 1, 303, 1),
(12, 1, 1001, 1), (13, 1, 1002, 1), (14, 1, 1003, 1), (15, 1, 1004, 1),
(16, 1, 1011, 1), (17, 1, 1012, 1), (18, 1, 1013, 1), (19, 1, 1014, 1),
(20, 1, 1021, 1), (21, 1, 1022, 1), (22, 1, 1023, 1), (23, 1, 1024, 1),
(24, 1, 2001, 1), (25, 1, 2002, 1), (26, 1, 2003, 1), (27, 1, 2004, 1), (28, 1, 2005, 1),

-- 系统管理员权限
(29, 2, 1, 1), (30, 2, 2, 1), (31, 2, 3, 1),
(32, 2, 101, 1), (33, 2, 102, 1), (34, 2, 103, 1),
(35, 2, 201, 1), (36, 2, 202, 1),
(37, 2, 301, 1), (38, 2, 302, 1),
(39, 2, 1001, 1), (40, 2, 1002, 1), (41, 2, 1003, 1),
(42, 2, 1011, 1), (43, 2, 1012, 1), (44, 2, 1013, 1),
(45, 2, 2001, 1), (46, 2, 2002, 1), (47, 2, 2003, 1),

-- 普通用户权限
(48, 4, 2, 1), (49, 4, 202, 1);

-- 插入用户数据（密码都是 123456，已使用BCrypt加密）
INSERT INTO `sys_user` (`id`, `username`, `password`, `real_name`, `email`, `phone`, `gender`, `status`, `dept_id`, `role_id`, `create_by`) VALUES
(1, 'admin', '$2a$10$7JB720yubVSOfvVWbGRjHOC.fxntNWjzMHW9BINfEKR/Q1VtYGYOu', '超级管理员', 'admin@company.com', '15888888888', 1, 1, 1, 1, 1),
(2, 'system', '$2a$10$7JB720yubVSOfvVWbGRjHOC.fxntNWjzMHW9BINfEKR/Q1VtYGYOu', '系统管理员', 'system@company.com', '15888888889', 1, 1, 2, 2, 1),
(3, 'zhangsan', '$2a$10$7JB720yubVSOfvVWbGRjHOC.fxntNWjzMHW9BINfEKR/Q1VtYGYOu', '张三', 'zhangsan@company.com', '15888888890', 1, 1, 5, 3, 1),
(4, 'lisi', '$2a$10$7JB720yubVSOfvVWbGRjHOC.fxntNWjzMHW9BINfEKR/Q1VtYGYOu', '李四', 'lisi@company.com', '15888888891', 0, 1, 6, 4, 1),
(5, 'wangwu', '$2a$10$7JB720yubVSOfvVWbGRjHOC.fxntNWjzMHW9BINfEKR/Q1VtYGYOu', '王五', 'wangwu@company.com', '15888888892', 1, 1, 3, 4, 1);
