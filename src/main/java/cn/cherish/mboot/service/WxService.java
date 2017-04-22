package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.entity.WxUser;
import cn.cherish.mboot.dal.repository.IBaseDAO;
import cn.cherish.mboot.dal.repository.WxUserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class WxService extends ABaseService<WxUser, Long> {

    @Autowired
    private WxUserDAO wxUserDAO;

    @Override
    protected IBaseDAO<WxUser, Long> getEntityDAO() {
        return wxUserDAO;
    }

    public WxUser findByOpenid(String openid) {
        return wxUserDAO.findByOpenid(openid);
    }

    public boolean exist(String openid) {
        return wxUserDAO.findByOpenid(openid) != null;
    }


}
