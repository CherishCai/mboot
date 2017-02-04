package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.entity.Role;
import cn.cherish.mboot.repository.CustomizedDAO;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.repository.RoleDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "roles")
public class RoleService extends ABaseService<Role, Long> {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private CustomizedDAO customizedDAO;

    @Override
    protected IBaseDAO<Role, Long> getEntityDAO() {
        return roleDAO;
    }

    /*不建议如此强硬，该手动去除关联再删除
    @Transactional(readOnly = false)
    public void delete(Long roleId){
        //先删除t_user_role表的外键关联
        customizedDAO.deleteUserRoleRelation(roleId);
        //再删除role
        roleDAO.delete(roleId);
    }*/

}
