package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.Permission;
import cn.cherish.mboot.dal.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Cherish on 2017/1/4.
 */
public interface PermissionDAO extends IBaseDAO<Permission,Long> {

    Permission findByPermit(String permit);

    @Query(value = "SELECT p.id,p.permit,p.description FROM t_permission AS p join t_role_permission AS rp " +
            " WHERE p.id = rp.permission_id AND rp.user_id = :roleId ", nativeQuery = true)
    List<Permission> findByRoleId(@Param("roleId") Long roleId);

}
