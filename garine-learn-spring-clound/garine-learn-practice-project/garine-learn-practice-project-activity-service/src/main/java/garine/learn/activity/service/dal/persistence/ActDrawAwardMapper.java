package garine.learn.activity.service.dal.persistence;


import garine.learn.activity.service.dal.entitys.ActDrawAward;
import garine.learn.common.dao.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActDrawAwardMapper extends BaseMapper<ActDrawAward> {


    /**
     * 根据id获取奖品
     * @param id
     * @return
     */
    ActDrawAward queryAwardById(Integer id);
}