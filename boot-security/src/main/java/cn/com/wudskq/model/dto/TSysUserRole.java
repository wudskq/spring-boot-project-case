package cn.com.wudskq.model.dto;
 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_sys_user_role")
public class TSysUserRole {
 
    @TableId
    private String id;//主键
    private String userId;//用户名id
    private String roleId;//角色id
}

