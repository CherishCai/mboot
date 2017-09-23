/**
 * JDKCC.com
 * Copyright (c) 2011-2017 All Rights Reserved.
 */
package cn.cherish.mboot.orm;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Jiangjiaze
 * @version Id: ProxyClient.java, v 0.1 2017/3/17 16:33 FancyKong Exp $$
 */
@Controller
public class ProxyClient {
    @Autowired
    UserDao dao;

    private void service() {
        System.out.println("dao.userListSize() = " + dao.userListSize());
        System.out.println("dao.selectList() = " + dao.selectList());
        System.out.println("dao.updateById(\"nimahai\",1) = " + dao.updateById("nimahai", 1));
        System.out.println("dao.deleteById(2) = " + dao.deleteById(2));
        System.out.println("dao.seleteById(1) = " + dao.seleteById(1));
        System.out.println("dao.insert() = " + dao.insert("cao","CURDATE()"));
    }

    public static void main(String[] args) {
        initContext();
        ProxyClient p = (ProxyClient) ioc.get(ProxyClient.class);
        p.service();
    }

    static Map<Class, Object> ioc = new HashMap<>();

    private static void initContext() {
        initDao();
        URL url = Thread.currentThread().getContextClassLoader().getResource("cn/cherish/mboot/orm");
        File file = new File(url.getPath());
        for (File f : file.listFiles()) {
            try {
                if (f.isFile()) {
                    Class c = Class.forName("cn.cherish.mboot.orm" + "." + f.getName().replace(".class", ""));
                    if (c.getAnnotation(Controller.class) != null) {
                        ioc.put(c, c.newInstance());
                    }
                }
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                e.printStackTrace();
            }
        }

        //做 Autowired 注解 设值工作
        for (Class clazz : ioc.keySet()) {
            for (Field field : clazz.getDeclaredFields()) {
                if (field.getAnnotation(Autowired.class) != null) {
                    try {
                        field.setAccessible(true);
                        field.set(ioc.get(clazz), ioc.get(field.getType()));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private static void initDao() {
        try {
            initEntity();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        UserDao userDao = (UserDao) Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader()
                , new Class[]{UserDao.class}
                , (proxy, method, args) -> {
                    UserDao dao = (UserDao) proxy;
                    System.out.println("dao.getClass() = " + dao.getClass());
                    SQL sqlA = method.getAnnotation(SQL.class);
                    String type = sqlA.type();
                    String sql = sqlA.value();
                    if(args != null && args.length > 0)
                    for(Object object : args){
                        sql = sql.replaceFirst("\\?",object.toString());
                    }
                    Class clz = method.getReturnType();
                    Connection con = getConnection();
                    String entiString = getEntityString(sql).toUpperCase();//拿到user
                    Class entity = entites.get(entiString);
                    switch (type){
                        case "select":
                            ResultSet rs = con.prepareStatement(sql).executeQuery();
                            if (clz.equals(List.class)) {
                                List list = new ArrayList();
                                while (rs.next()) {
                                    Object entityObj = entity.newInstance();
                                    for (Field field : entity.getDeclaredFields()) {
                                        field.setAccessible(true);
                                        field.set(entityObj, rs.getObject(field.getName()));
                                    }
                                    list.add(entityObj);
                                }
                                return list;
                            } else if(int.class.equals(clz)){
                                int res = 0;
                                while (rs.next()) res = rs.getInt(1);
                                return res;
                            } else if(long.class.equals(clz)){
                                long res = 0;
                                while (rs.next()) res = rs.getLong(1);
                                return res;
                            }else{
                                Object entityObj = entity.newInstance();
                                while (rs.next()){
                                    for(Field f : entity.getDeclaredFields()){
                                        f.setAccessible(true);
                                        f.set(entityObj,rs.getObject(f.getName()));
                                    }
                                }
                                return entityObj;
                            }
                        case "update":
                        case "insert":
                        case "delete":
                            return con.prepareStatement(sql).executeUpdate();
                        default:
                            throw new IllegalArgumentException("not supported type");
                    }
                });
        //User user = userDao.seleteById(1);
        System.out.println("userDao.getClass() = " + userDao.getClass());
        ioc.put(UserDao.class, userDao);
    }

    private static String getEntityString(String sql) {
        int i = sql.indexOf("from") + 4;
        int st = 0;
        int end = sql.length();
        for (int j = i; j < sql.length(); j++) {
            if (sql.charAt(j) == ' ') {
                st = j+1;
            } else {
                for (int k = st + 1; k < sql.length(); k++) {
                    if (sql.charAt(k) == ' ') {
                        end = k;
                        break;
                    }
                }
                break;
            }
        }
        return sql.substring(st, end);
    }

    static Map<String, Class> entites = new HashMap<>();

    private static void initEntity() throws ClassNotFoundException {
        String packageName = "cn.cherish.mboot.orm";//retains
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        File packageDir = new File(url.getPath());
        for (File classFile : packageDir.listFiles()) {
            if (!classFile.isDirectory()) {
                Class clz = Class.forName(packageName + "." + classFile.getName().split("\\.")[0]);
                if (clz.getAnnotation(Entity.class) != null) {
                    System.out.println("clz.getSimpleName() = " + clz.getSimpleName());
                    entites.put(clz.getSimpleName().toUpperCase(), clz);
                }
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUser("root");
        dataSource.setPassword("caimengyuan");
        return dataSource.getConnection();
    }


}

