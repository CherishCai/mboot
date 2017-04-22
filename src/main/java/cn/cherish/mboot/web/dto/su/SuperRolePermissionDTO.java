package cn.cherish.mboot.web.dto.su;

import lombok.Data;

import java.util.List;

/**
 * Created by Cherish on 2017/2/9.
 */
@Data
public class SuperRolePermissionDTO implements java.io.Serializable {

    private static final long serialVersionUID = 4048023739204872347L;
    String rolename;
    List<SuperPermissionDTO> permissions;

}
