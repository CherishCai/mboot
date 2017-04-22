package cn.cherish.mboot.web.controller;

import cn.cherish.mboot.web.MResponse;
import cn.cherish.mboot.web.dto.CustomerDTO;
import cn.cherish.mboot.dal.entity.Customer;
import cn.cherish.mboot.web.request.BasicSearchReq;
import cn.cherish.mboot.web.request.customer.CustomerSearchReq;
import cn.cherish.mboot.web.request.customer.CustomerReq;
import cn.cherish.mboot.service.CustomerService;
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
@RequestMapping("customer")
@RequiresRoles("admin")
public class CustomerController extends ABaseController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("admin/customer/list");
        return mv;
    }

    /**
     * 返回新增页面
     */
    @GetMapping("/add")
    public ModelAndView addForm(){
        ModelAndView mv = new ModelAndView("admin/customer/add");
        return mv;
    }

    /**
     * 返回修改信息页面
     */
    @GetMapping("/{customerId}/update")
    public ModelAndView updateForm(@PathVariable("customerId") Long customerId){
        ModelAndView mv = new ModelAndView("admin/customer/edit");
        Customer customer = customerService.findById(customerId);
        mv.addObject(customer);
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
    public MResponse toPage(BasicSearchReq basicSearchVO, CustomerSearchReq customerSearchVO){

        try {
            Page<CustomerDTO> page = customerService.findAll(basicSearchVO, customerSearchVO);

            return buildResponse(Boolean.TRUE, basicSearchVO.getDraw(), page);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("获取列表失败: {}", e.getMessage());
            return buildResponse(Boolean.FALSE, BUSY_MSG, null);
        }
    }

    /**
     * 删除
     * @param customerId ID
     * @return JSON
     */
    @DeleteMapping("/{customerId}/delete")
    @ResponseBody
    public MResponse delete(@PathVariable("customerId") Long customerId){

        try {
            customerService.delete(customerId);
            return buildResponse(Boolean.TRUE, "删除成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败:{}", e.getMessage());
            return buildResponse(Boolean.FALSE, "删除失败", null);
        }
    }

    /**
     * 更改信息
     * @param customerVO 更新信息
     * @return ModelAndView
     */
    @PostMapping("/update")
    public ModelAndView update(@Validated CustomerReq customerVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/customer/edit");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if(customerVO == null || customerVO.getId() == null){
            errorMap.put("msg", "数据错误");
            return mv;
        }

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("customer", customerVO);

        }else {
            try {
                customerService.update(customerVO);

                mv.addObject("customer", customerService.findById(customerVO.getId()));
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
     * @param customerVO 保存的信息
     * @return ModelAndView
     */
    @PostMapping("/save")
    public ModelAndView save(@Validated CustomerReq customerVO, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("admin/customer/add");
        Map<String, Object> errorMap = new HashMap<>();
        mv.addObject("errorMap", errorMap);

        if (bindingResult.hasErrors()) {
            errorMap.putAll(getErrors(bindingResult));
            mv.addObject("customer", customerVO);

        }else {
            try {
                customerService.save(customerVO);
                errorMap.put("msg", "添加成功");

            } catch (Exception e) {
                e.printStackTrace();
                errorMap.put("msg", "系统繁忙");
                LOGGER.error("添加失败:{}", e.getMessage());
            }
        }

        return mv;
    }

    /**
     * 提交密码修改请求
     * @return ModelAndView
     */
    @PostMapping("/modifyPassword")
    public ModelAndView modifyPassword() {

        return null;
    }


}
