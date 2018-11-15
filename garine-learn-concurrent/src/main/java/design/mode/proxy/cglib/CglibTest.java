package design.mode.proxy.cglib;

public class CglibTest  {
    public static void main(String[] args) {
        TargetAction targetAction = new TargetAction("defintioin word");
        TargetAction cglibProxy = CglibProxyCreator.create(TargetAction.class, new CglibCallBackInvocationHandler(targetAction));
        cglibProxy.move();
    }
}
