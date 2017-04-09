package cn.cherish.mboot.service;

import cn.cherish.mboot.repository.IBaseDAO;
import cn.cherish.mboot.util.DynamicSpecifications;
import cn.cherish.mboot.util.ObjectConvertUtil;
import cn.cherish.mboot.util.Reflections;
import cn.cherish.mboot.util.SearchFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ABaseService
 * 基础Service 封装常用 CURD 及分页 查询
 * 子类继承后可以省略代码
 * @author Cherish
 */
@Component
public abstract class ABaseService<E, PK extends Serializable> {

    protected Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 数据库实体类的类类型
     * eg. Class<User>
     */
    protected Class<E> clazz;

    protected abstract IBaseDAO<E, PK> getEntityDAO();

    /**
     * 用于Service层子类使用的构造函数.
     * 通过子类的泛型定义取得对象类型Class.
     * eg. public class UserService extends ABaseService<User, Long>
     */
    public ABaseService() {
        this.clazz = Reflections.getClassGenericType(getClass());
    }

    @Transactional(readOnly = false)
    public E save(E entity) {
        return getEntityDAO().save(entity);
    }

    @Transactional(readOnly = false)
    public void delete(PK id) {
        getEntityDAO().delete(id);
    }

    @Transactional(readOnly = false)
    public void update(E entity) {
        getEntityDAO().save(entity);
    }

    public E findById(PK id) {
        return getEntityDAO().findOne(id);
    }

    public E findByCondition(E entityCondition) {
        return getEntityDAO().findOne(buildSpecification(entityCondition));
    }

    /**
     * 根据搜索条件查询List
     * @param entityCondition 搜索条件对象
     * @return List<E>
     */
    public List<E> listByCondition(E entityCondition) {
        return getEntityDAO().findAll(buildSpecification(entityCondition));
    }

    /**
     * 根据搜索条件查询List
     * @param entityCondition 搜索条件对象
     * @param orderField 排序的属性
     * @param isDesc 是否倒序
     * @return List<E>
     */
    public List<E> listByCondition(E entityCondition, String orderField, boolean isDesc) {
        Sort sort = null;
        if (isDesc){
            sort = new Sort(Direction.DESC, orderField.trim());
        } else{
            sort = new Sort(Direction.ASC, orderField.trim());
        }
        return getEntityDAO().findAll(buildSpecification(entityCondition), sort);
    }

    /**
     * 根据搜索条件查询List
     * @param propertyName 搜索条件属性名
     * @param propertyValue 搜索条件属性值
     * @return List<E>
     */
    public List<E> listByCondition(String propertyName, Object propertyValue) {
        return getEntityDAO().findAll(buildSpecification(propertyName, propertyValue));
    }

    public Long getCount() {
        return getEntityDAO().count();
    }

    /**
     * 一定条件下的计数
     * @param entityCondition 条件对象
     * @return Long
     */
    public Long getCount(E entityCondition) {
        return getEntityDAO().count(buildSpecification(entityCondition));
    }

    /**
     * 一定条件下的计数
     * @param searchParams 计数条件
     * @return Long
     */
    public Long getCount(Map<String, Object> searchParams) {
        return getEntityDAO().count(buildSpecification(searchParams));
    }

    public boolean isUnique(E entityCondition) {
        return getEntityDAO().count(buildSpecification(entityCondition)) == 0;
    }

    public List<E> findAll() {
        return (List<E>) getEntityDAO().findAll();
    }

    /**
     * 分页查询
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return Page<E>
     */
    public Page<E> findAll(int pageNumber, int pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        return getEntityDAO().findAll(pageRequest);
    }

    /**
     * 带参数的分页查询
     * @param entityCondition 搜索条件
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return Page<E>
     */
    public Page<E> findAllByEntityCondition(E entityCondition, int pageNumber, int pageSize) {
        Map<String, Object> searchParams = buildSearchParams(entityCondition);
        return findAllBySearchParams(searchParams, pageNumber, pageSize);
    }

