package garine.learn.user.service.services;

import garine.learn.user.api.IUserQueryService;
import garine.learn.user.api.constants.ResponseCodeEnum;
import garine.learn.user.api.dto.UserQueryRequest;
import garine.learn.user.api.dto.UserQueryResponse;
import garine.learn.user.service.dal.entity.User;
import garine.learn.user.service.dal.persistence.UserMapper;
import garine.learn.user.service.exception.ExceptionUtil;
import garine.learn.user.service.exception.ServiceException;
import garine.learn.user.service.exception.ValidateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserQueryServiceImpl implements IUserQueryService {
    Logger logger= LoggerFactory.getLogger(UserQueryServiceImpl.class);

    @Autowired
    UserMapper userMapper;


    public UserQueryResponse getUserById(UserQueryRequest request) {
        UserQueryResponse response=new UserQueryResponse();
        try {
            beforeValidate(request);
            response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
            response.setCode(ResponseCodeEnum.SUCCESS.getCode());
            User user=userMapper.getUserByUid(request.getUid());
            if(user!=null){
                response.setAvatar(user.getAvatar());
                response.setSex(user.getSex());
                response.setRealName(user.getRealname());
                response.setMobile(user.getMobile());
                return response;
            }
            response.setCode(ResponseCodeEnum.QUERY_DATA_NOT_EXIST.getCode());
            response.setMsg(ResponseCodeEnum.QUERY_DATA_NOT_EXIST.getMsg());
        }catch (Exception e){
            ServiceException serviceException=(ServiceException) ExceptionUtil.handlerException4biz(e);
            response.setCode(serviceException.getErrorCode());
            response.setMsg(serviceException.getErrorMessage());
        }
        return response;
    }

    private void beforeValidate(UserQueryRequest request){
        if(null==request){
            throw new ValidateException("请求对象为空");
        }
        if(request.getUid()==null||request.getUid().intValue()==0){
            throw new ValidateException("用户id不能为空");
        }
    }
}
