package cn.cherish.mboot.web.dto.su;

import lombok.Data;

/**
 * Created by Cherish on 2017/2/10.
 */
@Data
public class SuperRoleDTO implements java.io.Serializable {

    private static final long serialVersionUID = -4829442090080181543L;
    private Long id;
    private String name;
    private boolean have;

}
