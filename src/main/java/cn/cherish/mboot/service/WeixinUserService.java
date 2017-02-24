package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.entity.WeixinUser;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.repository.WeixinUserDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class WeixinUserService extends ABaseService<WeixinUser, Long> {

    @Autowired
    private WeixinUserDAO weixinUserDAO;

    @Override
    protected IBaseDAO<WeixinUser, Long> getEntityDAO() {
        return weixinUserDAO;
    }

    public WeixinUser findByOpenid(String openid) {
        return weixinUserDAO.findByOpenid(openid);
    }

    public boolean exist(String openid) {
        return weixinUserDAO.findByOpenid(openid) != null;
    }


}
