package design.mode.strategyandtemplate.template;

public class XiaoqiangFightStrategy extends AbstractFightStrategy {

    @Override
    public void doXuanfengtui() {
        System.out.println("小强旋风腿");
    }

    @Override
    public void doDalizhang() {
        System.out.println("小强大力掌");
    }
}
