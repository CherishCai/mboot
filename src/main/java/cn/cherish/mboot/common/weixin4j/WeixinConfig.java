package cn.cherish.mboot.common.weixin4j;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public class WeixinConfig {

	public WeixinConfig() {
	}

	private static Properties props = new Properties();
	static {
		try {
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("weixin.properties"));
		} catch (IOException e) {
			log.error("init Fail ", Throwables.getStackTraceAsString(e));
		}
	}

	public static String getValue(String key) {
		return props.getProperty(key);
	}

	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}
}