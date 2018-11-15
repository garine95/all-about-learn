package design.mode.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class InvocationHandlerAction implements InvocationHandler {
    private Action target;

    public InvocationHandlerAction(Action target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoker doing");
        return method.invoke(target, args);
    }
}
