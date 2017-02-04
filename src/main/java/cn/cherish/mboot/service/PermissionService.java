package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.entity.Permission;
import cn.cherish.mboot.repository.CustomizedDAO;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.repository.PermissionDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "permissions")
public class PermissionService extends ABaseService<Permission, Long> {

    @Autowired
    private PermissionDAO permissionDAO;

    @Autowired
    private CustomizedDAO customizedDAO;

    @Override
    protected IBaseDAO<Permission, Long> getEntityDAO() {
        return permissionDAO;
    }

    /*不建议如此强硬，该手动去除关联再删除
    @Transactional(readOnly = false)
    public void delete(Long permissionId){
        //先删除t_role_permission表的外键关联
        customizedDAO.deleteRolePermissionRelation(permissionId);
        //再删除permission
        permissionDAO.delete(permissionId);
    }*/


}
