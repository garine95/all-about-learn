package design.mode.singleton;

public class DubbleCheckLazySingleton {
    private static volatile DubbleCheckLazySingleton singleton;

    private DubbleCheckLazySingleton(){
    }

    public static DubbleCheckLazySingleton getInstance(){
        if (singleton == null){
            synchronized (DubbleCheckLazySingleton.class){
                if (singleton == null){
                    singleton = new DubbleCheckLazySingleton();
                    return singleton;
                }else {
                    return singleton;
                }
            }
        }else {
            return singleton;
        }
    }
}
