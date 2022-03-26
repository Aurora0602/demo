package com.zhangheng.demo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public Result() {
        this.code = 200;
        this.message = "操作成功";
    }

    /**
     * 用于@valid错误处理
     *
     * @param code    错误码
     * @param message 错误提示信息
     */
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 用于封装返回数据
     *
     * @param code    返回码
     * @param message 提示信息
     */
    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    //系统错误
    public static Result error(int code, String message) {
        return new Result(code, message);
    }

    //操作成功，返回数据
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    //操作成功，无返回数据
    public static Result success() {
        return new Result(200, "success");
    }

    //操作失败，返回数据
    public static Result fail(Object data) {
        return new Result(200, "fail", data);
    }

    //操作失败，无返回数据
    public static Result fail() {
        return new Result(200, "fail");
    }
}
