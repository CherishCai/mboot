package cn.cherish.mboot.web.request.user;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserSearchReq implements java.io.Serializable {

    private String username;

    private String nickname;

    private String telephone;

    private String position;

    @DateTimeFormat(pattern="yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private Date hiredate;

    private Integer active;


}
