package garine.learn.TestProModel.monitor.upload.status;

import com.fpx.fb4.wms.schedule.model.entity.OTaskAlert;
import com.fpx.fb4.wms.schedule.monitor.Monitor;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujy
 * @date 2018年09月13日
 **/
public abstract class AbstractUploadStatusMonitor implements Monitor {
    List<AbstractUploadStatusProcessor> processes = Lists.newArrayList();

    @Override
    public void doAlert() {
        List<OTaskAlert> oTaskAlerts = findMonitoredObject();
        oTaskAlerts.forEach(this::invokeProcessors);
    }

    private void invokeProcessors(OTaskAlert oTaskAlert){
        for (AbstractUploadStatusProcessor processor : processes){
            if (processor.process(oTaskAlert)){
                break;
            }
        }
    }

    abstract List<OTaskAlert> findMonitoredObject();

    void initProcessors(ApplicationContext applicationContext, String processorType){
        Map<String, AbstractUploadStatusProcessor> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, AbstractUploadStatusProcessor.class, true, false);
        if (!matchingBeans.isEmpty()) {
            List<AbstractUploadStatusProcessor> allProcessors = new ArrayList<>(matchingBeans.values());
            allProcessors.forEach(p -> {
                List<Annotation> annotations = Lists.newArrayList(p.getClass().getAnnotations());
                annotations.forEach(a -> {
                    Map<String, Object> attris = AnnotationUtils.getAnnotationAttributes(a);
                });
            });
        }
    }
}
