package cn.cherish.mboot.dal.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO implements java.io.Serializable {

    private static final long serialVersionUID = 3120001402512097515L;

    private Long id;

    private String title;

    private String content;

    private Integer readSum;

}