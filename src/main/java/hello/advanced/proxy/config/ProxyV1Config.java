package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v1.*;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyV1Config {

    @Bean
    public OrderControllerV1 orderController(LogTrace trace) {
        OrderControllerV1Impl controllerImpl = new OrderControllerV1Impl(orderService(trace));
        return new OrderControllerInterfaceProxy(controllerImpl, trace);
    }

    @Bean
    public OrderServiceV1 orderService(LogTrace trace) {
        OrderServiceV1Impl serviceImpl = new OrderServiceV1Impl(orderRepository(trace));
        return new OrderServiceInterfaceProxy(serviceImpl, trace);
    }

    @Bean
    public OrderRepositoryV1 orderRepository(LogTrace trace) {
        OrderRepositoryV1Impl repositoryImpl = new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(repositoryImpl, trace);
    }
}
