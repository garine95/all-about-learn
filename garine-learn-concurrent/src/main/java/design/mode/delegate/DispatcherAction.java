package design.mode.delegate;

import java.util.HashMap;
import java.util.Map;

public class DispatcherAction implements Action{
    static Map<String, Action>  actionMap;
    static {
        actionMap = new HashMap<>();
        actionMap.put("walk" ,new WalkAction());
        actionMap.put("run", new RunAction());
    }

    @Override
    public void doAction(String command) {
        actionMap.get(command).doAction(command);
    }
}
