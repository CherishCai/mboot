package cn.cherish.mboot.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_article")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements java.io.Serializable {

	private static final long serialVersionUID = -9016983856075764634L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "title", nullable = false, length = 64)
	private String title;

    @Column(name = "content", nullable=false, columnDefinition = "mediumtext ")
    private String content;

	@Column(name = "read_sum", nullable = false, columnDefinition = "int default 0 ")
	private Integer readSum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_time", nullable = false, length = 19)
	private Date createdTime;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_time", nullable = false, length = 19)
	private Date modifiedTime;



}
