package cn.com.wudskq.model.vo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.servlet.ServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Data
public class Result {

    @ApiModelProperty(value = "返回码")
    private int code;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    @ApiModelProperty(value = "返回描述")
    private String msg;

    @ApiModelProperty(value = "返回长度")
    private long count;

    /**
     * 返回成功
     */
    public static Result success(List<Object> data, long count) {
        Result result = new Result();
        result.setCode(0);//成功
        result.setMsg("成功");//提示语
        result.setData(data);
        result.setCount(count);
        return result;
    }

    /**
     * 返回成功
     */
    public static Result success(List data) {
        Result result = new Result();
        result.setCode(0);//成功
        result.setMsg("成功");//提示语
        result.setData(data);
        result.setCount(data == null || data.size() == 0 ? 0 : data.size());
        return result;
    }

    /**
     * 返回成功
     */
    public static Result successForPage(List data, Integer count) {
        Result result = new Result();
        result.setCode(0);//成功
        result.setMsg("成功");//提示语
        result.setData(data);
        result.setCount(count == null ? 0 : count);
        return result;
    }

    /**
     * 返回成功
     */
    public static Result success() {
        Result result = new Result();
        result.setCode(0);//成功
        result.setMsg("成功");//提示语
        return result;
    }

    /**
     * 返回成功
     */
    public static Result success(Object object) {
        Result result = new Result();
        result.setCode(0);//成功
        result.setMsg("成功");//提示语
        result.setData(object);//返回内容
        return result;
    }

    /**
     * 返回失败
     */
    public static Result error() {
        Result result = new Result();
        result.setCode(1);//失败
        result.setMsg("失败");//提示语
        return result;
    }

    /**
     * 返回失败
     */
    public static Result error(int code, String msg) {
        Result result = new Result();
        result.setCode(code);//失败
        result.setMsg(msg);//提示语
        return result;
    }

    /**
     * 返回信息
     */
    public static Result response(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * Response输出Json格式
     */
    public static void responseJson(ServletResponse response, Object data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
            out.flush();
        } catch (Exception e) {
            System.out.println("Response输出Json异常：" + e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

}