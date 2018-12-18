package garine.learn.activity.web.intercept;

import garine.learn.activity.web.api.UserCoreServiceRemote;
import garine.learn.activity.web.controller.BaseController;
import garine.learn.common.annotations.Anoymous;
import garine.learn.common.constants.GpmallWebConstant;
import garine.learn.common.utils.CookieUtil;
import garine.learn.user.api.dto.CheckAuthRequest;
import garine.learn.user.api.dto.CheckAuthResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;


public class TokenIntercepter extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN="access_token";

    UserCoreServiceRemote userCoreServiceRemote;

    public TokenIntercepter(UserCoreServiceRemote userCoreServiceRemote) {
        this.userCoreServiceRemote = userCoreServiceRemote;
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Object bean=handlerMethod.getBean();

        if(isAnoymous(handlerMethod)){
            return true;
        }
        if(!(bean instanceof BaseController)){
            throw new RuntimeException("must extend basecontroller");
        }
        String token= CookieUtil.getCookieValue(request,ACCESS_TOKEN);
        boolean isAjax=CookieUtil.isAjax(request);
        if(StringUtils.isEmpty(token)){
            //没token，直接返回回到登录业或者注册页面
            if(isAjax){
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("{\"code\":\"-1\",\"msg\":\"error\"}");
                return false;
            }
            String uri = request.getRequestURI();
            if ("/loginPage".equals(uri) || "/registerPage".equals(uri)){
                return true;
            }else {
                response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
            }
            return false;
        }
        CheckAuthRequest checkAuthRequest=new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse=userCoreServiceRemote.validToken(checkAuthRequest);
        if("000000".equals(checkAuthResponse.getCode())){
            BaseController baseController=(BaseController)bean;
            baseController.setUid(checkAuthResponse.getUid());
            super.preHandle(request, response, handler);
        }
        if(isAjax){
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\""+checkAuthResponse.getCode()+"\"" +
                    ",\"msg\":\""+checkAuthResponse.getMsg()+"\"}");
            return false;
        }
        //token校验通过，重定向到access页面
        response.sendRedirect(GpmallWebConstant.GPMALL_ACTIVITY_ACCESS_URL);
        return false;
    }

    private boolean isAnoymous(HandlerMethod handlerMethod){
        Object bean=handlerMethod.getBean();
        Class clazz=bean.getClass();
        if(clazz.getAnnotation(Anoymous.class)!=null){
            return true;
        }
        Method method=handlerMethod.getMethod();
        return method.getAnnotation(Anoymous.class)!=null;
    }
}
