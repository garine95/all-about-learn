package design.mode.proxy.cglib;

public class TargetAction {
    private String word = "default";

    public TargetAction(){

    }

    public TargetAction(String word){
        this.word = word;
    }

    public void move(){
        System.out.println("target doing move" + word);
    }
}
