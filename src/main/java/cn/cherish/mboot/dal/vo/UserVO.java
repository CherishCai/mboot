package cn.cherish.mboot.dal.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserVO implements java.io.Serializable {

    private static final long serialVersionUID = -1645633795923583607L;

    private Long id;

    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,10}$", message="账号必须是6~16位数字和字母的组合")
    private String username;

    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,10}$", message="密码必须是6~16位数字和字母的组合")
    private String password;

    private String nickname;

    private String telephone;

    @DateTimeFormat(pattern="yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE_TIME)
    private Date hiredate;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm",iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdTime;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm",iso = DateTimeFormat.ISO.DATE_TIME)
    private Date modifiedTime;

    private String active;

    private String description;

}
