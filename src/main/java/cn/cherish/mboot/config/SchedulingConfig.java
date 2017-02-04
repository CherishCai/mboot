package cn.cherish.mboot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by Cherish on 2017/1/4.
 */
@Slf4j
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

    @Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
    public void scheduler() {
        log.debug(">>>>>>>>>>>>> scheduled ... ");
    }

}