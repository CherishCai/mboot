package cn.cherish.mboot.dal.vo;
// Generated 2016-8-16 18:02:26 by Hibernate Tools 4.3.1

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto implements java.io.Serializable {

    private static final long serialVersionUID = -3703091209635157421L;

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String telephone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date createtime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date modifytime;
    private String active;
    private String description;
    private Long roleId;

}
