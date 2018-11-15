package design.mode.decorator;

/**
 * 实现相同的接口，重在扩展源方法实现.装饰器类与原来的类同级
 */
public class DecoratedAction implements Action{
    private SimpleAction simpleAction;

    public DecoratedAction(SimpleAction simpleAction){
        this.simpleAction = simpleAction;
    }

    /**
     * 装饰增强方法逻辑，再执行原逻辑
     */
    @Override
    public void action() {
        System.out.println("decorated actionv logic");
        simpleAction.action();
    }
}
