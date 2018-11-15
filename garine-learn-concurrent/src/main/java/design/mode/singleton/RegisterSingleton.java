package design.mode.singleton;

import java.util.*;

public class RegisterSingleton {
    private static Map<String, Object> container = new HashMap<>();

    private RegisterSingleton(){
    }

    public static Object getInstance(String type){
        if (Objects.isNull(type)){
            return null;
        }
        Object  o = container.get(type);
        if (Objects.isNull(o)){
            doInitObject(type);
        }
        return container.get(type);
    }

    /**
     * 保证初始化过程线程安全即可
     * @param type
     */
    private synchronized static void doInitObject(String type) {
        if ("set".equals(type)){
            container.put(type, new HashSet<>());
        }else if ("list".equals(type)){
            container.put(type, new ArrayList<>());
        }
    }
}
