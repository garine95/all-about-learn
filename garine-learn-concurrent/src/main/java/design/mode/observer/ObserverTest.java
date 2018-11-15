package design.mode.observer;

import java.lang.reflect.InvocationTargetException;

public class ObserverTest {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Subject subject = new Subject();
        subject.addListener("add", new EventAdapter(){
            @Override
            public void add(){
                System.out.println("观察者收到--》触发add监听");
            }
        });
        subject.addListener("insert", new EventAdapter(){
            @Override
            public void insert(){
                System.out.println("观察者收到--》触发insert监听");
            }
        });

        subject.trigerEvent("add");

        subject.trigerEvent("insert");
    }
}
