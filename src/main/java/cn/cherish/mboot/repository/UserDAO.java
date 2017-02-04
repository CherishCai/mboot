package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.User;

/**
 * Created by Cherish on 2017/1/4.
 */
public interface UserDAO extends IBaseDAO<User,Long> {

    User findByUsername(String username);



}
