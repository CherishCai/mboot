package cn.cherish.mboot.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_permission")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements java.io.Serializable {
	
	private static final long serialVersionUID = 7008838524774836684L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "permit", nullable = false, length = 16)
	private String permit;

    @Column(name = "description", nullable=false, columnDefinition = "varchar(1024) default '' ")
    private String description;

	@JsonIgnore
	@ManyToMany(mappedBy="permissions",fetch = FetchType.LAZY)
	private List<Role> roles = new ArrayList<>();

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", permit='" + permit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
