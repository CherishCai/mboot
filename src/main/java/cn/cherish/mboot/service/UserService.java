package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.dto.UserDTO;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.user.UserSaveVO;
import cn.cherish.mboot.dal.vo.user.UserSearchVO;
import cn.cherish.mboot.dal.vo.user.UserUpdateVO;
import cn.cherish.mboot.extra.shiro.CryptographyUtil;
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
@CacheConfig(cacheNames = "users")
@Transactional(readOnly = true)
public class UserService extends ABaseService<User, Long> {

    @Autowired
    private UserDAO userDAO;

    private static final String UNKNOW = "未知";
    private static final String AC = "激活/在职";
    private static final String UN = "冻结/离职";

    @Override
    protected IBaseDAO<User, Long> getEntityDAO() {
        return userDAO;
    }

    @Cacheable(key = "'username_' + #username", unless = "#result==null")
    public User findByUsername(String username) {
        log.debug("username_{}没有缓存", username);
        return userDAO.findByUsername(username);
    }

    public boolean exist(String username) {
        return userDAO.findByUsername(username) != null;
    }

    @Cacheable(key = "'countAllUser'")
    public Long getCount() {
        log.debug("countAllUser没有缓存");
        return userDAO.count();
    }

    @Transactional(readOnly = false)
    @CacheEvict(allEntries = true)
    public void delete(Long id) {
        // 并不是真正的删除，只是冻结账户
        User user = findById(id);
        user.setActive(0);
        update(user);
    }

    @Transactional
    @CacheEvict(allEntries = true)
    public void updateByVO(UserUpdateVO userUpdateVO) {
        User user = findById(userUpdateVO.getId());
        ObjectConvertUtil.objectCopy(user, userUpdateVO);
        user.setModifiedTime(new Date());
        update(user);
    }

    @Transactional
    public void saveByVO(UserSaveVO userSaveVO) {

        if (exist(userSaveVO.getUsername())) {
            return;
        }

        User user = new User();
        ObjectConvertUtil.objectCopy(user, userSaveVO);
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());
        user.setPassword(CryptographyUtil.cherishSha1(user.getPassword()));
        save(user);
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
                userDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
                return userDTO;
            }).collect(Collectors.toList());

            //为了计算总数使用缓存，加快搜索速度
            Long count = getCount();
            return new PageImpl<>(userDTOList, pageRequest, count);
        }

        //有了其它搜索条件
        Page<User> userPage = super.findAllBySearchParams(
                buildSearchParams(userSearchVO), pageNumber, basicSearchVO.getPageSize());

        return userPage.map(source -> {
            UserDTO userDTO = new UserDTO();
            ObjectConvertUtil.objectCopy(userDTO, source);
            userDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
            return userDTO;
        });

    }

}
