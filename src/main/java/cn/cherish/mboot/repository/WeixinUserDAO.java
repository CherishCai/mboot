package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.WeixinUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WeixinUserDAO extends IBaseDAO<WeixinUser,Long> {

    WeixinUser findByOpenid(String openid);

    @Query("SELECT w FROM WeixinUser AS w ")
    List<WeixinUser> listAllPaged(Pageable pageable);



}
