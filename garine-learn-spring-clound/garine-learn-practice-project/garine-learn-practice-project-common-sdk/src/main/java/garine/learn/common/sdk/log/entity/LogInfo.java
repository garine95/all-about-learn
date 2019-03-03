package garine.learn.common.sdk.log.entity;

import lombok.Data;

/**
 * @author zhoujy
 * @date 2019年02月21日
 **/
@Data
public class LogInfo {
    /**
     * 日志类型
     */
    String logType;

    /**
     * 日志实体
     */
    String logContent;
}
