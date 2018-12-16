package garine.learn.user.api;


import garine.learn.user.api.dto.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/userCore")
public interface IUserCoreService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    @RequestMapping("/login")
    UserLoginResponse login(@RequestBody UserLoginRequest request);

    /**
     * 校验过程
     * @param request
     * @return
     */
    @RequestMapping("/validToken")
    CheckAuthResponse validToken(@RequestBody CheckAuthRequest request);

    /*
     * 注册
     */
    @RequestMapping("/register")
    UserRegisterResponse register(@RequestBody UserRegisterRequest userRegisterRequest);


    @GetMapping("/test")
    String testPr();
}
