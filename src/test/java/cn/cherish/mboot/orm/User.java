/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package cn.cherish.mboot.orm;

import java.util.Date;

/**
 * @author Jiangjiaze
 * @version Id: User.java, v 0.1 2017/3/17 16:38 FancyKong Exp $$
 */
@Entity
public class User {
    private long id;
    private String name;
    private Date create_time;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
