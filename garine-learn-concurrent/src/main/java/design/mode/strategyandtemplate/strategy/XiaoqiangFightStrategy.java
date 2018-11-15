package design.mode.strategyandtemplate.strategy;

public class XiaoqiangFightStrategy implements FightStrategy {
    @Override
    public void dofight() {
        System.out.println("问候他");
        System.out.println("小强旋风腿");
        System.out.println("小强大力掌");
        System.out.println("溜了溜了");
    }
}
