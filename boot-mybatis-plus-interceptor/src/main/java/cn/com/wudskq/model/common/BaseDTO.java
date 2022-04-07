package cn.com.wudskq.model.common;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(fill = FieldFill.INSERT,value = "create_time")
    private Date createTime;

    @TableField("create_by")
    private String createBy;

    @TableField(fill = FieldFill.INSERT,value = "update_time")
    private Date updateTime;

    @TableField("update_by")
    private String updateBy;
}
