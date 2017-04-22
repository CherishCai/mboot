package cn.cherish.mboot.web.controller;

import cn.cherish.mboot.web.MResponse;
import cn.cherish.mboot.web.dto.UserDTO;
import cn.cherish.mboot.dal.entity.User;
import cn.cherish.mboot.web.request.BasicSearchReq;
import cn.cherish.mboot.web.request.user.UserModifyPasswordReq;
import cn.cherish.mboot.web.request.user.UserSaveReq;
import cn.cherish.mboot.web.request.user.UserSearchReq;
import cn.cherish.mboot.web.request.user.UserUpdateReq;
import cn.cherish.mboot.common.shiro.CryptographyUtil;
import cn.cherish.mboot.common.shiro.ShiroUserUtil;
import cn.cherish.mboot.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Cherish on 2017/1/6.
 */
@Controller
@RequestMapping("user")
@RequiresAuthentication
public class UserController extends ABaseController {

    @Autowired
    private UserService userService;

    @GetMapping
    @RequiresPermissions("user:show")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("admin/user/list");
        return mv;
    }

    /**
     * 返回新增用户的页面
     */
    @GetMapping("/add")
    @RequiresPermissions("user:add")
    public ModelAndView addForm(){
        ModelAndView mv = new ModelAndView("admin/user/add");
        return mv;
    }

    /**
     * 返回修改用户信息的页面
     */
    @GetMapping("/{userId}/update")
    @RequiresPermissions("user:update")
    public ModelAndView updateForm(@PathVariable("userId") Long userId){
        ModelAndView mv = new ModelAndView("admin/user/edit");
        User user = userService.findById(userId);
        mv.addObject(user);
        return mv;
    }

    /**
     * 用户查看个人信息的页面
     */
    @GetMapping("/profile")
    public ModelAndView profile(){
        ModelAndView mv = new ModelAndView("admin/user/profile");
        return mv;
    }

    /**
     * 用户修改密码的页面
     */
    @GetMapping("/modifyPassword")
    public ModelAndView modifyPassword(){
        ModelAndView mv = new ModelAndView("admin/user/modifyPassword");
        return mv;
    }

    /**
     * 分页查询用户
     * @param basicSearchVO 基本搜索条件
     * @return JSON
     * @date 2016年8月30日 下午5:30:18
     */
    @GetMapping("/page")
    @ResponseBody
    public MResponse toPage(BasicSearchReq basicSearchVO, UserSearchReq userSearchVO){

        try {
            Page<UserDTO> page = userService.findAll(userSearchVO, basicSearchVO);

            return buildResponse(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取用户列表失败: {}", e.getMessage());
            return buildResponse(Boolean.FALSE, BUSY_MSG, null);
        }
    }

    /**
     * 删除用户
     * @param userId 用户ID
     * @return JSON
     */
    @DeleteMapping("/{userId}/delete")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public MResponse delete(@PathVariable("userId") Long userId){
        try {
            userService.delete(userId);

            return buildResponse(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败:{}", e.getMessage());
            return buildResponse(Boolean.FALSE, "删除失败", null);
        }
    }

    /**
     * 更改用户信息
     * @param userUpdateVO 更新信息
     * @return ModelAndView
     */
    @PostMapping("/update")
    @RequiresPermissions("user:update")
    public ModelAndView update(@Validated UserUpdateReq userUpdateVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/user/edit");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if(userUpdateVO == null || userUpdateVO.getId() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("user", userUpdateVO);

        }else {
            try {
                userService.update(userUpdateVO);

                mv.addObject("user", userService.findById(userUpdateVO.getId()));
                errorMap.put("msg", "修改成功");
            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("修改用户错误:{}", e.getMessage());
            }
        }

        return mv;
    }

    /**
     * 保存新用户
     * @param userSaveVO 保存的信息
     * @return ModelAndView
     */
    @PostMapping("/save")
    @RequiresPermissions("user:add")
    public ModelAndView save(@Validated UserSaveReq userSaveVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/user/add");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("user", userSaveVO);

        }else {
            try {
                if (userService.exist(userSaveVO.getUsername())){
                    errorMap.put("msg", "该用户名已存在，请更换再试");
                    mv.addObject("user", userSaveVO);
                }else {
                    userService.save(userSaveVO);
                    errorMap.put("msg", "添加成功");
                }

            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("添加用户失败:{}", e.getMessage());
            }
        }

        return mv;
    }



    /**
     * 提交密码修改请求
     * @param modifyPasswordVO 新旧密码
     * @param bindingResult 表单验证
     * @return ModelAndView
     */
    @PostMapping("/modifyPassword")
    public ModelAndView modifyPassword(@Validated UserModifyPasswordReq modifyPasswordVO, BindingResult bindingResult) {

        ModelAndView mv = new ModelAndView("admin/user/modifyPassword");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        //表单验证是否通过
        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));

        }else {
            if (StringUtils.isBlank(modifyPasswordVO.getPassword())
                    || StringUtils.isBlank(modifyPasswordVO.getRepeatPassword())
                    || !modifyPasswordVO.getPassword().equals(modifyPasswordVO.getRepeatPassword())){

                errorMap.put("msg", "两次输入的密码不一致");
                return mv;
            }

            try {
                User user = userService.findByUsername(ShiroUserUtil.getUsername());

                if (!user.getPassword().equals(CryptographyUtil.cherishSha1(modifyPasswordVO.getOldPassword()))) {
                    errorMap.put("msg", "密码认证错误");
                }else {
                    user.setPassword(CryptographyUtil.cherishSha1(modifyPasswordVO.getPassword()));
                    userService.update(user);

                    errorMap.put("msg" ,"更改成功");
                }

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("修改密码失败:{}", e.getMessage());
                errorMap.put("msg", BUSY_MSG);
            }
        }

        return mv;
    }


}
