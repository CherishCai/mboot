package cn.cherish.mboot.extra.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

/**
 * Created by Cherish on 2017/1/6.
 */
public class MyReids {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void opsForValue() {
        ValueOperations<String, String> ops = this.redisTemplate.opsForValue();
        String key = "spring.boot.redis.test";
        if (!this.redisTemplate.hasKey(key)) {
            ops.set(key, "foo");
        }
        System.out.println("Found key " + key + ", value=" + ops.get(key));
    }


}
