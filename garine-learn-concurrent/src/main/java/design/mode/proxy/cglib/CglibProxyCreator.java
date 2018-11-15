package design.mode.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

public class CglibProxyCreator {
    public static <T> T create(Class<T> clazz, MethodInterceptor callbackinvoker){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(callbackinvoker);
        return (T) enhancer.create();
    }
}
