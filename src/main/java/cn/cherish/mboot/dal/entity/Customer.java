package cn.cherish.mboot.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_customer")
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 2285174464789310329L;

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nickname", nullable = false, length = 32)
    private String nickname;

    @Column(name = "telephone", unique = true, nullable = false, length = 11)
    private String telephone;

    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", nullable = false, length = 19)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_time", length = 19)
    private Date modifiedTime;
    /**
     * 账户是否激活可用
     */
	@Column(name = "is_active", nullable = false)
	private Integer active;
    /**
     * 关联的微信账户ID
     */
    @Column(name = "weixinuser_id")
    private Long weixinuserId;

}
