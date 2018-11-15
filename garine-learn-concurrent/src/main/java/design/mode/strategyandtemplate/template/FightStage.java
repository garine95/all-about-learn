package design.mode.strategyandtemplate.template;

public class FightStage {
    public void dofight(FightStrategy fightStrategy){
        fightStrategy.dofight();
    }
}
