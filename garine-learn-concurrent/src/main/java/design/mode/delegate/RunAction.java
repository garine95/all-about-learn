package design.mode.delegate;

public class RunAction implements Action {
    @Override
    public void doAction(String command) {
        System.out.println("命令:" + command +", do run");
    }
}
