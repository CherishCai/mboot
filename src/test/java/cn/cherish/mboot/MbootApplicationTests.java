package cn.cherish.mboot;

import cn.cherish.mboot.service.PermissionService;
import cn.cherish.mboot.service.RoleService;
import cn.cherish.mboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

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

    @Test
	public void contextLoads() {

        System.out.println("userService.getClass() = " + userService.getClass());
        System.out.println("roleService.getClass() = " + roleService.getClass());
        System.out.println("permissionService.getClass() = " + permissionService.getClass());

    }

}
