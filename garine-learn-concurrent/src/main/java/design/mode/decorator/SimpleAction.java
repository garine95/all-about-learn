package design.mode.decorator;

/**
 * 实现接口，原始方法实现
 */
public class SimpleAction implements Action {
    @Override
    public void action() {
        System.out.println("simple action");
    }
}
