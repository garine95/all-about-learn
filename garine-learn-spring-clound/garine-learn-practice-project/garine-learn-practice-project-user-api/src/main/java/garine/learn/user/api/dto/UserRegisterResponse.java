package garine.learn.user.api.dto;



import garine.learn.user.api.abs.AbstractResponse;

import java.io.Serializable;


public class UserRegisterResponse extends AbstractResponse implements Serializable{

    private static final long serialVersionUID = -7690077437344492561L;

    private Integer uid;

    private UserDTO userDTO;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
