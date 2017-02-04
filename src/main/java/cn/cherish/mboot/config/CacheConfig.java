package cn.cherish.mboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Cherish on 2017/1/4.
 */
@Slf4j
@Configuration
@EnableCaching
public class CacheConfig {


}
/*
    缓存类例子
    @Component
    @CacheConfig(cacheNames = "countries")
    public class CountryRepository {

        @Cacheable
        public Country findByCode(String code) {
            System.out.println("---> Loading country with code '" + code + "'");
            return new Country(code);
        }

    }
*/
