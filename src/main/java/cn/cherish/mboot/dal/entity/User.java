package cn.cherish.mboot.dal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "t_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements java.io.Serializable {

    private static final long serialVersionUID = -3703091209635157421L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 16)
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 40)
    private String password;

    @Column(name = "nickname", nullable = false, length = 16)
    private String nickname;

    @Column(name = "telephone", nullable = false, length = 16)
    private String telephone;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hiredate", nullable = false, length = 19)
    private Date hiredate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_time", nullable = false, length = 19)
    private Date createdTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_time", nullable = false, length = 19)
    private Date modifiedTime;

    @Column(name = "is_active", nullable = false)
    private Integer active;

    @Column(name = "description", nullable=false, columnDefinition = "varchar(1024) default '' ")
    private String description;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false)})
    private List<Role> roles = new ArrayList<>();

    @Transient
    public Set<String> getRolesName() {
        Set<String> rolesName = new HashSet<>();
        for(Role role: this.roles){
            rolesName.add(role.getName());
        }
        return rolesName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                ", active=" + active +
                ", description='" + description + '\'' +
                '}';
    }
}
