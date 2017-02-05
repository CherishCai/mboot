package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 需要自定义复杂操作继承此抽象类
 * createNativeQuery -> native sql
 * createQuery       -> hsql
 * @author Cherish
 */
@Repository
public final class CustomizedDAO {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * 删除用户与角色的中间表，对应roleId的行
     * @param roleId 角色ID
     * @return 是否成功删除
     */
    @Transactional(readOnly = false)
    public boolean deleteUserRoleRelation(Long roleId){
        String sql = "DELETE ur FROM t_user_role AS ur WHERE ur.role_id = :roleId";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("roleId", roleId);

        return nativeQuery.executeUpdate() > 0;
    }

    /**
     * 删除角色与权限的中间表，对应permissionId的行
     * @param permissionId 权限ID
     * @return 是否删除成功
     */
    @Transactional(readOnly = false)
    public boolean deleteRolePermissionRelation(Long permissionId){
        String sql = "DELETE rp FROM t_role_permission AS rp WHERE rp.permission_id = :permissionId";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("permissionId", permissionId);

        return nativeQuery.executeUpdate() > 0;
    }

    /**
     * 冻结用户
     * @param userId 用户ID
     * @return 是否冻结成功
     */
    @Transactional(readOnly = false)
    public boolean freezeUser(Long userId){
        String sql = "UPDATE t_user SET is_active = 0 WHERE id = :userId";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("userId", userId);

        return nativeQuery.executeUpdate() > 0;
    }

    /**
     * 激活用户
     * @param userId 用户ID
     * @return 是否激活成功
     */
    @Transactional(readOnly = false)
    public boolean activateUser(Long userId){
        String sql = "UPDATE t_user SET is_active = 1 WHERE id = :userId";
        Query nativeQuery = entityManager.createNativeQuery(sql);
        nativeQuery.setParameter("userId", userId);

        return nativeQuery.executeUpdate() > 0;
    }

    // TODO 自行封装Page just Demo
    public Page<User> findByPageRequest(PageRequest pageRequest){
        String sql = "FROM User AS u";
        Query query = entityManager.createQuery(sql, User.class);
        query.setFirstResult((pageRequest.getPageNumber()-1) * pageRequest.getPageSize());
        query.setMaxResults(pageRequest.getPageSize());
        return new PageImpl<>(query.getResultList(),pageRequest,2);
    }

}
