package garine.learn.restclient.proxy;

import garine.learn.restclient.annotation.RestClient;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class RestClientInvocationHandler implements InvocationHandler{
    private BeanFactory beanFactory;

    private RestClient restClient;

    public RestClientInvocationHandler(BeanFactory beanFactory, RestClient restClient){
        this.beanFactory = beanFactory;
        this.restClient = restClient;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(method, RequestMapping.class);
        //判断是否RequestMapping注解标注
        if (requestMapping != null){
            //只拿一个
            String uri = requestMapping.value()[0];
            //获取参数的所有注解
            Annotation[][]  parameterAns = method.getParameterAnnotations();
            int paramsCount = method.getParameterCount();
            String servicelUrl = StringUtils.hasText(restClient.serviceUrl()) ? "@" + restClient.serviceUrl() : restClient.applicationName();
            StringBuilder sb = new StringBuilder(servicelUrl + "/" + uri);
            for (int i=0;i<paramsCount;i++){
                Annotation[] p = parameterAns[i];
                RequestParam rep = (RequestParam)Stream.of(p).filter(val -> val instanceof RequestParam).findFirst().get();
                String paramName = rep.value();
                //拼接url
                String andchar = i == 0 ? "?" : "&";
                sb.append(andchar + paramName + "=" + args[i]);
            }
            RestTemplate restTemplate = (RestTemplate) beanFactory.getBean("lancedRestTemplate");
            return restTemplate.getForObject(sb.toString(), method.getReturnType());
        }
        return null;
    }
}
