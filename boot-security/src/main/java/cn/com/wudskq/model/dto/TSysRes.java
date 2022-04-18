package cn.com.wudskq.model.dto;
 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
 
import java.util.Date;
 

@Data
@TableName("t_sys_res")
public class TSysRes {
 
    @TableId
    private String id;//主键
    private String name;//资源名称
    private String resUrl;//资源路径
    private String permission;//做拦截的code
    private String resType;//0菜单   1按钮
    private String pid;//父级id
    private String icon;//菜单图标
    private String createUser;//创建人
    private Date createDate;//创建时间
 
}

