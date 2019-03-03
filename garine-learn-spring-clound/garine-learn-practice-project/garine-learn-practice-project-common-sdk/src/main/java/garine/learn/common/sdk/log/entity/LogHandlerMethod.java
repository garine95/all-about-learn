package garine.learn.common.sdk.log.entity;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 日志操作处理器
 * @author zhoujy
 * @date 2019年02月21日
 **/
public class LogHandlerMethod {

    /**
     * 对应的日志操作bean
     */
    private Object logHandlerBean;

    /**
     * 对应的日志操作方法
     */
    private Method logHandler;

    public LogHandlerMethod(Object logHandlerBean, Method logHandler) {
        this.logHandlerBean = logHandlerBean;
        this.logHandler = logHandler;
    }

    public void invoke(Object... args) throws InvocationTargetException, IllegalAccessException {
        logHandler.invoke(logHandlerBean, args);
    }
}
