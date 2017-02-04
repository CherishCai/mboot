package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.dto.UserDTO;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.UserVO;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = "users")
public class UserService extends ABaseService<User, Long> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomizedDAO customizedDAO;

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
        customizedDAO.freezeUser(userId);
    }


    public Page<UserDTO> findAllByVO(UserVO userVO, BasicSearchVO basicSearchVO) {

        int pageNumber = basicSearchVO.getStartIndex() / basicSearchVO.getPageSize() + 1;

        Page<User> userPage = super.findAllBySearchParams(
                buildSearchParams(userVO), pageNumber, basicSearchVO.getPageSize());

        return userPage.map(source -> {
            UserDTO userDTO = new UserDTO();
            ObjectConvertUtil.objectCopy(userDTO, source);
            return userDTO;
        });

//        PageImpl
//        PageRequest
    }
}
