package hello.advanced.proxy.config;

import hello.advanced.proxy.app.advice.LogTraceAdvice;
import hello.advanced.proxy.app.v2.OrderControllerV2;
import hello.advanced.proxy.app.v2.OrderRepositoryV2;
import hello.advanced.proxy.app.v2.OrderServiceV2;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ProxyFactoryV2Config {

    private Advisor getAdvisor(LogTrace trace) {

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public OrderControllerV2 orderControllerV2(LogTrace trace) {

        OrderControllerV2 controller = new OrderControllerV2(orderServiceV2(trace));

        ProxyFactory proxyFactory = new ProxyFactory(controller);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderControllerV2 proxy = (OrderControllerV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), controller.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV2 orderServiceV2(LogTrace trace) {

        OrderServiceV2 service = new OrderServiceV2(orderRepositoryV2(trace));

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderServiceV2 proxy = (OrderServiceV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), service.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV2 orderRepositoryV2(LogTrace trace) {

        OrderRepositoryV2 repository = new OrderRepositoryV2();

        ProxyFactory proxyFactory = new ProxyFactory(repository);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV2 proxy = (OrderRepositoryV2) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), repository.getClass());
        return proxy;
    }
}
