package cn.cherish.mboot.repository;

import cn.cherish.mboot.dal.entity.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDAO extends IBaseDAO<Customer,Long> {

    Customer findByTelephone(String telephone);

    Customer findByWeixinuserId(Long weixinuserId);

    @Query("SELECT c FROM Customer AS c ")
    List<Customer> listAllPaged(Pageable pageable);



}
