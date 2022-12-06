package hello.advanced.proxy.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

public class OrderControllerConcreteProxy extends OrderControllerV2 {

    private final OrderControllerV2 target;
    private final LogTrace trace;

    public OrderControllerConcreteProxy(OrderControllerV2 target, LogTrace trace) {
        super(null);
        this.target = target;
        this.trace = trace;
    }

    @Override
    public String request(String itemId) {
        TraceStatus status = null;
        try {
            status = trace.begin("OrderController.request()");

            // target 호출
            String result = target.request(itemId);

            trace.end(status);
            return result;

        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
