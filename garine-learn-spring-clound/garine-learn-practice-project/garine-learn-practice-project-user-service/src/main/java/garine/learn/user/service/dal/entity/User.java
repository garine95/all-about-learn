package garine.learn.user.service.dal.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String realname;
    private String avatar;

    private String mobile;
    private String sex;
    private int status;
    private Date createTime;

}
