package design.mode.singleton;

public class InnerClassLazySingleton {
    private InnerClassLazySingleton(){
    }

    public static InnerClassLazySingleton getInstance(){
       return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder{
        static InnerClassLazySingleton INSTANCE = new InnerClassLazySingleton();
    }
}
