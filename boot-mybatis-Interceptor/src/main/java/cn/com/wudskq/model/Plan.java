package cn.com.wudskq.model;

import cn.com.wudskq.annotation.*;
import cn.com.wudskq.model.common.BaseDTO;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenfangchao
 * @title: Plan
 * @projectName boot-project
 * @description: TODO 计划数据模型
 * @date 2022/4/4 5:59 AM
 */
@SensitiveData
@Data
public class Plan extends BaseDTO implements Serializable {

    //id
    private Long id;

    //名称
    private String planName;

    //内容
    @SensitiveField
    private String planContent;

    public Plan(String planName, String planContent) {
        this.planName = planName;
        this.planContent = planContent;
    }
}
