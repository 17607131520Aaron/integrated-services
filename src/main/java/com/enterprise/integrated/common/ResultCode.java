package com.enterprise.integrated.common;

/**
 * 响应状态码枚举
 */
public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(0, "操作成功"),

    /* 客户端错误 */
    BAD_REQUEST(9001, "请求参数错误"),
    UNAUTHORIZED(9002, "未授权"),
    FORBIDDEN(9003, "禁止访问"),
    NOT_FOUND(9004, "资源不存在"),
    METHOD_NOT_ALLOWED(9005, "请求方法不允许"),
    CONFLICT(9006, "资源冲突"),
    UNPROCESSABLE_ENTITY(9007, "请求参数校验失败"),

    /* 服务器错误 */
    INTERNAL_SERVER_ERROR(9000, "服务器内部错误"),
    BAD_GATEWAY(9008, "网关错误"),
    SERVICE_UNAVAILABLE(9009, "服务不可用"),

    /* 业务错误码 */
    BUSINESS_ERROR(9100, "业务处理失败"),
    DATA_NOT_EXIST(9101, "数据不存在"),
    DATA_ALREADY_EXIST(9102, "数据已存在"),
    OPERATION_NOT_ALLOWED(9103, "操作不被允许"),

    /* 系统错误码 */
    SYSTEM_BUSY(9200, "系统繁忙，请稍后重试"),
    SYSTEM_TIMEOUT(9201, "系统超时"),
    SYSTEM_MAINTENANCE(9202, "系统维护中");

    private final Integer code;
    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
