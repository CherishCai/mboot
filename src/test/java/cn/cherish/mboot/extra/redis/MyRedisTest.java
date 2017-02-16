package cn.cherish.mboot.extra.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Cherish on 2017/1/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRedisTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void opsForValue() {
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String key = "spring.boot.redis.test2";
        opsForValue.setIfAbsent(key, "cherish");
        System.out.println("Found key " + key + ", value=" + opsForValue.get(key));
    }

    @Test
    public void opsForList() {
        ListOperations<String, String> opsForList = redisTemplate.opsForList();

        opsForList.leftPush("listTest","m0");
        opsForList.leftPush("listTest","m1");
        opsForList.leftPush("listTest","m2");
        opsForList.leftPushAll("listTest", "m3","m4","m5");

        String leftPop = opsForList.leftPop("listTest");
        String rightPop = opsForList.rightPop("listTest");
        System.out.println("leftPop = " + leftPop);
        System.out.println("rightPop = " + rightPop);
    }

    @Test
    public void opsForSet() {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.add("mset", "hh","enheng");
        Set<String> members = opsForSet.members("mset");
        System.out.println("members = " + members);

    }

    @Test
    public void osForHash(){
        HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
        Map<String,String> author = new HashMap<>();
        author.put("love", "cherish");
        author.put("heigth", "170");
        opsForHash.put("author","name","cherish");
        opsForHash.putAll("author",author);

    }

}