package cn.cherish.mboot;

import com.alibaba.druid.pool.DruidDataSource;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Cherish on 2017/2/13.
 */
public class Main {

    public static void main(String[] args)  {

        // TODO 玩一下 Unsafe
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);

            System.out.println("UNSAFE = " + UNSAFE);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mboot");
        dataSource.setUsername("root");
        dataSource.setPassword("caimengyuan");

        for (int i = 0; i < 50; i++) {
            final int j = i;

            new Thread(()->{
                try {
                    Connection connection = dataSource.getConnection();
                    System.out.println("i = " + j);
                    System.out.println("connection = " + connection.getClass());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
        }



    }



}

