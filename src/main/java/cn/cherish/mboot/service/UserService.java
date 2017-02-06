package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.dto.UserDTO;
import cn.cherish.mboot.dal.entity.Role;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.UserSaveVO;
import cn.cherish.mboot.dal.vo.UserSearchVO;
import cn.cherish.mboot.dal.vo.UserUpdateVO;
import cn.cherish.mboot.extra.shiro.CryptographyUtil;
import cn.cherish.mboot.repository.CustomizedDAO;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.repository.UserDAO;
import cn.cherish.mboot.util.ObjectConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "users")
public class UserService extends ABaseService<User, Long> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomizedDAO customizedDAO;
    private static final String UNKNOW = "未知";
    private static final String AC = "激活/在职";
    private static final String UN = "冻结/离职";

    @Override
    protected IBaseDAO<User, Long> getEntityDAO() {
        return userDAO;
    }

    @Cacheable(key="'username_' + #username", unless = "#result==null")
    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Transactional(readOnly = false)
    @CacheEvict(allEntries = true)
    public void delete(Long userId) {
        // TODO 并不是真正的删除，只是改变数据库里某个状态
        //customizedDAO.freezeUser(userId);
        User user = this.findById(userId);
        user.setActive(0);
        this.update(user);
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void updateByVO(UserUpdateVO userUpdateVO) {
        User user = this.findById(userUpdateVO.getId());
        ObjectConvertUtil.objectCopy(user, userUpdateVO);
        user.setModifiedTime(new Date());
        this.update(user);
    }

    @Transactional
    public void saveByVO(UserSaveVO userSaveVO) {
        User user = new User();
        ObjectConvertUtil.objectCopy(user, userSaveVO);
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        user.setPassword(CryptographyUtil.cherishSha1(user.getPassword()));
        this.save(user);
    }

    public Page<UserDTO> findAll(UserSearchVO userSearchVO, BasicSearchVO basicSearchVO) {

        int pageNumber = basicSearchVO.getStartIndex() / basicSearchVO.getPageSize() + 1;
        PageRequest pageRequest = super.buildPageRequest(pageNumber, basicSearchVO.getPageSize());

        //除了分页条件没有特定搜索条件的，为了缓存count
        if (ObjectConvertUtil.objectFieldIsBlank(userSearchVO)){
            log.debug("没有特定搜索条件的");
            List<User> userList = userDAO.listAllPaged(pageRequest);
            List<UserDTO> userDTOList = userList.stream().map(source -> {
                UserDTO userDTO = new UserDTO();
                ObjectConvertUtil.objectCopy(userDTO, source);
                String roleStr = "";
                for (Role r : source.getRoles()) {
                    roleStr += r.getName();
                }
                userDTO.setRoleStr(roleStr);
                userDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
                return userDTO;
            }).collect(Collectors.toList());

            //为了计算总数使用缓存，加快搜索速度
            Long count = this.getCount();
            return new PageImpl<>(userDTOList, pageRequest, count);
        }

        //有了其它搜索条件
        Page<User> userPage = super.findAllBySearchParams(
                buildSearchParams(userSearchVO), pageNumber, basicSearchVO.getPageSize());

        return userPage.map(source -> {
            UserDTO userDTO = new UserDTO();
            ObjectConvertUtil.objectCopy(userDTO, source);
            String roleStr = "";
            for (Role r : source.getRoles()) {
                roleStr += r.getName();
            }
            userDTO.setRoleStr(roleStr);
            userDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
            return userDTO;
        });

    }

    public boolean exist(String username){
        return userDAO.findByUsername(username) != null;
    }

    @Cacheable(key = "'countAllUser'")
    public Long getCount() {
        return userDAO.count();
    }


}
