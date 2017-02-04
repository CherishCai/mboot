package cn.cherish.mboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@EnableAspectJAutoProxy //默认已开启
//@EnableTransactionManagement //默认已开启
//@EnableJpaRepositories({"cn.cherish.mboot.repository","cn.cherish.mboot.extra.solr"}) //默认已开启
//@ImportResource({"classpath:spring-shiro.xml","classpath:spring-other.xml"}) //需要引入xml的话
@SpringBootApplication
public class MbootApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(MbootApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(MbootApplication.class, args);
	}


}
