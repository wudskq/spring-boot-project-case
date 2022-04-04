package cn.com.wudskq.model.common;

import cn.com.wudskq.annotation.CreateBy;
import cn.com.wudskq.annotation.CreateTime;
import cn.com.wudskq.annotation.UpdateBy;
import cn.com.wudskq.annotation.UpdateTime;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenfangchao
 * @title: BaseDTO
 * @projectName boot-project
 * @description: TODO 基础数据模型
 * @date 2022/4/4 6:03 AM
 */
@Data
public class BaseDTO implements Serializable {

    @CreateTime
    private Date createTime;

    @CreateBy
    private String createBy;

    @UpdateTime
    private Date updateTime;

    @UpdateBy
    private String updateBy;
}
