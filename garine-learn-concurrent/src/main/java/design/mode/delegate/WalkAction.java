package design.mode.delegate;

public class WalkAction implements Action {
    @Override
    public void doAction(String command) {
        System.out.println("命令:" + command +", do walk");
    }
}
