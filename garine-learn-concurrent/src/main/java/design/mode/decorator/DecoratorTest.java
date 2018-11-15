package design.mode.decorator;

public class DecoratorTest {
    public static void main(String[] args) {
        SimpleAction simpleAction =new SimpleAction();
        DecoratedAction decoratedAction = new DecoratedAction(simpleAction);
        decoratedAction.action();
    }
}
