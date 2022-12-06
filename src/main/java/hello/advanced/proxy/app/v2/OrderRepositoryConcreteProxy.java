package hello.advanced.proxy.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class OrderRepositoryConcreteProxy extends OrderRepositoryV2 {

    private final OrderRepositoryV2 target;
    private final LogTrace trace;

    public OrderRepositoryConcreteProxy(OrderRepositoryV2 target, LogTrace trace) {
        this.target = target;
        this.trace = trace;
    }

    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderRepository.save()");

            // target 호출
            target.save(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
