package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Cherish on 2017/1/4.
 */
public interface RoleDAO extends IBaseDAO<Role,Long> {


    Role findByName(String name);

    @Query(value = "SELECT r.id,r.name,r.description FROM t_role AS r join t_user_role AS ur " +
            " WHERE r.id = ur.role_id AND ur.user_id = :userId ", nativeQuery = true)
    List<Role> findByUserId(@Param("userId") Long userId);


}
