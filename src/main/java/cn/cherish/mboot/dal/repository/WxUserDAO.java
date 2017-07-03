package cn.cherish.mboot.dal.repository;

import cn.cherish.mboot.dal.entity.WxUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WxUserDAO extends IBaseDAO<WxUser,Long> {

    WxUser findByOpenid(String openid);

    @Query("SELECT w FROM WxUser AS w ")
    List<WxUser> listAllPaged(Pageable pageable);



}
