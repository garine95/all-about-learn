package garine.learn.activity.service.dal.entitys;

import lombok.Data;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 * 抽奖资格
 */
@Data
public class ActivityDrawNum {
    private Integer id;
    private Integer uid;
    private String name;
    private Integer nowNumber;
    private Integer maxNumber;
    private String createDate;

    @Override
    public String toString() {
        return "ActivityDrawNum{" +
                "id=" + id +
                ", id=" + uid +
                ", name='" + name + '\'' +
                ", maxNumber=" + maxNumber +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
