package garine.learn.activity.service.dal.persistence;


import garine.learn.activity.service.dal.entitys.ActivityDrawNum;
import garine.learn.common.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
@Mapper
public interface ActDrawNumMapper extends BaseMapper<ActivityDrawNum> {

    /**
     * 插入抽奖次数记录
     * @param ActivityDrawNum
     * @return
     */
    int inputDrawNumber(ActivityDrawNum ActivityDrawNum);

    /**
     * 更新已抽次数
     * @param uid
     * @return
     */
    int updateNowNumber(int uid);

    /**
     * 更新总的抽次数
     * @param uid
     * @return
     */
    int updateMaxNumber(int uid);

    /**
     * 查询指定用户的抽奖次数
     * @param uid
     * @return
     */
    ActivityDrawNum queryDrawNumForUid(int uid);
}
