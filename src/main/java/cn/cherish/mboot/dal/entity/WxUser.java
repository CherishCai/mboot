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
@Table(name = "t_weixinuser")
public class WxUser implements java.io.Serializable {

	private static final long serialVersionUID = 5654579397240647401L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
    /**
     * 微信openid
     */
    @Column(name = "openid", nullable = false, length = 32)
	private String openid;
    /**
     * 昵称
     */
    @Column(name = "nickname", length = 32)
	private String nickname;
    /**
     * 性别
     */
    @Column(name = "sex")
	private Short sex;
    /**
     * 城市
     */
    @Column(name = "city", length = 10)
	private String city;
    /**
     * 关注时间
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "subscribe_time", nullable = false, length = 19)
	private Date subscribeTime;
    /**
     * 头像链接
     */
    @Column(name = "headimgurl")
	private String headimgurl;



}
