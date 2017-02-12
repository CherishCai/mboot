package cn.cherish.mboot.dal.vo.su;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Cherish on 2017/2/9.
 */
@Data
public class SuperRolePermissionVO implements java.io.Serializable {

    private static final long serialVersionUID = 4048023739204872347L;

    @Length(min = 1,max = 16, message = "1-16个字符")
    String rolename;;

    @NotNull(message = "错误的ID")
    List<Long> permissionIds;


}
