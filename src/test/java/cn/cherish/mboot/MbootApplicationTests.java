package cn.cherish.mboot;

import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.common.shiro.MShiroRealm;
import cn.cherish.mboot.service.PermissionService;
import cn.cherish.mboot.service.RoleService;
import cn.cherish.mboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MbootApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private MShiroRealm mShiroRealm;

    @Test
	public void userCacheTest() {
        User cherish = userService.findByUsername("cherish");
        System.out.println("cherish = " + cherish);

        cherish = userService.findByUsername("cherish");
        System.out.println("cherish = " + cherish);

        cherish = userService.findByUsername("cherish");
        System.out.println("cherish = " + cherish);

        Collection<String> cacheNames = cacheManager.getCacheNames();
        for (String cacheName : cacheNames){
            Cache cache1 = cacheManager.getCache(cacheName);
            System.out.println("cacheName = " + cacheName);
            User user = cache1.get("username_cherish", User.class);
            System.out.println("user = " + user);
        }
    }

}
