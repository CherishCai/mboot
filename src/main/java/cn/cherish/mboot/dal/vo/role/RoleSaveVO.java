package cn.cherish.mboot.dal.vo.role;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * Created by Cherish on 2017/2/6.
 */
@Data
public class RoleSaveVO implements java.io.Serializable {

    private static final long serialVersionUID = -6371855737632998948L;

    @Pattern(regexp="^[A-Za-z]{1,16}$", message="角色名必须是1~16位字母")
    private String name;

    @Length(min = 0, max = 1024, message = "{user.description}")
    private String description;

}
