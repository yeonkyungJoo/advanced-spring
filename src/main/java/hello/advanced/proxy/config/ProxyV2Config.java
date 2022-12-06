package hello.advanced.proxy.config;

import hello.advanced.proxy.app.v2.*;
import hello.advanced.trace.logtrace.LogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyV2Config {

    @Bean
    public OrderControllerV2 orderController(LogTrace trace) {
        OrderControllerV2 controller = new OrderControllerV2(orderService(trace));
        return new OrderControllerConcreteProxy(controller, trace);
    }

    @Bean
    public OrderServiceV2 orderService(LogTrace trace) {
        OrderServiceV2 service = new OrderServiceV2(orderRepository(trace));
        return new OrderServiceConcreteProxy(service, trace);
    }

    @Bean
    public OrderRepositoryV2 orderRepository(LogTrace trace) {
        OrderRepositoryV2 repository = new OrderRepositoryV2();
        return new OrderRepositoryConcreteProxy(repository, trace);
    }
}
