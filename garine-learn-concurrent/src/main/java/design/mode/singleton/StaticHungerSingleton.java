package design.mode.singleton;

public class StaticHungerSingleton {
    private static StaticHungerSingleton singleton;
    static {
        singleton = new StaticHungerSingleton();
    }

    private StaticHungerSingleton(){

    }
    public static StaticHungerSingleton getInstance(){
        return singleton;
    }
}
