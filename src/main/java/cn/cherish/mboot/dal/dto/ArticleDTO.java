package cn.cherish.mboot.dal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Cherish on 2017/2/12.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO implements java.io.Serializable {

    private static final long serialVersionUID = -4265666189867911606L;

    private Long id;

    private String title;

    private String content;

    private Integer readSum;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date createdTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone="UTC")
    private Date modifiedTime;


}