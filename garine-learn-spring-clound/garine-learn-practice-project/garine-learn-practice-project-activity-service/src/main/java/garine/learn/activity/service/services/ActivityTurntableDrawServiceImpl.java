package garine.learn.activity.service.services;


import garine.learn.activity.api.commons.ResultResp;
import garine.learn.activity.api.commons.ReturnCodeEnum;
import garine.learn.activity.api.draw.ActivityTurntableDrawService;
import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.api.draw.bean.AwardDrawRecordBean;
import garine.learn.activity.service.dal.entitys.ActDrawAward;
import garine.learn.activity.service.dal.entitys.ActDrawAwardItem;
import garine.learn.activity.service.dal.entitys.ActivityDrawNum;
import garine.learn.activity.service.dal.entitys.ActivityDrawRecord;
import garine.learn.activity.service.dal.persistence.ActDrawAwardItemMapper;
import garine.learn.activity.service.dal.persistence.ActDrawAwardMapper;
import garine.learn.activity.service.dal.persistence.ActDrawNumMapper;
import garine.learn.activity.service.dal.persistence.ActDrawRecordMapper;
import garine.learn.activity.service.services.processor.ActivityDrawContext;
import garine.learn.activity.service.services.processor.ActivityTurntableDrawProxy;
import garine.learn.activity.service.services.processor.constants.ActivityConstants;
import garine.learn.activity.service.services.processor.constants.DrawContants;
import garine.learn.activity.service.services.processor.constants.RedisKeyManager;
import garine.learn.activity.service.services.processor.exception.RewardException;
import garine.learn.user.api.IUserQueryService;
import garine.learn.user.api.dto.UserQueryRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 * 抽奖主代码
 */
@RestController
public class ActivityTurntableDrawServiceImpl implements ActivityTurntableDrawService {
    private static final Logger logger = LoggerFactory.getLogger(ActivityTurntableDrawServiceImpl.class);

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ActDrawAwardItemMapper actDrawAwardItemMapper;

    @Autowired
    ActDrawAwardMapper actDrawAwardMapper;

    @Autowired
    ActDrawNumMapper actDrawNumMapper;

    @Autowired
    ActivityTurntableDrawProxy activityTurntableDrawProxy;

    @Autowired
    IUserQueryService userQueryService;

    @Autowired
    ActDrawRecordMapper actDrawRecordMapper;

    @PostConstruct
    public void initDrawData() throws Exception {
        logger.info("开始初始化奖品数据");
        //获得所有奖项：一等奖、二等奖、三等奖..
        List<ActDrawAwardItem> awardItems=this.actDrawAwardItemMapper.queryAwardItem();
        if(awardItems==null||awardItems.isEmpty()){
            throw new Exception("奖品数据未创建，初始化失败");
        }
        redisTemplate.opsForValue().set(DrawContants.DRAW_ITEM,awardItems);
        redisTemplate.expire(DrawContants.DRAW_ITEM,DrawContants.EXPIRE_TIME, TimeUnit.DAYS);
        for(ActDrawAwardItem awardItem:awardItems){//遍历所有奖项获得每个奖项对应的奖品
            ActDrawAward activityDrawAward =actDrawAwardMapper.queryAwardById(awardItem.getAwardId());
            redisTemplate.opsForValue().set(RedisKeyManager.getAwardRedisKey(activityDrawAward), activityDrawAward);
            redisTemplate.expire(RedisKeyManager.getAwardRedisKey(activityDrawAward),DrawContants.EXPIRE_TIME,TimeUnit.DAYS);
            //TODO 如果奖品是有数量限制的，比如京东券 ，那么针对这类的奖品需要放到队列中
//            redisTemplate.opsForList().leftPush()
        }
    }

