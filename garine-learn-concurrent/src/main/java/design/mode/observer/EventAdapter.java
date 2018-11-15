package design.mode.observer;

public class EventAdapter {
    public void add(){
        throw new RuntimeException("adapter wasn't overrided");
    }

    public void insert(){
        throw new RuntimeException("adapter wasn't overrided");
    }
}
