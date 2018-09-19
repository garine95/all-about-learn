package garine.learn.breaker.annotation;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

/**
 * aop切面
 */
@Aspect
@Component
public class BreakerCommonndAspect {
    /**
     * 熔断策略-信号量，一般是个分布式配置
     */
    static final String BREAKER_STRATEGY_SEMAPHORE = "SEMAPHORE";

    static final Semaphore semaphore = new Semaphore(1);

    /**
     * 熔断策略-熔断时间
     */
    static final String BREAKER_STRATEGY_BREAKTIME = "BREAKTIME";

    /**
     * 线程池
     */
    ExecutorService executorService = Executors.newFixedThreadPool(20);

    @Around("@annotation(garine.learn.breaker.annotation.BreakerCommand)")
    public Object breakerCommonndAspect(ProceedingJoinPoint joinPoint) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        Method targetMethod = getCurrentMethod(joinPoint);
        Object[] args = joinPoint.getArgs();
        //future代理调用方法
        Future<Object> future =  executorService.submit(() -> {
           Object returnVal = null;
            try {
                returnVal = joinPoint.proceed(args);
            } catch (Throwable throwable) {
                System.out.println(throwable.getStackTrace());
            }
            return returnVal;
        });
        BreakerCommand annotation = targetMethod.getAnnotation(BreakerCommand.class);

        Object returnVal = null;
        if (BREAKER_STRATEGY_BREAKTIME.equals(annotation.strategy())){
            try {
                //方便测试随机一个熔断时间
                returnVal = future.get(new Random().nextInt(120), TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                System.out.println(e.getStackTrace());
            } catch (ExecutionException e) {
                System.out.println(e.getStackTrace());
            } catch (TimeoutException e) {
               //熔断处理逻辑
                returnVal = fallBack(args);
            }
        }else if (BREAKER_STRATEGY_SEMAPHORE.equals(annotation.strategy())){
            //信号量控制允许访问请求个数
            try {
                //单机环境下可行
                if (semaphore.tryAcquire()){
                    System.out.println("-------------got permission");
                    returnVal = joinPoint.proceed(args);
                }else {
                    System.out.println("-------------got denied");
                    returnVal = fallBack(args);
                }
            } catch (Throwable throwable) {
                System.out.println(Arrays.toString(throwable.getStackTrace()));
            }finally {
                //释放信号
                semaphore.release();
            }

        }
        return returnVal;
    }

    private Method getCurrentMethod(ProceedingJoinPoint pjp){
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        //方法元信息
        msig = (MethodSignature) sig;
        Object target = pjp.getTarget();
        Method currentMethod = null;
        try {
            currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            return currentMethod;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }

    public String fallBack(Object[] args){
        return "hi.this is breaker service fallback: " + Arrays.toString(args);
    }
}
