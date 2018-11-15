package design.mode.singleton;

public class SingletonTest {
    public static void main(String[] args) {
        for (int i=0;i< 30;i++){
            new Thread(() -> {
                //System.out.println(HungerSingleton.getInstance());
                //System.out.println(StaticHungerSingleton.getInstance());
               // System.out.println(SyncLazySingleton.getInstance());
                //System.out.println(DubbleCheckLazySingleton.getInstance());
                //System.out.println(InnerClassLazySingleton.getInstance());
                System.out.println(RegisterSingleton.getInstance("set").hashCode());
            }).start();
        }
    }
}
