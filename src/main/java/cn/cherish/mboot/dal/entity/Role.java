package cn.cherish.mboot.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role implements java.io.Serializable {

	private static final long serialVersionUID = 2207185006755635269L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "name", nullable = false, length = 16)
	private String name;

	@Column(name = "description", nullable=false, columnDefinition = "varchar(1024) default '' ")
	private String description;

	@JsonIgnore
	@ManyToMany(mappedBy="roles",fetch = FetchType.LAZY)
	private List<User> users = new ArrayList<>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_role_permission", joinColumns = {
			@JoinColumn(name = "role_id", nullable = false) }, inverseJoinColumns = {
			@JoinColumn(name = "permission_id", nullable = false) })
	private List<Permission> permissions = new ArrayList<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
