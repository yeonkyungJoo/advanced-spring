package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.proxy.app.v1.handler.LogTraceBasicHandler;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;

@Configuration
public class DynamicProxyBasicConfig {

    public static final String[] PATTERNS = {"request*", "order*", "save*"};

    @Bean
    public OrderControllerV1 orderControllerV1(LogTrace trace) {
        OrderControllerV1Impl controller = new OrderControllerV1Impl(orderServiceV1(trace));
        OrderControllerV1 proxy = (OrderControllerV1) Proxy.newProxyInstance(OrderControllerV1.class.getClassLoader(),
                new Class[]{OrderControllerV1.class}, new LogTraceBasicHandler(controller, trace, PATTERNS));
        return proxy;
    }

    @Bean
    public OrderServiceV1 orderServiceV1(LogTrace trace) {
        OrderServiceV1Impl service = new OrderServiceV1Impl(orderRepositoryV1(trace));
        OrderServiceV1 proxy = (OrderServiceV1) Proxy.newProxyInstance(OrderServiceV1.class.getClassLoader(),
                new Class[]{OrderServiceV1.class}, new LogTraceBasicHandler(service, trace, PATTERNS));
        return proxy;
    }

    @Bean
    public OrderRepositoryV1 orderRepositoryV1(LogTrace trace) {
        OrderRepositoryV1Impl repository = new OrderRepositoryV1Impl();
        OrderRepositoryV1 proxy = (OrderRepositoryV1) Proxy.newProxyInstance(OrderRepositoryV1.class.getClassLoader(),
                new Class[]{OrderRepositoryV1.class}, new LogTraceBasicHandler(repository, trace, PATTERNS));
        return proxy;
    }
}
