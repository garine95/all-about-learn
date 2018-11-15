package design.mode.adapter;

import java.util.ArrayList;
import java.util.List;

public class AdapterTest {
    public static void main(String[] args) {
        VGASignal vgaSignal = new VGASignal();
        vgaSignal.setVgaContent("vgaInfo::");
        new TVScreen().showSignal(vgaSignal);
        System.out.println();
        List<String> hdmiContent = new ArrayList<>();
        hdmiContent.add("hdmihead::");
        hdmiContent.add("hdmibody::");
        hdmiContent.add("hdmitail::");
        HDMISignal hdmiSignal = new HDMISignal();
        hdmiSignal.setHdmiContent(hdmiContent);

        new SignalTransferAdapter().showSignalForHDMI(hdmiSignal);
    }
}
