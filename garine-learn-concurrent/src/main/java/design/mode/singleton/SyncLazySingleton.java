package design.mode.singleton;

public class SyncLazySingleton {
    private static SyncLazySingleton singleton;

    private SyncLazySingleton(){
    }

    public synchronized static SyncLazySingleton getInstance(){
        if (singleton == null){
            singleton = new SyncLazySingleton();
            return singleton;
        }else {
            return singleton;
        }
    }
}
