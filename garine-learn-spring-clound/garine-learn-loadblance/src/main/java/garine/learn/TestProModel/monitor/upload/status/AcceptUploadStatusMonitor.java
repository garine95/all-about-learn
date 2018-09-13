package garine.learn.TestProModel.monitor.upload.status;

import com.fpx.fb4.wms.schedule.model.entity.OTaskAlert;
import com.fpx.fb4.wms.schedule.service.OutboundConsignmentService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhoujy
 * @date 2018年09月13日
 **/
@Component
public class AcceptUploadStatusMonitor extends AbstractUploadStatusMonitor implements InitializingBean,ApplicationContextAware {

    @Resource
    private OutboundConsignmentService outboundConsignmentService;

    private ApplicationContext applicationContext;
    @Override
    List<OTaskAlert> findMonitoredObject() {
        return outboundConsignmentService.findUnpushAcceptOrders();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initProcessors(this.applicationContext, "acceptStatus");
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}
