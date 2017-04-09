package cn.cherish.mboot.dal.request.permission;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

/**
 * Created by Cherish on 2017/2/6.
 */
@Data
public class PermissionUpdateReq implements java.io.Serializable {

    private static final long serialVersionUID = -6491207752238389073L;

    @Min(value = 1, message = "{user.id}")
    private Long id;

    @Length(min = 1, max = 16, message="权限名必须是1~16位字母")
    private String permit;

    @Length(min = 0, max = 1024, message = "{user.description}")
    private String description;


}
