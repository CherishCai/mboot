package cn.cherish.mboot;

import cn.cherish.mboot.repository.CustomizedDAO;
import cn.cherish.mboot.service.PermissionService;
import cn.cherish.mboot.service.RoleService;
import cn.cherish.mboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MbootApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private CustomizedDAO customizedDAO;

	@Test
	public void contextLoads() {

       /* List<Permission> permissionList = new ArrayList<>();
        permissionList.add(new Permission(1L, "", "", null));
        permissionList.add(new Permission(2L, "", "", null));

        User user = new User(1L, "cherish", "cherish",
                "Cherish", "18826137274", new Date(), new Date(), (short)1, "就是我", null);
        List<User> users = new ArrayList<>();
        users.add(user);

        Role role = new Role(2L, "test", "尝试",users,permissionList);

        roleService.save(role);
*/
        /*List<Role> roles = new ArrayList<>();
        roles.add(role1);
        roles.add(new Role(1L,"","", null,null));

        User user = new User(2L, "222", "222", "222", "18826137274", new Date(), new Date(), (short)1, "就是我", roles);

        userService.save(user);*/


        customizedDAO.freezeUser(2L);

    }

}
