package cn.cherish.mboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * Created by Cherish on 2017/2/17.
 */
@Data
public class CustomerDTO implements java.io.Serializable {

    private static final long serialVersionUID = -3859558785433931029L;

    private Long id;

    private String nickname;

    private String telephone;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date modifiedTime;

    private String activeStr;

}
