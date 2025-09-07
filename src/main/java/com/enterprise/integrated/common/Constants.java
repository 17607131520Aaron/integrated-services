package com.enterprise.integrated.common;

/**
 * 系统常量类
 */
public class Constants {

    /**
     * 用户状态
     */
    public static class UserStatus {
        /** 禁用 */
        public static final Integer DISABLED = 0;
        /** 启用 */
        public static final Integer ENABLED = 1;
    }

    /**
     * 性别
     */
    public static class Gender {
        /** 女 */
        public static final Integer FEMALE = 0;
        /** 男 */
        public static final Integer MALE = 1;
    }

    /**
     * 逻辑删除
     */
    public static class Deleted {
        /** 未删除 */
        public static final Integer NOT_DELETED = 0;
        /** 已删除 */
        public static final Integer DELETED = 1;
    }

    /**
     * 菜单类型
     */
    public static class MenuType {
        /** 目录 */
        public static final Integer CATALOG = 1;
        /** 菜单 */
        public static final Integer MENU = 2;
        /** 按钮 */
        public static final Integer BUTTON = 3;
    }

    /**
     * 业务类型
     */
    public static class BusinessType {
        /** 其它 */
        public static final Integer OTHER = 0;
        /** 新增 */
        public static final Integer INSERT = 1;
        /** 修改 */
        public static final Integer UPDATE = 2;
        /** 删除 */
        public static final Integer DELETE = 3;
        /** 授权 */
        public static final Integer GRANT = 4;
        /** 导出 */
        public static final Integer EXPORT = 5;
        /** 导入 */
        public static final Integer IMPORT = 6;
        /** 强退 */
        public static final Integer FORCE = 7;
        /** 生成代码 */
        public static final Integer GENCODE = 8;
        /** 清空数据 */
        public static final Integer CLEAN = 9;
    }

    /**
     * 操作状态
     */
    public static class OperationStatus {
        /** 正常 */
        public static final Integer SUCCESS = 0;
        /** 异常 */
        public static final Integer FAIL = 1;
    }

    /**
     * 缓存键前缀
     */
    public static class CacheKey {
        /** 用户缓存前缀 */
        public static final String USER_KEY = "user:";
        /** 角色缓存前缀 */
        public static final String ROLE_KEY = "role:";
        /** 菜单缓存前缀 */
        public static final String MENU_KEY = "menu:";
        /** 部门缓存前缀 */
        public static final String DEPT_KEY = "dept:";
        /** 验证码缓存前缀 */
        public static final String CAPTCHA_KEY = "captcha:";
        /** 登录用户缓存前缀 */
        public static final String LOGIN_USER_KEY = "login_user:";
    }

    /**
     * 缓存过期时间（秒）
     */
    public static class CacheExpire {
        /** 默认过期时间：30分钟 */
        public static final long DEFAULT = 30 * 60;
        /** 验证码过期时间：5分钟 */
        public static final long CAPTCHA = 5 * 60;
        /** 登录用户过期时间：12小时 */
        public static final long LOGIN_USER = 12 * 60 * 60;
        /** 菜单缓存过期时间：1小时 */
        public static final long MENU = 60 * 60;
    }

    /**
     * HTTP状态码
     */
    public static class HttpStatus {
        /** 成功 */
        public static final int SUCCESS = 200;
        /** 未授权 */
        public static final int UNAUTHORIZED = 401;
        /** 禁止访问 */
        public static final int FORBIDDEN = 403;
        /** 资源不存在 */
        public static final int NOT_FOUND = 404;
        /** 服务器内部错误 */
        public static final int ERROR = 500;
    }

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 系统用户ID
     */
    public static final Long SYSTEM_USER_ID = 1L;

    /**
     * 根部门ID
     */
    public static final Long ROOT_DEPT_ID = 0L;

    /**
     * 根菜单ID
     */
    public static final Long ROOT_MENU_ID = 0L;
}
