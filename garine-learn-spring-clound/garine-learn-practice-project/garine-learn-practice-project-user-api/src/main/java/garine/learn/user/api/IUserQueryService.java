package garine.learn.user.api;


import garine.learn.user.api.dto.UserQueryRequest;
import garine.learn.user.api.dto.UserQueryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("userQuery")
public interface IUserQueryService {


    /**
     * 根据用户id来查询用户信息
     * @param request
     * @return
     */
    @GetMapping("/getUserById")
    UserQueryResponse getUserById(UserQueryRequest request);

}
