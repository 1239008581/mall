package com.worm.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: 自定义响应数据结构
 * 这个类是提供给门户，ios，安卓，微信商城用的
 * 门户接受此类数据后需要使用本类的方法转换成对于的数据类型格式（类，或者list）
 * 其他自行处理
 * 200：表示成功
 * 401：权限问题
 * 500：表示错误，错误信息在msg字段中
 * 501：bean验证错误，
 * 502：拦截器拦截到用户token出错
 * 555：异常抛出信息
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("返回消息的结构体")
public class JsonResult {

    @ApiModelProperty("返回消息的状态")
    private Integer status;

    @ApiModelProperty("返回消息的信息")
    private String msg;

    @ApiModelProperty("返回消息的数据")
    private Object data;

    public static JsonResult ok(){
        return new JsonResult(200,"ok",null);
    }

    public static JsonResult ok(Object data) {
        return new JsonResult(200, "ok", data);
    }

    public static JsonResult error(String msg) {
        return new JsonResult(500, msg, null);
    }

    public static JsonResult parameterError(String msg){
        return new JsonResult(400,msg,null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null);
    }

    public static JsonResult errorException(String msg) {
        return new JsonResult(555, msg, null);
    }

}
