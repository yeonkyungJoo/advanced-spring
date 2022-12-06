package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.app.advice.LogTraceAdvice;
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
public class ProxyFactoryV1Config {

    private Advisor getAdvisor(LogTrace trace) {

        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        LogTraceAdvice advice = new LogTraceAdvice(trace);

        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {

        OrderControllerV1 controller = new OrderControllerV1Impl(orderServiceV1(trace));

        ProxyFactory proxyFactory = new ProxyFactory(controller);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderControllerV1 proxy = (OrderControllerV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), controller.getClass());
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {

        OrderServiceV1 service = new OrderServiceV1Impl(orderRepositoryV1(trace));

        ProxyFactory proxyFactory = new ProxyFactory(service);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderServiceV1 proxy = (OrderServiceV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), service.getClass());
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {

        OrderRepositoryV1 repository = new OrderRepositoryV1Impl();

        ProxyFactory proxyFactory = new ProxyFactory(repository);
        proxyFactory.addAdvisor(getAdvisor(trace));
        OrderRepositoryV1 proxy = (OrderRepositoryV1) proxyFactory.getProxy();
        log.info("ProxyFactory proxy={}, target={}", proxy.getClass(), repository.getClass());
        return proxy;
    }
}
