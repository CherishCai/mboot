/**
 * JDKCC.com.
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package cn.cherish.mboot.orm;

import java.util.List;

/**
 * @author Jiangjiaze
 * @version Id: UserDao.java, v 0.1 2017/3/17 16:36 FancyKong Exp $$
 */
public interface UserDao {
    @SQL(value = "select * from user where id = ?")
    User seleteById(long id);

    @SQL("select * from   user ")
    List<User> selectList();

    @SQL("select count(*) from  user  ")
    int userListSize();

    @SQL(value = "delete from user where id = ?",type = "delete")
    int deleteById(long id);

    @SQL(value = "update user set name = '?' where id = ?",type = "update")
    int updateById(String name , long id);

    @SQL(value = "insert into user (name,create_time) values('?',?)",type = "insert")
    int insert(String name,String cd);
}
