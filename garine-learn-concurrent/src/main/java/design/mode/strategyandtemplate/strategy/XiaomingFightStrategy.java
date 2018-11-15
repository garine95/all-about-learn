package design.mode.strategyandtemplate.strategy;

public class XiaomingFightStrategy implements FightStrategy {
    @Override
    public void dofight() {
        System.out.println("问候他");
        System.out.println("小明旋风腿");
        System.out.println("小明大力掌");
        System.out.println("溜了溜了");
    }
}
