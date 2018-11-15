package design.mode.adapter;

import java.util.List;

/**
 * 为了不改变已有的稳定的逻辑，
 * 继承老系统稳定的逻辑，将新逻辑做适配老系统逻辑处理，然后再调用老系统接口。
 */
public class SignalTransferAdapter extends TVScreen{
    /**
     * 显示hdmi信号
     * @param hdmiSignal
     */
    public void showSignalForHDMI(HDMISignal hdmiSignal){
        List<String> hdmiContent =hdmiSignal.getHdmiContent();
        //hdmi信号的字符数组转换为vga信号的字符串，然后调用老系统的显示vga信号接口
        StringBuilder sb = new StringBuilder();
        hdmiContent.forEach(sb::append);
        VGASignal vgaSignal = new VGASignal();
        vgaSignal.setVgaContent(sb.toString());
        super.showSignal(vgaSignal);
    }
}
