package design.mode.strategyandtemplate.strategy;

public class StrategyTest {
    public static void main(String[] args) {
        //定义策略
        FightStrategy xiaoming = new XiaomingFightStrategy();
        FightStrategy xiaoqiang = new XiaoqiangFightStrategy();
        FightStage fightStage = new FightStage();
        //传入策略进行执行
        fightStage.dofight(xiaoming);
        fightStage.dofight(xiaoqiang);
    }
}
