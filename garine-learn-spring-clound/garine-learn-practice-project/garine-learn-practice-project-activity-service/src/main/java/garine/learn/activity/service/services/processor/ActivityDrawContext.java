package garine.learn.activity.service.services.processor;


import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.service.dal.entitys.ActDrawAward;
import garine.learn.activity.service.dal.entitys.ActDrawAwardItem;
import garine.learn.user.api.dto.UserQueryResponse;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class ActivityDrawContext {

    private ActivityTurntableDrawReq activityTurntableDrawReq;

    private ActDrawAwardItem activityDrawAwardItem;

    private ActDrawAward activityDrawAward;

    private UserQueryResponse currentUser;

    public ActivityTurntableDrawReq getActivityTurntableDrawReq() {
        return activityTurntableDrawReq;
    }

    public void setActivityTurntableDrawReq(ActivityTurntableDrawReq activityTurntableDrawReq) {
        this.activityTurntableDrawReq = activityTurntableDrawReq;
    }

    public ActDrawAwardItem getActivityDrawAwardItem() {
        return activityDrawAwardItem;
    }

    public void setActivityDrawAwardItem(ActDrawAwardItem activityDrawAwardItem) {
        this.activityDrawAwardItem = activityDrawAwardItem;
    }

    public UserQueryResponse getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(UserQueryResponse currentUser) {
        this.currentUser = currentUser;
    }

    public ActDrawAward getActivityDrawAward() {
        return activityDrawAward;
    }

    public void setActivityDrawAward(ActDrawAward activityDrawAward) {
        this.activityDrawAward = activityDrawAward;
    }
}
