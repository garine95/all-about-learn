package design.mode.strategyandtemplate.template;

public class XiaomingFightStrategy extends AbstractFightStrategy {
    @Override
    public void doXuanfengtui() {
        System.out.println("小明旋风腿");
    }

    @Override
    public void doDalizhang() {
        System.out.println("小明大力掌");
    }
}
