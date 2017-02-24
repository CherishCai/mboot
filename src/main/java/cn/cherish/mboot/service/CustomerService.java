package cn.cherish.mboot.service;

import cn.cherish.mboot.dal.dto.CustomerDTO;
import cn.cherish.mboot.dal.entity.Customer;
import cn.cherish.mboot.dal.vo.BasicSearchVO;
import cn.cherish.mboot.dal.vo.customer.CustomerSearchVO;
import cn.cherish.mboot.dal.vo.customer.CustomerVO;
import cn.cherish.mboot.extra.shiro.CryptographyUtil;
import cn.cherish.mboot.repository.CustomerDAO;
import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.util.ObjectConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
public class CustomerService extends ABaseService<Customer, Long> {

    @Autowired
    private CustomerDAO customerDAO;

    private static final String UNKNOW = "未知";
    private static final String AC = "激活";
    private static final String UN = "冻结";

    @Override
    protected IBaseDAO<Customer, Long> getEntityDAO() {
        return customerDAO;
    }

    public CustomerDTO findOne(Long CustomerId) {
        Customer customer = customerDAO.findOne(CustomerId);
        return customer == null ? null : ObjectConvertUtil.objectCopy(new CustomerDTO(), customer);
    }

    public Customer findByWeixinUserId(Long weixnuserId){
        return customerDAO.findByWeixinuserId(weixnuserId);
    }

    public boolean exist(String telephone) {
        return customerDAO.findByTelephone(telephone) != null;
    }

    @Transactional
    public void delete(Long customerId) {
        Customer customer = customerDAO.findOne(customerId);
        if (customer == null) return;

        super.delete(customerId);
    }

    public Page<CustomerDTO> findAll(BasicSearchVO basicSearchVO, CustomerSearchVO customerSearchVO) {

        int pageNumber = basicSearchVO.getStartIndex() / basicSearchVO.getPageSize() + 1;
        PageRequest pageRequest = super.buildPageRequest(pageNumber, basicSearchVO.getPageSize());

        //除了分页条件没有特定搜索条件的，为了缓存count
        if (ObjectConvertUtil.objectFieldIsBlank(customerSearchVO)){
            log.debug("没有特定搜索条件的");
            List<Customer> customerList = customerDAO.listAllPaged(pageRequest);
            List<CustomerDTO> CustomerDTOList = customerList.stream().map(source -> {
                CustomerDTO customerDTO = new CustomerDTO();
                ObjectConvertUtil.objectCopy(customerDTO, source);
                customerDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
                return customerDTO;
            }).collect(Collectors.toList());

            //为了计算总数使用缓存，加快搜索速度
            Long count = getCount();
            return new PageImpl<>(CustomerDTOList, pageRequest, count);
        }

        //有了其它搜索条件
        Page<Customer> userPage = super.findAllBySearchParams(
                buildSearchParams(customerSearchVO), pageNumber, basicSearchVO.getPageSize());

        return userPage.map(source -> {
            CustomerDTO customerDTO = new CustomerDTO();
            ObjectConvertUtil.objectCopy(customerDTO, source);
            customerDTO.setActiveStr(source.getActive() == null ? UNKNOW : source.getActive() == 1 ? AC : UN);
            return customerDTO;
        });
    }

    @Transactional
    public void updateByVO(CustomerVO customerVO) {
        Customer customer = this.findById(customerVO.getId());
        if (customer == null) return;

        ObjectConvertUtil.objectCopy(customer, customerVO);
        customer.setModifiedTime(new Date());
        this.update(customer);

    }

    @Transactional
    public Customer saveByVO(CustomerVO customerVO) {
        Customer customer = new Customer();
        ObjectConvertUtil.objectCopy(customer, customerVO);
        customer.setPassword(CryptographyUtil.cherishSha1(customer.getPassword()));
        customer.setCreatedTime(new Date());
        customer.setModifiedTime(new Date());


        return this.save(customer);
    }


}
