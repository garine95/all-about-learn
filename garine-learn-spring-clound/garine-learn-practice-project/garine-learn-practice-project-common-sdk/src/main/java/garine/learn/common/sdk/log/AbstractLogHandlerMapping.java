package garine.learn.common.sdk.log;

import com.fpx.common.util.StringUtils;
import com.fpx.fb4.common.sdk.log.annotation.LogHandler;
import com.fpx.fb4.common.sdk.log.aop.AopUtil;
import com.fpx.fb4.common.sdk.log.entity.LogHandlerMethod;
import com.fpx.fb4.common.sdk.log.entity.LogInfo;
import com.fpx.fb4.common.util.JsonUtils;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.support.WebApplicationObjectSupport;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoujy
 * @date 2019年02月21日
 **/
@Slf4j
public abstract class AbstractLogHandlerMapping extends WebApplicationObjectSupport implements InitializingBean {

    private Map<String, LogHandlerMethod> methodRegistry = new HashMap<>();

    @Override
    public void afterPropertiesSet() throws Exception {
        initLogHandlerMethod();
    }

    /**
     * 初始化LogHandlerMethod
     */
    private void initLogHandlerMethod(){
        String[] beanNames = getBeamNames();
        if (null != beanNames){
            for (int i=0;i<beanNames.length;i++){
                AbstractLogHandlerMapping hm = obtainApplicationContext().getBean(beanNames[i], AbstractLogHandlerMapping.class);
                detectLogHandleMethod(hm);
            }
        }
    }

    /**
     * 搜索HandlerMapping中的LogHandler标注的处理器方法并保存到methodRegistry
     * @param hm
     */
    private void detectLogHandleMethod(AbstractLogHandlerMapping hm) {
        Method[] methods = AopUtil.getTarget(hm).getClass().getMethods();
        for (Method method : methods) {
            String methodName = getLogMethodName(method);
            if (!StringUtils.isEmpty(methodName)){
                methodRegistry.put(methodName, createLogMethod(hm, method));
            }
        }
    }

    private LogHandlerMethod createLogMethod(AbstractLogHandlerMapping hm, Method method) {
        return new LogHandlerMethod(hm, method);
    }

    /**
     * 获取方法上LogHandler注解的name值，如果没有表示不是处理器
     * @param method
     * @return
     */
    private String getLogMethodName(Method method) {
        Annotation[] annotations = method.getDeclaredAnnotations();
        for (Annotation mta : annotations) {
            if (mta instanceof LogHandler){
                return ((LogHandler) mta).name();
            }
        }
        return null;
    }

    /**
     * 默认实现查找所有AbstractLogHandlerMapping类以及子类，此方法子类可以覆盖实现来控制搜索HanlerMapping的范围
     * @return
     */
    protected String[] getBeamNames(){
        return obtainApplicationContext().getBeanNamesForType(AbstractLogHandlerMapping.class);
    }

    public void doDispatch(String logInfoJson){
        try {
            LogInfo logInfo = JsonUtils.jsonToBean(logInfoJson, LogInfo.class);
            LogHandlerMethod method = methodRegistry.get(logInfo.getLogType());
            method.invoke(logInfo.getLogContent());
        } catch (InvocationTargetException | IllegalAccessException e) {
            log.error("异步写日志反射调用异常{}", Throwables.getStackTraceAsString(e));
        }
    }
}
