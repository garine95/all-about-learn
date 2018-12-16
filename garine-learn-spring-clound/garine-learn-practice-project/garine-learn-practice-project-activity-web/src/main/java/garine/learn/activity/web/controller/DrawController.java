package garine.learn.activity.web.controller;


import garine.learn.activity.api.commons.ResultResp;
import garine.learn.activity.api.draw.bean.ActivityTurntableDrawReq;
import garine.learn.activity.api.draw.bean.AwardDrawRecordBean;
import garine.learn.activity.web.api.ActivityTurntableDrawServiceRemote;
import garine.learn.activity.web.controller.support.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */


@Controller
public class DrawController extends BaseController{


    @Autowired
    ActivityTurntableDrawServiceRemote activityTurntableDrawServiceRemote;


    @GetMapping("/activityPage")
    public String registerPage(){
        return "activity";
    }


    @PostMapping("/doDraw")
    @ResponseBody
    public ResponseData doDraw(){
        ActivityTurntableDrawReq drawReq=new ActivityTurntableDrawReq();
        drawReq.setUid(Integer.parseInt(getUid()));
        ResultResp<AwardDrawRecordBean> resp= activityTurntableDrawServiceRemote.doDraw(drawReq);
        ResponseData data=new ResponseData();
        data.setCode(resp.getReturnCodeEnum().getCode());
        data.setMessage(resp.getReturnCodeEnum().getMsg());
        data.setData(resp.getResult());
        return data;
    }
}
