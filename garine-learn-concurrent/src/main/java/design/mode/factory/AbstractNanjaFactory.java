package design.mode.factory;

public abstract class AbstractNanjaFactory {

    public void publicLogic(){
        System.out.println("构建对象用到公共逻辑");
    }

    public abstract Nanja getWater();

    public abstract Nanja getFire();
}
