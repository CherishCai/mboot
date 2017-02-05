package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.dto.UserDTO;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.ModifyPasswordVO;
import cn.cherish.mboot.dal.vo.UserVO;
import cn.cherish.mboot.extra.shiro.CryptographyUtil;
import cn.cherish.mboot.extra.shiro.ShiroUserUtil;
import cn.cherish.mboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cherish on 2017/1/6.
 */
@Controller
@RequestMapping("user")
@RequiresAuthentication
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
            Page<UserDTO> page = userService.findAll(userVO, basicSearchVO);

            LOGGER.debug("用户总数：{}",page.getTotalElements());

            return getReturnMap(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户列表失败:", e.getMessage());
            return getReturnMap(Boolean.FALSE, BUSY_MSG, null);
        }
    }

    @DeleteMapping("/{userId}")
    @ResponseBody
    public Map delUser(@PathVariable("userId") Long userId){

        try {
            userService.delete(userId);

            return getReturnMap(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败:{}", e.getMessage());
            return getReturnMap(Boolean.FALSE, "删除失败", null);
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public Map updateUser(@RequestBody UserVO userVO){

        if(userVO == null || userVO.getId() == null){
            return getReturnMap(Boolean.FALSE, "数据错误", null);
        }

        try {
            User user = userService.findById(userVO.getId());
            //TODO


            userService.update(user);

            return getReturnMap(Boolean.TRUE, "修改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改用户错误:{}", e.getMessage());
        }
        return getReturnMap(Boolean.FALSE, "修改失败", null);
    }

    @PostMapping("/save")
    @ResponseBody
    public Map saveUser(@RequestBody UserVO userVO){

        if(userVO == null || userVO.getUsername() == null){
            return getReturnMap(Boolean.FALSE, "数据错误！", null);
        }

        try {
            User user = new User();
            //TODO

            userService.save(user);

            return getReturnMap(Boolean.TRUE, "添加成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加用户失败:{}", e.getMessage());
            return getReturnMap(Boolean.FALSE, "添加失败", null);
        }
    }

    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView("/admin/user/profile");
        return mv;
    }

    @GetMapping("/modifyPassword")
    public ModelAndView modifyPassword(){
        ModelAndView mv = new ModelAndView("/admin/user/modifyPassword");
        return mv;
    }

    @PostMapping("/modifyPassword")
    @ResponseBody
    public Map modifyPassword(@Validated ModifyPasswordVO modifyPasswordVO, BindingResult bindingResult) {

        //表单验证是否通过
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            Map<String, Object> errorMap = new HashMap<>();
            for (FieldError error : list) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            System.out.println("errorMap = " + errorMap);
        }

        if (StringUtils.isBlank(modifyPasswordVO.getPassword())
                || StringUtils.isBlank(modifyPasswordVO.getConfirmPassword())
                || !modifyPasswordVO.getPassword().equals(modifyPasswordVO.getConfirmPassword()))
            return getReturnMap(Boolean.FALSE, "两次输入的密码不一致", null);

        try {
            User user = userService.findByUsername(ShiroUserUtil.getUsername());

            if (!user.getPassword().equals(CryptographyUtil.cherishSha1(modifyPasswordVO.getOldPassword()))) {
                return getReturnMap(Boolean.FALSE, "密码认证错误", null);
            }
            user.setPassword(CryptographyUtil.cherishSha1(modifyPasswordVO.getPassword()));
            userService.update(user);

            return getReturnMap(Boolean.TRUE, "更改成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("修改密码失败:{}", e.getMessage());
            return getReturnMap(Boolean.FALSE, BUSY_MSG, null);
        }
    }


}
