package cn.cherish.mboot.dal.request.role;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * Created by Cherish on 2017/2/6.
 */
@Data
public class RoleUpdateReq implements java.io.Serializable {

    private static final long serialVersionUID = 4498632553629891827L;

    @Min(value = 1, message = "{user.id}")
    private Long id;

    @Length(min = 1, max = 16, message="权限名必须是1~16位字母")
    private String name;

    @Length(min = 0, max = 1024, message = "{user.description}")
    private String description;


}
