package hello.advanced.proxy.dynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection1() throws Exception {

        Class clazz = Class.forName("hello.advanced.proxy.dynamic.ReflectionTest$Hello");
        Hello target = new Hello();

        Method methodCallA = clazz.getMethod("callA");
        Object resultA = methodCallA.invoke(target);
        log.info("resultA={}", resultA);

        Method methodCallB = clazz.getMethod("callB");
        Object resultB = methodCallB.invoke(target);
        log.info("resultB={}", resultB);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
        Object result = method.invoke(target);
        log.info("result={}", result);
    }

    @Test
    void reflection0() {

        Hello target = new Hello();

        log.info("start");
        String resultA = target.callA();
        log.info("result={}", resultA);

        log.info("start");
        String resultB = target.callB();
        log.info("result={}", resultB);
    }

    @Slf4j
    static class Hello {

        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
