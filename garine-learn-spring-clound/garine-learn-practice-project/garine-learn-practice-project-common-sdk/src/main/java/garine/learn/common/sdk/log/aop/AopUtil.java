package garine.learn.common.sdk.log.aop;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.support.AopUtils;

import java.lang.reflect.Field;

/**
 * @author zhoujy
 * @date 2019年02月21日
 **/
@Slf4j
public class AopUtil {

    public static Object getTarget(Object beanInstance) {
        if (!AopUtils.isAopProxy(beanInstance)) {
            return beanInstance;
        } else if (AopUtils.isCglibProxy(beanInstance)) {
            try {
                Field h = beanInstance.getClass().getDeclaredField("CGLIB$CALLBACK_0");
                h.setAccessible(true);
                Object dynamicAdvisedInterceptor = h.get(beanInstance);

                Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
                advised.setAccessible(true);

                Object target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
                return target;
            } catch (Exception e) {
                log.error("获取cglib代理类的原始对象异常{}"+ Throwables.getStackTraceAsString(e));
            }
        }
        return null;
    }
}
