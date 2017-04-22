package cn.cherish.mboot.web;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Cherish on 2017/2/24.
 */
@Data
@AllArgsConstructor
public class MResponse<T> implements java.io.Serializable {
    private static final long serialVersionUID = -222983483999088181L;
    private Integer code;
    private Boolean success;
    private String message;
    private T data;
}
