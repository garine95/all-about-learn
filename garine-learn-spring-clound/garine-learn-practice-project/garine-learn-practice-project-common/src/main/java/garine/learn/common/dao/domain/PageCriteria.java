package garine.learn.common.dao.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询条件实体
 *
 * @author wugx
 */
@Data
public class PageCriteria implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页数
     */
    private Integer pageNum;

    /**
     * 页大小
     */
    private Integer pageSize;


}
