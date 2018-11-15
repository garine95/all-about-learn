package design.mode.observer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventListener {
    private Map<String, EventAdapter> listeners = new ConcurrentHashMap<>();

    private static Map<String, Method> methodMap = new HashMap<>();
    static {
        Method[] methods = EventAdapter.class.getMethods();
        for (Method method : methods) {
            methodMap.put(method.getName(), method);
        }
    }

    public void addListener(String event, EventAdapter adapter){
        listeners.put(event, adapter);
    }

    public void trigerEvent(String event) throws InvocationTargetException, IllegalAccessException {
        Method method = methodMap.get(event);
        EventAdapter adapter = listeners.get(event);
        method.invoke(adapter, null);
    }
}
