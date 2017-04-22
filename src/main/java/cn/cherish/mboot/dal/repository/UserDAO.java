package cn.cherish.mboot.dal.repository;

import cn.cherish.mboot.dal.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Cherish on 2017/1/4.
 */
public interface UserDAO extends IBaseDAO<User,Long> {

    User findByUsername(String username);

    @Query("SELECT u FROM User AS u ")
    List<User> listAllPaged(Pageable pageable);



}
