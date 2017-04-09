package cn.cherish.mboot.web;

import cn.cherish.mboot.dal.MResponse;
import cn.cherish.mboot.dal.dto.PermissionDTO;
import cn.cherish.mboot.dal.entity.Permission;
import cn.cherish.mboot.dal.request.BasicSearchReq;
import cn.cherish.mboot.dal.request.permission.PermissionSaveReq;
import cn.cherish.mboot.dal.request.permission.PermissionUpdateReq;
import cn.cherish.mboot.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("permission")
@RequiresRoles("super")
public class PermissionController extends ABaseController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("admin/permission/list");
        return mv;
    }

    /**
     * 返回新增页面
     */
    @GetMapping("/add")
    public ModelAndView addForm(){
        ModelAndView mv = new ModelAndView("admin/permission/add");
        return mv;
    }

    /**
     * 返回修改信息页面
     */
    @GetMapping("/{permissionId}/update")
    public ModelAndView updateForm(@PathVariable("permissionId") Long permissionId){
        ModelAndView mv = new ModelAndView("admin/permission/edit");
        Permission permission = permissionService.findById(permissionId);
        mv.addObject(permission);
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
    public MResponse toPage(BasicSearchReq basicSearchVO){

        try {
            Page<PermissionDTO> page = permissionService.findAll(basicSearchVO);

            return buildResponse(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取列表失败: {}", e.getMessage());
            return buildResponse(Boolean.FALSE, BUSY_MSG, null);
        }
    }

    /**
     * 删除
     * @param permissionId ID
     * @return JSON
     */
    @DeleteMapping("/{permissionId}/delete")
    @ResponseBody
    public MResponse delpermission(@PathVariable("permissionId") Long permissionId){

        try {
            permissionService.delete(permissionId);
            return buildResponse(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败:{}", e.getMessage());
            return buildResponse(Boolean.FALSE, "删除失败", null);
        }
    }

    /**
     * 更改信息
     * @param permissionUpdateVO 更新信息
     * @return ModelAndView
     */
    @PostMapping("/update")
    public ModelAndView updatepermission(@Validated PermissionUpdateReq permissionUpdateVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/permission/edit");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if(permissionUpdateVO == null || permissionUpdateVO.getId() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("permission", permissionUpdateVO);

        }else {
            try {
                permissionService.updateByVO(permissionUpdateVO);

                mv.addObject("permission", permissionService.findById(permissionUpdateVO.getId()));
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
     * @param permissionSaveVO 保存的信息
     * @return ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView savepermission(@Validated PermissionSaveReq permissionSaveVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/permission/add");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("permission", permissionSaveVO);

        }else {
            try {
                if (permissionService.exist(permissionSaveVO.getPermit())){
                    errorMap.put("msg", "该角色名已存在，请更换再试");
                    mv.addObject("permission", permissionSaveVO);
                }else {
                    permissionService.saveByVO(permissionSaveVO);
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
