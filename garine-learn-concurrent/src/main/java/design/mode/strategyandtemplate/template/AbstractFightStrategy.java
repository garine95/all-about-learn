package design.mode.strategyandtemplate.template;

public abstract class AbstractFightStrategy implements FightStrategy{

    /**
     * 骨架方法，公共总处理逻辑
     */
    @Override
    public void dofight(){
        System.out.println("问候他");
        //差异步骤，子类实现
        this.doXuanfengtui();
        //差异步骤，子类实现
        this.doDalizhang();
        System.out.println("溜了溜了");
    }

    /**
     * 差异步骤
     */
    public abstract void doXuanfengtui();

    /**
     * 差异步骤
     */
    public abstract void doDalizhang();
}
