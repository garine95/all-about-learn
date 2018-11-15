/*
package garine.learn.TestProModel.monitor.upload.status;

import com.fpx.common.util.StringUtils;
import com.fpx.fb4.common.sdk.dingtalk.DingTalkComponent;
import com.fpx.fb4.wms.schedule.model.entity.OTaskAlert;
import com.fpx.fb4.wms.schedule.monitor.ProcessFor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;

*/
/**
 * @author zhoujy
 * @date 2018年09月13日
 **//*

@Configuration
public class ProcessorConfiguration {

    @ProcessFor("acceptStatus")
    @Order(Ordered.LOWEST_PRECEDENCE - 100)
    class AcceptStatusDefaultProcessor extends AbstractUploadStatusProcessor{
        @Resource
        private DingTalkComponent dingTalkComponent;

         @Override
         boolean isCanHandle(OTaskAlert oTaskAlert){
            return true;
         }

         @Override
         boolean isFilted(OTaskAlert oTaskAlert){
             if (StringUtils.isNotEmpty(oTaskAlert.getRefNo()) && oTaskAlert.getRefNo().startsWith("LF")){
                 return true;
             }
             return false;
         }

         @Override
         boolean doProcess(OTaskAlert oTaskAlert){

             return false;
         }
    }
}
*/
