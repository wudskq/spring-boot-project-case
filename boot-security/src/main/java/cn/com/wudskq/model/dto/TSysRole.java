package cn.com.wudskq.model.dto;
 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
 
import java.util.Date;
 

@Data
@TableName("t_sys_role")
public class TSysRole {
 
    @TableId
    private String id;//主键
    private String roleName;//角色名称
    private String roleExplain;//角色描述
    private String createUser;//创建人
    private Date createDate;//创建时间
 
}

