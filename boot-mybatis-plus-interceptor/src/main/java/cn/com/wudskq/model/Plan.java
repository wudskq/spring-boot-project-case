package cn.com.wudskq.model;

import cn.com.wudskq.model.common.BaseDTO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenfangchao
 * @title: Plan
 * @projectName boot-project
 * @description: TODO 计划数据模型
 * @date 2022/4/4 5:59 AM
 */
@TableName("plan")
@Data
public class Plan extends BaseDTO implements Serializable {

    //id
    private Long id;

    //名称
    @TableField("plan_name")
    private String planName;

    @TableField("plan_content")
    private String planContent;

    public Plan(String planName, String planContent) {
        this.planName = planName;
        this.planContent = planContent;
    }
}
