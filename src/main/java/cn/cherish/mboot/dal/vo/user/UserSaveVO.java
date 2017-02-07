package cn.cherish.mboot.dal.vo.user;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserSaveVO implements java.io.Serializable {

    private static final long serialVersionUID = 5777281146489769093L;

    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,16}$", message="账号必须是6~16位字母和数字的组合")
    private String username;

    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,16}$", message="密码必须是6~16位字母和数字的组合")
    private String password;

    @Length(min = 1 ,max = 16 ,message = "{user.nickname}")
    private String nickname;

    @Pattern(regexp = "^[1][34578][0-9]{9}$", message = "请输入正确的手机号码")
    private String telephone;

    @DateTimeFormat(pattern="yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
//    @Past(message = "入职时间该在今天之前")
    private Date hiredate;

    @Range(min = 0, max = 1, message = "{user.active}")
    private Integer active;

    @Length(min = 0, max = 1024, message = "{user.description}")
    private String description;

}
