package cn.com.wudskq.model.dto;
 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
 

@Data
@TableName("t_sys_role_res")
public class TSysRoleRes {
 
    @TableId
    private String id;//主键
    private String roleId;//角色id
    private String resId;//资源id
 
}

