package cn.cherish.mboot.web.dto.su;

import lombok.Data;

/**
 * Created by Cherish on 2017/2/10.
 */
@Data
public class SuperPermissionDTO implements java.io.Serializable {

    private static final long serialVersionUID = 8048511489976115684L;
    private Long id;
    private String permit;
    private boolean have;

}
