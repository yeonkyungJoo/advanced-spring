package hello.advanced.proxy.app.v1;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderServiceInterfaceProxy implements OrderServiceV1 {

    private final OrderServiceV1 target;
    private final LogTrace trace;

    @Override
    public void orderItem(String itemId) {

        TraceStatus status = null;
        try {
            status = trace.begin("OrderService.orderItem()");

            // target 호출
            target.orderItem(itemId);

            trace.end(status);
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }
}
