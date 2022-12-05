package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.ContextV1;
import hello.advanced.trace.strategy.code.Strategy;
import hello.advanced.trace.strategy.code.StrategyLogic1;
import hello.advanced.trace.strategy.code.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {


    @Test
    void strategyV3() {

        ContextV1 context = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
        context.execute();

        context = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
        context.execute();
    }

    // 익명 내부 클래스
    @Test
    void strategyV2() {

        ContextV1 context = null;

        Strategy strategyLogic1 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("strategyLogic1={}", strategyLogic1.getClass());
        context = new ContextV1(strategyLogic1);
        context.execute();

        Strategy strategyLogic2 = new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("strategyLogic2={}", strategyLogic2.getClass());
        context = new ContextV1(strategyLogic2);
        context.execute();

    }

    @Test
    void strategyV1() {

        ContextV1 context = null;
        Strategy strategyLogic1 = new StrategyLogic1();
        context = new ContextV1(strategyLogic1);
        context.execute();

        Strategy strategyLogic2 = new StrategyLogic2();
        context = new ContextV1(strategyLogic2);
        context.execute();
    }

    @Test
    void strategyV0() {
        logic1();
        logic2();
    }

    private void logic1() {

        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직1 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }

    private void logic2() {

        long startTime = System.currentTimeMillis();

        // 비즈니스 로직 실행
        log.info("비즈니스 로직2 실행");
        // 비즈니스 로직 종료

        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
