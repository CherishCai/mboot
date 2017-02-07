package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.dto.RoleDTO;
import cn.cherish.mboot.dal.entity.Role;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.role.RoleSaveVO;
import cn.cherish.mboot.dal.vo.role.RoleUpdateVO;
import cn.cherish.mboot.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
@RequestMapping("role")
@RequiresAuthentication
public class RoleController extends ABaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("admin/role/list");
        return mv;
    }

    /**
     * 返回新增页面
     */
    @GetMapping("/add")
    public ModelAndView addForm(){
        ModelAndView mv = new ModelAndView("admin/role/add");
        return mv;
    }

    /**
     * 返回修改信息页面
     */
    @GetMapping("/{roleId}")
    public ModelAndView updateForm(@PathVariable("roleId") Long roleId){
        ModelAndView mv = new ModelAndView("admin/role/edit");
        Role role = roleService.findById(roleId);
        mv.addObject(role);
        return mv;
    }

    /**
     * 分页查询
     * @param basicSearchVO 基本搜索条件
     * @return JSON
     * @date 2016年8月30日 下午5:30:18
     */
    @GetMapping("/page")
    @ResponseBody
    public Map toPage(BasicSearchVO basicSearchVO){

        try {
            Page<RoleDTO> page = roleService.findAll(basicSearchVO);

            return getReturnMap(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取列表失败:", e.getMessage());
            return getReturnMap(Boolean.FALSE, BUSY_MSG, null);
        }
    }

    /**
     * 删除
     * @param roleId ID
     * @return JSON
     */
    @DeleteMapping("/{roleId}")
    @ResponseBody
    public Map delrole(@PathVariable("roleId") Long roleId){

        try {
            roleService.delete(roleId);
            return getReturnMap(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败:{}", e.getMessage());
            return getReturnMap(Boolean.FALSE, "删除失败", null);
        }
    }

    /**
     * 更改信息
     * @param roleUpdateVO 更新信息
     * @return ModelAndView
     */
    @PostMapping("/update")
    public ModelAndView updaterole(@Validated RoleUpdateVO roleUpdateVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/role/edit");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if(roleUpdateVO == null || roleUpdateVO.getId() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            mv.addObject("role", roleUpdateVO);

        }else {
            try {
                roleService.updateByVO(roleUpdateVO);

                mv.addObject("role", roleService.findById(roleUpdateVO.getId()));
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
     * 保存新用户
     * @param roleSaveVO 保存的信息
     * @return ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView saverole(@Validated RoleSaveVO roleSaveVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/role/add");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            mv.addObject("role", roleSaveVO);

        }else {
            try {
                if (roleService.exist(roleSaveVO.getName())){
                    errorMap.put("msg", "该角色名已存在，请更换再试");
                    mv.addObject("role", roleSaveVO);
                }else {
                    roleService.saveByVO(roleSaveVO);
                    errorMap.put("msg", "添加成功");
                }

            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("添加失败:{}", e.getMessage());
            }
        }

        return mv;
    }


}
