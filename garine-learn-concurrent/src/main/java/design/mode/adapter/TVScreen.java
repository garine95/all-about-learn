package design.mode.adapter;

public class TVScreen {
    public void showSignal(VGASignal vgaSignal){
        System.out.println(vgaSignal.getVgaContent());
    }
}
