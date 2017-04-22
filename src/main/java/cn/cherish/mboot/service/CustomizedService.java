package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.repository.CustomizedDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 需要自定义复杂操作
 * @author Cherish
 */
@Slf4j
@Service
@Transactional
public class CustomizedService {

    @Autowired
    private CustomizedDAO customizedDAO;

    /**
     * 删除用户与角色的中间表，对应roleId的行
     * @param roleId 角色ID
     * @return 是否成功删除
     */
    public boolean deleteUserRoleRelation(Long roleId){
        return customizedDAO.deleteUserRoleRelation(roleId);
    }

    /**
     * 删除角色与权限的中间表，对应permissionId的行
     * @param permissionId 权限ID
     * @return 是否删除成功
     */
    public boolean deleteRolePermissionRelation(Long permissionId){
        return customizedDAO.deleteRolePermissionRelation(permissionId);
    }

    /**
     * 冻结用户
     * @param userId 用户ID
     * @return 是否成功
     */
    public boolean freezeUser(Long userId){
        return customizedDAO.freezeUser(userId);
    }

    /**
     * 激活用户
     * @param userId 用户ID
     * @return 是否激活成功
     */
    public boolean activateUser(Long userId){
        return customizedDAO.activateUser(userId);
    }

}
