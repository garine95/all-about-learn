package garine.learn.activity.service.dal.persistence;




import garine.learn.activity.service.dal.entitys.ActivityDrawRecord;
import garine.learn.common.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActDrawRecordMapper extends BaseMapper<ActivityDrawRecord> {
	/**
	 * 添加抽奖记录
	 * */
	int addActDrawRecord(ActivityDrawRecord activityDrawRecord);


	/**
	 * 查询中奖记录信息
	 * @return 中奖记录列表（包含轮播的10条记录）
	 */
	List<ActivityDrawRecord> queryDrawRecordList();

}
