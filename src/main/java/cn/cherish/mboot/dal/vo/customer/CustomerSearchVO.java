package cn.cherish.mboot.dal.vo.customer;

import lombok.Data;

@Data
public class CustomerSearchVO implements java.io.Serializable {

    private static final long serialVersionUID = -6252802038931388533L;

    private String nickname;

    private String telephone;

    private Integer active;


}
