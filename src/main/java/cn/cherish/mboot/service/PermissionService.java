package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.dto.PermissionDTO;
import cn.cherish.mboot.dal.entity.Permission;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.permission.PermissionSaveVO;
import cn.cherish.mboot.dal.vo.permission.PermissionUpdateVO;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.repository.PermissionDAO;
import cn.cherish.mboot.util.ObjectConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PermissionService extends ABaseService<Permission, Long> {

    @Autowired
    private PermissionDAO permissionDAO;

    @Override
    protected IBaseDAO<Permission, Long> getEntityDAO() {
        return permissionDAO;
    }

    public Page<PermissionDTO> findAll(BasicSearchVO basicSearchVO) {

        int pageNumber = basicSearchVO.getStartIndex() / basicSearchVO.getPageSize() + 1;
        Page<Permission> permissionPage = this.findAll(pageNumber, basicSearchVO.getPageSize());

        return permissionPage.map(source -> {
            PermissionDTO roleDTO = new PermissionDTO();
            ObjectConvertUtil.objectCopy(roleDTO, source);
            return roleDTO;
        });
    }

    @Transactional
    public void updateByVO(PermissionUpdateVO permissionUpdateVO) {

        Permission permission = this.findById(permissionUpdateVO.getId());
        ObjectConvertUtil.objectCopy(permission, permissionUpdateVO);
        this.update(permission);
    }

    public boolean exist(String permit) {
        return permissionDAO.findByPermit(permit) != null;
    }

    @Transactional
    public void saveByVO(PermissionSaveVO permissionSaveVO) {

        Permission permission = new Permission();
        ObjectConvertUtil.objectCopy(permission, permissionSaveVO);
        this.save(permission);
    }

    public List<Permission> listByRoleId(Long roleId){
        return permissionDAO.findByRoleId(roleId);
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
