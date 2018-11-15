package design.mode.singleton.seriable;

import java.io.Serializable;

public class SeriableHungerSingleton implements Serializable{
    private static SeriableHungerSingleton singleton = new SeriableHungerSingleton();

    private SeriableHungerSingleton(){
    }

    public static SeriableHungerSingleton getInstance(){
        return singleton;
    }

    public Object readResolve(){
        return singleton;
    }
}
