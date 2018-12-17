package garine.learn.activity.service.dal.entitys;

import lombok.Data;

import java.util.Date;


@Data
public class ActivityDrawRecord {
    private Integer id;

    private Integer uid;

    private String name;

    private Integer level;

    private String mobile;

    private Date createTime;

    private String awardName;

}
