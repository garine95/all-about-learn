package garine.learn.activity.service.dal.persistence;




import garine.learn.activity.service.dal.entitys.ActivityDrawAwardItem;
import garine.learn.common.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActDrawAwardItemMapper extends BaseMapper<ActivityDrawAwardItem> {


    /**
     * 查询所有奖项
     * @return
     */
    List<ActivityDrawAwardItem> queryAwardItem();

}