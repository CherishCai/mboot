package cn.cherish.mboot.extra.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ServiceMonitor {

	@AfterReturning("execution(* cn.cherish.mboot.service..*Service.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		log.info("Completed: " + joinPoint);
	}

}