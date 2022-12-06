package hello.advanced;

import hello.advanced.proxy.config.DynamicProxyBasicConfig;
import hello.advanced.proxy.config.ProxyFactoryV1Config;
import hello.advanced.proxy.config.ProxyFactoryV2Config;
import hello.advanced.proxy.config.ProxyV2Config;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

//@Import(ProxyV1Config.class)
//@Import(ProxyV2Config.class)
//@Import(DynamicProxyBasicConfig.class)
//@Import(ProxyFactoryV1Config.class)
@Import(ProxyFactoryV2Config.class)
@SpringBootApplication(scanBasePackages = "hello.advanced.proxy.app")
public class AdvancedApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedApplication.class, args);
	}

	@Bean
	public LogTrace logTrace() {
		return new ThreadLocalLogTrace();
	}
}
