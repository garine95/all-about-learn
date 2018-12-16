package garine.learn.activity.api.draw;



import garine.learn.activity.api.commons.ResultResp;
import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.api.draw.bean.AwardDrawRecordBean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
@RequestMapping("activityTurntableDrawService")
public interface ActivityTurntableDrawService {

    /**
     * 转盘抽奖
     *
     * @param activityTurntableDrawReq
     * @return
     */
    @RequestMapping("/doDraw")
    ResultResp<AwardDrawRecordBean> doDraw(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq);


    /**
     * 查询用户剩余抽奖次数
     * @param activityTurntableDrawReq
     * @return
     */
    @RequestMapping("/queryRemainDrawCount")
    Integer queryRemainDrawCount(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq);

    /**
     * 查询抽奖记录
     * @param activityTurntableDrawReq
     * @return
     */
    @RequestMapping("/queryAwardDrawRecord")
    List<AwardDrawRecordBean> queryAwardDrawRecord(@RequestBody ActivityTurntableDrawReq activityTurntableDrawReq);
}
