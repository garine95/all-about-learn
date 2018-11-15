package design.mode.delegate;

public class DelegateTest {
    public static void main(String[] args) {
        Action action = new DispatcherAction();
        action.doAction("walk");
        action.doAction("run");
    }
}
