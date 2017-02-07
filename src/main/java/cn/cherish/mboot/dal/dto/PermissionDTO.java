package cn.cherish.mboot.dal.dto;

import lombok.Data;

/**
 * Created by Cherish on 2017/2/6.
 */
@Data
public class PermissionDTO implements java.io.Serializable{

    private static final long serialVersionUID = -1801100759054480063L;
    private Long id;

    private String permit;

    private String description;

}
