package hello.advanced.aop.order.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class AspectV6 {

    @Around("hello.advanced.aop.order.aspect.PointCuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {


            log.info("[트랜잭션 시작] {}", joinPoint.getSignature());

            // @Before

            Object result = joinPoint.proceed();

            // @AfterReturning

            log.info("[트랜잭션 커밋] {}", joinPoint.getSignature());
            return result;

        } catch (Exception e) {

            // @AfterThrowing

            log.info("[트랜잭션 롤백] {}", joinPoint.getSignature());
            throw e;

        } finally {

            // @After

            log.info("[리소스 릴리즈] {}", joinPoint.getSignature());
        }
    }

    @Before("hello.advanced.aop.order.aspect.PointCuts.orderAndService()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("[before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "hello.advanced.aop.order.aspect.PointCuts.orderAndService()", returning = "result")
    public void doReturn(JoinPoint joinPoint, Object result) {
        log.info("[return] {} return={}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "hello.advanced.aop.order.aspect.PointCuts.orderAndService()", throwing = "ex")
    public void doThrowing(JoinPoint joinPoint, Exception ex) {
        log.info("[ex] {} message={}", joinPoint.getSignature(), ex.getMessage());
    }

    @After("hello.advanced.aop.order.aspect.PointCuts.orderAndService()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("[after] {}", joinPoint.getSignature());
    }
}
