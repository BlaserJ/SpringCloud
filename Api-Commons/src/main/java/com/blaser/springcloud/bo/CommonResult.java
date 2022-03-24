package com.blaser.springcloud.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 返回给前端的通用结果实体类
 * @author: Blaser
 * @create: 2022-03-21 17:56
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CommonResult<T> {
    /**
     * 请求码
     */
    private Integer code;

    /**
     * 附加消息
     */
    private String message;

    /**
     * 附加数据
     */
    private T data;

    /**
     * 不设置data的构造函数
     * @param code 请求码
     * @param message 附带消息
     */
    public CommonResult(Integer code, String message){
        this(code, message, null);
    }
}
