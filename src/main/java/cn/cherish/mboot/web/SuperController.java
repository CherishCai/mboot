package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.vo.su.SuperRolePermissionVO;
import cn.cherish.mboot.dal.vo.su.SuperUserRoleVO;
import cn.cherish.mboot.service.SuperService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Cherish on 2017/1/6.
 */
@Controller
@RequestMapping("super")
@RequiresRoles("super")
public class SuperController extends ABaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(SuperController.class);

    @Autowired
    private SuperService superService;

    @ModelAttribute
    public void roles(Model model) {
        model.addAttribute("roles", superService.listAllRole());
    }

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("admin/super/manage");
        return mv;
    }

    @RequestMapping("/findUser")
    public ModelAndView findUser(String username) {
        ModelAndView mv = new ModelAndView("admin/super/manage");
        mv.addObject("username", username);
        mv.addObject("superUserRole", superService.findByUsername(username));
        return mv;
    }

    @RequestMapping("/findRole")
    public ModelAndView findRole(String rolename) {
        ModelAndView mv = new ModelAndView("admin/super/manage");
        mv.addObject("rolename", rolename);
        mv.addObject("superRolePermission", superService.findByRolename(rolename));
        return mv;
    }

    /**
     * 更改用户的角色
     * @param superUserRoleVO 用户ID与角色ID
     * @return ModelAndView
     */
    @PostMapping("/updateUserRole")
    public ModelAndView updateUserRole(@Validated SuperUserRoleVO superUserRoleVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/super/manage");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);


        if(superUserRoleVO == null || superUserRoleVO.getUsername() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));

        }else {
            try {
                superService.updateUserRole(superUserRoleVO);

                errorMap.put("msg", "修改成功");
            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("修改错误:{}", e.getMessage());
            }
        }

        return mv;
    }

    /**
     * 更改角色的权限
     * @param superRolePermissionVO 角色ID与权限ID
     * @return ModelAndView
     */
    @PostMapping("/updateRolePermission")
   public ModelAndView updateRolePermission(@Validated SuperRolePermissionVO superRolePermissionVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/super/manage");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if(superRolePermissionVO == null || superRolePermissionVO.getRolename() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }
        LOGGER.debug("superRolePermissionVO = " + superRolePermissionVO);

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));

        }else {
            try {
                superService.updateRolePermission(superRolePermissionVO);

                errorMap.put("msg", "修改角色权限成功");
            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("修改错误:{}", e.getMessage());
            }
        }

        return mv;
    }



}
