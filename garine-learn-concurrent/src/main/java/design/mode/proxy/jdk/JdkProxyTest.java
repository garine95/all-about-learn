package design.mode.proxy.jdk;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

public class JdkProxyTest {
    public static void main(String[] args) throws IOException {
        Action targetAction = new TargetAction();
        Action proxyAction = (Action) Proxy.newProxyInstance(targetAction.getClass().getClassLoader(), targetAction.getClass().getInterfaces(), new InvocationHandlerAction(targetAction));
        proxyAction.move();
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Action.class});
        FileOutputStream outputStream = new FileOutputStream(new File("D://$Proxy0.class"));
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
