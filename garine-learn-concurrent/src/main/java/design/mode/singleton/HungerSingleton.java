package design.mode.singleton;

public class HungerSingleton {
    private static HungerSingleton singleton = new HungerSingleton();

    private HungerSingleton(){
    }

    public static HungerSingleton getInstance(){
        return singleton;
    }
}
