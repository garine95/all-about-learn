package garine.learn.TestProModel.monitor.upload.status;

import com.fpx.fb4.wms.schedule.model.entity.OTaskAlert;

/**
 * @author zhoujy
 * @date 2018年09月13日
 **/
public abstract class AbstractUploadStatusProcessor {
    boolean process(OTaskAlert oTaskAlert) {
        if (!isCanHandle(oTaskAlert)){
            return false;
        }
        if (!isFilted(oTaskAlert)){
            doProcess(oTaskAlert);
        }
        return true;
    }

    abstract boolean isCanHandle(OTaskAlert oTaskAlert);

    abstract boolean isFilted(OTaskAlert oTaskAlert);

    abstract boolean doProcess(OTaskAlert oTaskAlert);

}
