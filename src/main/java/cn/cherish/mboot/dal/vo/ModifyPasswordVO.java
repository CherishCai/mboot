package cn.cherish.mboot.dal.vo;

import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * Created by Cherish on 2017/2/4.
 */
@Data
public class ModifyPasswordVO implements java.io.Serializable {

    private static final long serialVersionUID = 6675494721221351239L;

    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,10}$", message="{user.password}")
    private String oldPassword;
    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,10}$", message="{user.password}")
    private String password;
    @Pattern(regexp="^(?![0-9]+$)[0-9A-Za-z]{6,10}$", message="{user.password}")
    private String confirmPassword;

}
