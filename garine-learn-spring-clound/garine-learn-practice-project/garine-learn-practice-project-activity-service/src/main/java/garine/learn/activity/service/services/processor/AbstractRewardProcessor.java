package garine.learn.activity.service.services.processor;



import garine.learn.activity.service.dal.entitys.ActivityDrawAward;
import garine.learn.activity.service.dal.entitys.ActivityDrawRecord;
import garine.learn.activity.service.dal.persistence.ActDrawRecordMapper;
import garine.learn.activity.service.services.processor.constants.DrawContants;
import garine.learn.activity.service.services.processor.exception.UnRewardException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


public abstract class AbstractRewardProcessor implements RewardProcessor<ActivityDrawContext> {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractRewardProcessor.class);

    @Autowired
    protected RedisTemplate redisTemplate;

    @Autowired
    ActDrawRecordMapper actDrawRecordMapper;

    private void beforeProcessor(ActivityDrawContext activityDrawContext) {
        //判断奖项当天发放总数以及整个活动过程中的总数：total_num、day_total_num
        if(validDayAwardNum(activityDrawContext)&&validTotalAwardNum(activityDrawContext)){
            ActivityDrawAward activityDrawAward =(ActivityDrawAward)redisTemplate.opsForValue().get(DrawContants.DRAW_AWARD+getAwardType()+":"+activityDrawContext.getActivityDrawAwardItem().getAwardId());
            //TODO 判断奖品的状态
            activityDrawContext.setActivityDrawAward(activityDrawAward);
        }else{
            throw new UnRewardException("当前奖项已达到当日最大限制");
        }

    }

    public ActivityDrawContext doReword(ActivityDrawContext activityDrawContext) {
        //验证奖品数量及获取奖品明细
        beforeProcessor(activityDrawContext);

        //处理发放奖品
        processor(activityDrawContext);

        //发送短信
        afterProcessor(activityDrawContext);

        return activityDrawContext;
    }

    /**
     * 保存中奖记录
     */
    protected void modifyAwardRecord(ActivityDrawContext activityDrawContext) {
        ActivityDrawRecord activityDrawRecord =new ActivityDrawRecord();
        activityDrawRecord.setAwardName(activityDrawContext.getActivityDrawAward().getAwardName());
        activityDrawRecord.setLevel(activityDrawContext.getActivityDrawAwardItem().getLevel());
        activityDrawRecord.setMobile(activityDrawContext.getCurrentUser().getMobile());
        activityDrawRecord.setUid(activityDrawContext.getActivityTurntableDrawReq().getUid());
        activityDrawRecord.setName(activityDrawContext.getCurrentUser().getRealName());
        actDrawRecordMapper.addActDrawRecord(activityDrawRecord);
    }


    /**
     * 校验每日奖品数量
     *
     * @return
     */
    protected boolean validDayAwardNum(ActivityDrawContext activityDrawContext) {
        //TODO 判断当天针对当前奖项发放的数量
        return true;
    }

    /**
     * 校验奖品数量
     *
     * @return
     */
    protected boolean validTotalAwardNum(ActivityDrawContext activityDrawContext) {
        //TODO 判断当前奖项总的发放数量
        return true;
    }


    /**
     * 发放对应的奖品
     *
     * @param activityDrawContext
     */
    protected abstract void processor(ActivityDrawContext activityDrawContext);


    /**
     * @param activityDrawContext
     */
    protected abstract void afterProcessor(ActivityDrawContext activityDrawContext);

    /**
     * 返回当前奖品类型
     *
     * @return
     */
    protected abstract int getAwardType();
}
