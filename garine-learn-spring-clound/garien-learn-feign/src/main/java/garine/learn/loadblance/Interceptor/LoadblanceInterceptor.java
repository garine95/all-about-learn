package garine.learn.loadblance.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * RequestTemplate执行请求时可以应用的自定义拦截器，拦截器逻辑包含了http请求逻辑
 */
public class LoadblanceInterceptor implements ClientHttpRequestInterceptor{
    private volatile Map<String, Set<String>> serviveUrlCache  =  new HashMap<>();

    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * 更新地址缓存
     */
    @Scheduled(fixedRate = 10000)
    public void pullServiceUrl(){
        Map<String, Set<String>> oldServiveUrlCache = this.serviveUrlCache;
        Map<String, Set<String>> newServiveUrlCache = new HashMap<>();
        discoveryClient.getServices().forEach(dto -> {
        Set<String> ins = discoveryClient.getInstances(dto).stream().map(serviceInstance -> {
            return (serviceInstance.isSecure() ? "https://":"http://") + serviceInstance.getHost() + ":" + serviceInstance.getPort();
        }).collect(Collectors.toSet());
        newServiveUrlCache.put(dto, ins);
        });
        this.serviveUrlCache = newServiveUrlCache;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String requestPath = request.getURI().toString().substring(1);
        String urlOrName = requestPath.split("/")[0];
        String targetUrl = "";
        if (urlOrName.startsWith("@")){
            targetUrl = urlOrName.substring(urlOrName.indexOf("@"));
        }else {
            Set<String> ins = this.serviveUrlCache.get(urlOrName);
            List<String> insUrls = new ArrayList<>(ins);//重新建立列表，防止并发过程被修改
            int index = new Random().nextInt(ins.size());
            String preTargetUrl = insUrls.get(index);
            targetUrl = preTargetUrl + requestPath.substring(requestPath.indexOf("/"));
        }
        URL url = new URL(targetUrl);
        URLConnection urlConnection = url.openConnection();
        //make a simple response
        ClientHttpResponse response = new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return 200;
            }

            @Override
            public String getStatusText() throws IOException {
                return "OK";
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                return urlConnection.getInputStream();
            }

            @Override
            public HttpHeaders getHeaders() {
                return new HttpHeaders();
            }
        };
        return response;
}
}
