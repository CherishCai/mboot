package cn.cherish.mboot.web.dto;

import lombok.Data;

/**
 * Created by Cherish on 2017/2/6.
 */
@Data
public class RoleDTO implements java.io.Serializable{

    private static final long serialVersionUID = -4798776753955957049L;

    private Long id;

    private String name;

    private String description;

}
