package cn.cherish.mboot.web.request.su;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Cherish on 2017/2/9.
 */
@Data
public class SuperUserRoleReq implements java.io.Serializable {

    private static final long serialVersionUID = -3712969985463314297L;
    @Length(min = 6,max = 16, message = "6-16个字符")
    String username;

    @NotNull(message = "错误的ID")
    List<Long> roleIds;


}
