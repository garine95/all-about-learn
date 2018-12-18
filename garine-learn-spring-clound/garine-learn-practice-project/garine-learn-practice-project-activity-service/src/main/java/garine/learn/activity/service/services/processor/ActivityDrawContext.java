package garine.learn.activity.service.services.processor;


import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.service.dal.entitys.ActivityDrawAward;
import garine.learn.activity.service.dal.entitys.ActivityDrawAwardItem;
import garine.learn.user.api.dto.UserQueryResponse;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ActivityDrawContext {

    private ActivityTurntableDrawReq activityTurntableDrawReq;

    private ActivityDrawAwardItem activityDrawAwardItem;

    private ActivityDrawAward activityDrawAward;

    private UserQueryResponse currentUser;

    public ActivityTurntableDrawReq getActivityTurntableDrawReq() {
        return activityTurntableDrawReq;
    }

    public void setActivityTurntableDrawReq(ActivityTurntableDrawReq activityTurntableDrawReq) {
        this.activityTurntableDrawReq = activityTurntableDrawReq;
    }

    public ActivityDrawAwardItem getActivityDrawAwardItem() {
        return activityDrawAwardItem;
    }

    public void setActivityDrawAwardItem(ActivityDrawAwardItem activityDrawAwardItem) {
        this.activityDrawAwardItem = activityDrawAwardItem;
    }

    public UserQueryResponse getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserQueryResponse currentUser) {
        this.currentUser = currentUser;
    }

    public ActivityDrawAward getActivityDrawAward() {
        return activityDrawAward;
    }

    public void setActivityDrawAward(ActivityDrawAward activityDrawAward) {
        this.activityDrawAward = activityDrawAward;
    }
}
