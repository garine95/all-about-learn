package design.mode.proxy.jdk;

public class TargetAction implements Action {
    @Override
    public void move() {
        System.out.println("target doing move");
    }
}
