package garine.learn.user.sso.controller;

import garine.learn.common.annotations.Anoymous;
import garine.learn.common.constants.GpmallWebConstant;
import garine.learn.user.api.IUserCoreService;
import garine.learn.user.api.dto.UserLoginRequest;
import garine.learn.user.api.dto.UserLoginResponse;
import garine.learn.user.api.dto.UserRegisterRequest;
import garine.learn.user.api.dto.UserRegisterResponse;
import garine.learn.user.sso.controller.support.ResponseData;
import garine.learn.user.sso.controller.support.ResponseEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserController extends BaseController{

    @Resource
    DiscoveryClient discoveryClient;

    @Autowired
    IUserCoreService userCoreService;

   @Autowired
   KafkaTemplate kafkaTemplate;

    @GetMapping("/loginPage")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registerPage")
    public String registerPage(){
        return "register";
    }


    @Anoymous
    @PostMapping("/login")
    @ResponseBody
    public ResponseData doLogin(String username, String password,
                                HttpServletResponse response){
        ResponseData data=new ResponseData();
        UserLoginRequest request=new UserLoginRequest();
        request.setPassword(password);
        request.setUserName(username);
        UserLoginResponse userLoginResponse=userCoreService.login(request);
        response.addHeader("Set-Cookie",
                "access_token="+userLoginResponse.getToken()+";Path=/;HttpOnly");

        data.setMessage(userLoginResponse.getMsg());
        data.setCode(userLoginResponse.getCode());
        data.setData(GpmallWebConstant.GPMALL_ACTIVITY_ACCESS_URL);
        return data;
    }


    @PostMapping("/register")
    @Anoymous
    public @ResponseBody ResponseData register(String username, String password, String mobile){
        ResponseData data=new ResponseData();

        UserRegisterRequest request=new UserRegisterRequest();
        request.setMobile(mobile);
        request.setUsername(username);
        request.setPassword(password);
        try {
            UserRegisterResponse response = userCoreService.register(request);
            //添加抽奖机会
            kafkaTemplate.send("test",response.getUid());
            data.setMessage(response.getMsg());
            data.setCode(response.getCode());
        }catch(Exception e) {
            data.setMessage(ResponseEnum.FAILED.getMsg());
            data.setCode(ResponseEnum.FAILED.getCode());
        }
        return data;
    }

}
