package garine.learn.activity.service.dal.entitys;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ActDrawAwardItem implements Serializable{
    private static final long serialVersionUID = -2578396302905051481L;
    private Integer id;

    private String itemName;

    private Integer totalNum;

    private Float probability;

    private Integer status;

    private Integer awardId;

    private Integer dayTotalNum;

    private Date createTime;

    private Integer level;

    @Override
    public String toString() {
        return "ActivityDrawAwardItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", totalNum=" + totalNum +
                ", probability=" + probability +
                ", status=" + status +
                ", awardId=" + awardId +
                ", dayTotalNum=" + dayTotalNum +
                ", createTime=" + createTime +
                ", level=" + level +
                '}';
    }
}