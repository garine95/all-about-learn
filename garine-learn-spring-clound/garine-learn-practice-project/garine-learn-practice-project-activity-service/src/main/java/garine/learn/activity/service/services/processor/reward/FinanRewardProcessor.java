package garine.learn.activity.service.services.processor.reward;


import garine.learn.activity.service.services.processor.AbstractRewardProcessor;
import garine.learn.activity.service.services.processor.ActivityDrawContext;
import garine.learn.activity.service.services.processor.constants.DrawContants;
import org.springframework.stereotype.Service;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
@Service("financeRewardProcessor")
public class FinanRewardProcessor extends AbstractRewardProcessor {

    protected void processor(ActivityDrawContext activityDrawContext) {
        logger.info("用户:{},获得奖项:{}",activityDrawContext.getActivityTurntableDrawReq().getUid(),activityDrawContext.getActivityDrawAwardItem().getItemName());
        //TODO 发放奖品
        modifyAwardRecord(activityDrawContext); //保存记录
    }

    protected void afterProcessor(ActivityDrawContext activityDrawContext) {
        //发送短信
    }

    protected int getAwardType() {
        return DrawContants.AWARD_TYPE_ENUM.FINANCE.getValue();
    }
}
