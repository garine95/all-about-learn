package garine.learn.TestProModel.monitor;

import com.google.common.collect.Lists;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 任务单状态监控器组
 * @author zhoujy
 * @date 2018年09月13日
 **/
@Component
public class MonitorGroup implements Monitor,InitializingBean,ApplicationContextAware {
    private List<Monitor> wokerMonitors = Lists.newArrayList();

    private ApplicationContext context;
    @Override
    public void doAlert() {
        wokerMonitors.forEach(Monitor::doAlert);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initMonitors();
    }

    private void initMonitors(){
        Map<String, Monitor> matchingBeans =
                BeanFactoryUtils.beansOfTypeIncludingAncestors(this.context, Monitor.class, true, false);
        if (!matchingBeans.isEmpty()) {
            this.wokerMonitors = new ArrayList<>(matchingBeans.values());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
