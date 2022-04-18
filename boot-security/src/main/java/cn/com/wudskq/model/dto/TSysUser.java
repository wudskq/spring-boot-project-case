package cn.com.wudskq.model.dto;
 
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
 
import java.io.Serializable;
import java.util.Date;
 

@Data
@TableName("t_sys_user")
public class TSysUser implements Serializable {
 
    private static final long serialVersionUID = 1L;
 
    @TableId
    private String id;//主键
    private String username;//用户名
    private String password;//密码
    private String nickName;//昵称
    private String cellPhone;//电话
    private String mail;//邮件
    private Date birthday;//生日
    private String status;//状态（0-正常，1-禁用，2-删除）
    private String accountType;//1系统账号 2客户账号
    private String inviteCode;//邀请码
    private String sex;//性别：0男 1女
    private String address;//地址
    private Integer upNum;//获赞总量
    private Integer readNum;//文章被阅读总量
    private String sign;//签名
    private String pictureId;//用户头像
    private String createUser;//创建人
    private Date createDate;//创建时间
 
}

