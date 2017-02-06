package cn.cherish.mboot.dal.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class UserUpdateVO implements java.io.Serializable {

    @Min(value = 1, message = "{user.id}")
    private Long id;

    @Length(min = 1 ,max = 16 ,message = "{user.nickname}")
    private String nickname;

    @Pattern(regexp = "^[1][34578][0-9]{9}$", message = "请输入正确的手机号码")
    private String telephone;

    @DateTimeFormat(pattern="yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    @Past(message = "入职时间该在今天之前")
    private Date hiredate;

    @Range(min = 0, max = 1, message = "{user.active}")
    private Integer active;

    @Length(min = 0, max = 1024, message = "{user.description}")
    private String description;

}
