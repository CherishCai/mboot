
package cn.cherish.mboot.web.dto.su;

import lombok.Data;

import java.util.List;

/**
 * Created by Cherish on 2017/2/9.
 */
@Data
public class SuperUserRoleDTO implements java.io.Serializable {

    private static final long serialVersionUID = -3712969985463314297L;
    String username;

    List<SuperRoleDTO> roles;

}