    public ResultResp<AwardDrawRecordBean> doDraw(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq) {
        ResultResp<AwardDrawRecordBean> recordBeanResultResp=new ResultResp<AwardDrawRecordBean>();
        try{
            checkDrawParams(activityTurntableDrawReq);//检查请求参数

            ActivityDrawContext activityDrawContext=new ActivityDrawContext();
            activityDrawContext.setActivityTurntableDrawReq(activityTurntableDrawReq);
            UserQueryRequest userQueryRequest=new UserQueryRequest();
            userQueryRequest.setUid(activityTurntableDrawReq.getUid());
            activityDrawContext.setCurrentUser(userQueryService.getUserById(userQueryRequest));

            activityTurntableDrawProxy.doDrawForProxy(activityDrawContext);

            AwardDrawRecordBean awardDrawRecordBean=new AwardDrawRecordBean();
            awardDrawRecordBean.setLevel(activityDrawContext.getActivityDrawAwardItem().getLevel());
            awardDrawRecordBean.setName(activityDrawContext.getCurrentUser().getRealName());
            awardDrawRecordBean.setUid(activityDrawContext.getActivityTurntableDrawReq().getUid());
            recordBeanResultResp.setResult(awardDrawRecordBean);
            //设置返回的信息
            recordBeanResultResp.setReturnCodeEnum(ReturnCodeEnum.SUCCESS);

        }catch (Exception e) {
            logger.error("抽奖失败!", e);
            recordBeanResultResp.setReturnCodeEnum(ReturnCodeEnum.SYSTEM_ERROR);
        } finally {
            logger.info("抽奖请求{},响应{}", activityTurntableDrawReq, recordBeanResultResp);
            //清除正在抽奖标志cache
            cleanRedisCache(activityTurntableDrawReq);
        }
        return recordBeanResultResp;
    }


    private void checkDrawParams(ActivityTurntableDrawReq activityTurntableDrawReq){
        if ((null == activityTurntableDrawReq.getUid())) {
            throw new RewardException("uid不能为空");
        }
        Object value=redisTemplate.opsForValue().get(RedisKeyManager.getDrawingRedisKey(activityTurntableDrawReq));
        if(value!=null&&DrawContants.ISEXIST.EXIST.getValue().equals(value)){
            throw new RewardException("上一次抽奖还未结束");
        }
        //设置正在抽奖
        redisTemplate.opsForValue().set(RedisKeyManager.getDrawingRedisKey(activityTurntableDrawReq),DrawContants.ISEXIST.EXIST.getValue());
        redisTemplate.expire(RedisKeyManager.getDrawingRedisKey(activityTurntableDrawReq),DrawContants.EXPIRE_TIME,TimeUnit.DAYS);
    }

    private void cleanRedisCache(ActivityTurntableDrawReq activityTurntableDrawReq) {
        try {
            redisTemplate.delete(RedisKeyManager.getDrawingRedisKey(activityTurntableDrawReq));
        } catch (Exception e) {
            logger.error("清除key[" +RedisKeyManager.getDrawingRedisKey(activityTurntableDrawReq)+ "]异常", e);
        }
    }

    /**
     * 查询转盘抽奖剩余抽奖次数
     *
     * @param activityTurntableDrawReq 抽奖次数
     * @return 抽奖次数列表及返回码信息
     */
    @Override
    public Integer queryRemainDrawCount(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq) {
        try {
            ActivityDrawNum drawNum = actDrawNumMapper.queryDrawNumForUid(activityTurntableDrawReq.getUid());
            if(drawNum!=null && drawNum.getMaxNumber() > 0){
                return drawNum.getMaxNumber()-drawNum.getNowNumber();
            }
        } catch (Exception e) {
            logger.error("查询抽奖次数异常", e);
            ActivityConstants.IsAllowDrawCode.LUCK_DRAW_NOT_KNOW_DRAW.getValue();
        }
        return ActivityConstants.IsAllowDrawCode.LUCK_DRAW_NOT_ALLOW.getValue();
    }

    @Override
    public List<AwardDrawRecordBean> queryAwardDrawRecord(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq){
        if ((null == activityTurntableDrawReq.getUid())) {
            throw new RuntimeException("uid不能为空");
        }
        List<AwardDrawRecordBean> awardDrawRecordBeans=new ArrayList<>();
        List<ActivityDrawRecord> activityDrawRecords =actDrawRecordMapper.queryDrawRecordList();
        for(int i = 0; i< activityDrawRecords.size(); i++){
            AwardDrawRecordBean awardDrawRecordBean=new AwardDrawRecordBean();
            awardDrawRecordBean.setId(activityDrawRecords.get(i).getId());
            awardDrawRecordBean.setLevel(activityDrawRecords.get(i).getLevel());
            awardDrawRecordBean.setMobile(activityDrawRecords.get(i).getMobile());
            awardDrawRecordBean.setName(activityDrawRecords.get(i).getAwardName());
            awardDrawRecordBean.setUid(activityDrawRecords.get(i).getUid());
            awardDrawRecordBeans.add(awardDrawRecordBean);
        }
        return awardDrawRecordBeans;
    }

}
