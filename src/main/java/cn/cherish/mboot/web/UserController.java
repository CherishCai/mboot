package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.dto.UserDTO;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.UserVO;
import cn.cherish.mboot.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by Cherish on 2017/1/6.
 */
@Controller
@RequestMapping("user")
public class UserController extends ABaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresRoles("admin")
    @RequiresPermissions("user:del")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/admin/user/list");
        return mv;
    }

    /**
     * 分页查询用户
     * @param basicSearchVO 基本搜索条件
     * @return Map
     * @date 2016年8月30日 下午5:30:18
     */
    @GetMapping("/page")
    @ResponseBody
    public Map toPage(BasicSearchVO basicSearchVO, UserVO userVO){

        try {
            Page<UserDTO> page = userService.findAllByVO(userVO, basicSearchVO);

            LOGGER.debug("用户总数：{}",page.getTotalElements());

            return getReturnMap(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户列表失败" + e.getMessage());
        }
        return getReturnMap(Boolean.FALSE, "系统繁忙", null);
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public Map delUser(@PathVariable("userId") Long userId){

        try {
            userService.delete(userId);

            return getReturnMap(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败" + e.getMessage());
        }
        return getReturnMap(Boolean.FALSE, "删除失败", null);
    }

//    @RequiresPermissions("userManage")
  /*  @PostMapping("/edit")
    @ResponseBody
    public Map editUser(@RequestBody UserDto userDto){

        if(userDto == null || userDto.getId() == null){
            return getReturnMap(Boolean.FALSE, "数据错误！", null);
        }

        try {
            userService.update(userDto);

            return getReturnMap(Boolean.TRUE, "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改用户错误" + e.getMessage());
        }
        return getReturnMap(Boolean.FALSE, "修改失败", null);
    }

    @PostMapping("/save")
    @ResponseBody
    public Map saveUser(@RequestBody UserDto userDto){

        if(userDto == null || userDto.getUsername() == null){
            return getReturnMap(Boolean.FALSE, "数据错误！", null);
        }

        try {
            String backStr = userService.save(userDto);

            return getReturnMap(Boolean.TRUE, backStr, null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加本企业用户失败" + e.getMessage());
            return getReturnMap(Boolean.FALSE, "添加失败", null);
        }
    }*/


    /*@RequestMapping("/modifyPassword")
    @ResponseBody
    public Map modifyPassword(String oldPassword, String password,
                              String confirmPassword) {
        Date date = new Date();
        if (StringUtils.isBlank(password)
                || StringUtils.isBlank(confirmPassword)
                || !password.equals(confirmPassword))
            return getReturnMap(SUCCESS_CODE, "两次输入的密码不一致", null);

        try {
            Long id = SessionUser.getUserId();
            Admin admin = adminService.getById(id);
            if (!admin.getPassword()
                    .equals(CryptographyUtil.cherishSha1(oldPassword))) {
                return getReturnMap(ERROR_CODE, "密码认证错误", null);
            }
            admin.setPassword(CryptographyUtil.cherishSha1(password));
            admin.setLastUpdateTime(date);
            adminService.save(admin);
            return getReturnMap(SUCCESS_CODE, "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return getReturnMap(ERROR_CODE, "网络错误", null);
        }
    }*/


}
