package cn.cherish.mboot.dal.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class UserSearchVO implements java.io.Serializable {

    private String username;

    private String nickname;

    private String telephone;

    @DateTimeFormat(pattern="yyyy-MM-dd",iso = DateTimeFormat.ISO.DATE)
    private Date hiredate;

    private Integer active;


}
