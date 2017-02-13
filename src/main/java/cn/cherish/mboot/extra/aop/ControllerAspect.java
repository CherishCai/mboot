package cn.cherish.mboot.extra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ControllerAspect {

	@After("execution(public * cn.cherish.mboot.web.*Controller.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		log.info("ControllerMonitor: " + joinPoint);
	}

}