    /**
     * 带参数的分页查询
     * @param searchParams 搜索条件
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return Page<E>
     */
    public Page<E> findAllBySearchParams(Map<String, Object> searchParams, int pageNumber, int pageSize) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize);
        Specification<E> spec = buildSpecification(searchParams);
        log.debug("findAllBySearchParams:{}",searchParams.toString());
        return this.getEntityDAO().findAll(spec, pageRequest);
    }

    /**
     * 带参数的分页查询
     * 1，提取分页参数，组装到PageRequest对象中。
     * 2，提取查询过滤参数，组装到Specification<T>对象中。
     * 3，执行查询（参数包装对象，分页对象）
     * @param searchParams 搜索条件
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @param orderField 排序列
     * @return Page<E>
     */
    public Page<E> findAllAndSort(Map<String, Object> searchParams, int pageNumber, int pageSize, String orderField, Direction direction) {
        PageRequest pageRequest = buildPageRequest(pageNumber, pageSize, orderField ,direction);
        Specification<E> spec = buildSpecification(searchParams);
        return this.getEntityDAO().findAll(spec, pageRequest);
    }

    protected Specification<E> buildSpecification(String propertyName, Object propertyValue) {
        return buildSpecification(buildSearchParams(propertyName, propertyValue));
    }

    protected Specification<E> buildSpecification(E entity) {
        return buildSpecification(buildSearchParams(entity));
    }

    protected Specification<E> buildSpecification(Map<String, Object> searchParams) {
        return DynamicSpecifications.bySearchFilter(
                SearchFilter.parse(searchParams).values(), this.clazz);
    }

    /**
     * 把实体对象封装成Map<String,Object>
     * 请严格匹配数据库的字段，否则用 E entity参数做最强验证
     * 如：object对象 ==>Map｛EQ_id:1;EQ_name:admin}
     * @param entity 搜索条件实体类
     * @return Map
     */
    protected Map<String, Object> buildSearchParams(Object entity) {
        Map<String, Object> searchParams = new HashMap<>();
        Map<String, Object> map = ObjectConvertUtil.objectToMap(entity);
        if (map != null && !map.isEmpty()) {
            for (Entry<String, Object> entry : map.entrySet()) {
                // 过滤掉空值
                String key = entry.getKey();
                Object value = entry.getValue();
                if (null != value) {
                    if (value instanceof String) {
                        if (StringUtils.isBlank((String) value)) {
                            continue;
                        }
                    }
                    String searchParam = "EQ_" + key;
                    searchParams.put(searchParam, value);
                }
            }
        }
        return searchParams;
    }

    /**
     * 把实体对象封装成Map<String,Object>
     * 如：object对象 ==>Map｛EQ_id:1;EQ_name:admin}
     * @param propertyName 搜索属性名
     * @param propertyValue 搜索属性值
     * @return Map
     */
    protected Map<String, Object> buildSearchParams(String propertyName, Object propertyValue) {
        Map<String, Object> searchParams = new HashMap<>();
        String searchParam = "EQ_" + propertyName;
        searchParams.put(searchParam, propertyValue);
        return searchParams;
    }

    protected static final Sort SORT_ID = new Sort(Direction.DESC, "id");
    /**
     * 创建分页请求.
     * @param pageNumber 页码
     * @param pagzSize 每页条数
     * @return PageRequest
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize) {
        //Sort sort = new Sort(Direction.DESC, "id");
        return new PageRequest(pageNumber - 1, pagzSize, SORT_ID);
    }

    /**
     * 创建分页请求.
     * @param pageNumber 页码
     * @param pagzSize 每页条数
     * @param orderField 排序列
     * @param direction 顺序或倒序
     * @return PageRequest
     */
    protected PageRequest buildPageRequest(int pageNumber, int pagzSize, String orderField, Direction direction) {
        Sort sort = SORT_ID;
        if (StringUtils.isNotBlank(orderField) && !"auto".equals(orderField) && direction != null) {
            sort = new Sort(direction, orderField.trim());
        }
        return new PageRequest(pageNumber - 1, pagzSize, sort);
    }


}
