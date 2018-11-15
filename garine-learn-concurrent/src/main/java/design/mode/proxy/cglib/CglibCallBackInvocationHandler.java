package design.mode.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibCallBackInvocationHandler implements MethodInterceptor{
    private TargetAction targetAction;

    public CglibCallBackInvocationHandler(TargetAction targetAction){
        this.targetAction = targetAction;
    }

    /**
     * 类似动态代理的InvocationHandler作用
     * @param o
     * @param method
     * @param objects
     * @param methodProxy
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("invoker doinig move");
        //return methodProxy.invokeSuper(o, objects);
        return method.invoke(targetAction, objects);
    }
}
