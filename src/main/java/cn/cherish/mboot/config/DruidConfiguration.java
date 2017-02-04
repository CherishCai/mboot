package cn.cherish.mboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

/**
 * druid 配置.
 */
@Slf4j
@Configuration
public class DruidConfiguration {

    @Bean
    public DruidDataSource dataSource() {

        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mboot?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=UTC&autoReconnect=true");
        dataSource.setUsername("root");
        dataSource.setPassword("caimengyuan");

        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenEvictionRunsMillis(60000);
        dataSource.setMinEvictableIdleTimeMillis(300000);

        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setValidationQuery("SELECT 1 FROM t_user");

        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);

        try {
            dataSource.setFilters("stat,wall,log4j");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        dataSource.setConnectionProperties("druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500");
        dataSource.setUseGlobalDataSourceStat(true);

        return dataSource;
    }

    /**
     * 注册一个StatViewServlet
     */
    @Bean
    public ServletRegistrationBean DruidStatViewServlet(){
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册.
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(),"/druid/*");

        //添加初始化参数：initParams

        //白名单：
        servletRegistrationBean.addInitParameter("allow","127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.
        servletRegistrationBean.addInitParameter("deny","192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername","cherish");
        servletRegistrationBean.addInitParameter("loginPassword","cherish");
        //是否能够重置数据.
        servletRegistrationBean.addInitParameter("resetEnable","false");// 禁用HTML页面上的“Reset All”功能
        return servletRegistrationBean;
    }

    /**
     * 注册一个：filterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean druidStatFilter(){

        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        filterRegistrationBean.setName("druidWebStatFilter");
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");

        //添加忽略的格式信息.
        filterRegistrationBean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

}
