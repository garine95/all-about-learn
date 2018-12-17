package garine.learn.activity.service.services.processor.constants;


import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.service.dal.entitys.ActDrawAward;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class RedisKeyManager {

    /**
     * 正在抽奖的key
     * @param activityTurntableDrawReq
     * @return
     */
    public static String getDrawingRedisKey(ActivityTurntableDrawReq activityTurntableDrawReq) {
        return DrawContants.DRAWING_PREFIX+String.valueOf(activityTurntableDrawReq.getUid());
    }

    public static String getAwardRedisKey(ActDrawAward activityDrawAward){
        return DrawContants.DRAW_AWARD+ activityDrawAward.getAwardType()+":"+ activityDrawAward.getId();
    }
}
