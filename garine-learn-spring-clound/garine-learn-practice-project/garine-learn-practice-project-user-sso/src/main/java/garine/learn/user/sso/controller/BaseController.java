package garine.learn.user.sso.controller;


public class BaseController {

    static  ThreadLocal<String> uidThreadLocal=new ThreadLocal<>();

    public void setUid(String uid){
        uidThreadLocal.set(uid);
    }
    public String getUid(){
      return  uidThreadLocal.get();
    }

}
