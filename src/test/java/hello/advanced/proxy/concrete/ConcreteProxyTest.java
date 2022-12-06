package hello.advanced.proxy.concrete;

import hello.advanced.proxy.concrete.code.ConcreteLogic;
import hello.advanced.proxy.concrete.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }
}
