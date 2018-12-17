package garine.learn.activity.service.dal.entitys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActDrawAward implements Serializable{

    private static final long serialVersionUID = 1765800142109468438L;

    private Integer id;

    private String awardName;

    private String awardInfo;

    private Integer awardType;

    private Date createTime;

    @Override
    public String toString() {
        return "ActivityDrawAward{" +
                "id=" + id +
                ", awardName='" + awardName + '\'' +
                ", awardInfo='" + awardInfo + '\'' +
                ", awardType=" + awardType +
                ", createTime=" + createTime +
                '}';
    }
}