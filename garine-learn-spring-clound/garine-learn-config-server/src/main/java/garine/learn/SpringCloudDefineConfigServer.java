package garine.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableConfigServer
public class SpringCloudDefineConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudDefineConfigServer.class, args);
    }

    @Bean
    public EnvironmentRepository newEnvironmentRepository(){
        return new EnvironmentRepository() {
            @Override
            public Environment findOne(String application, String profile, String label) {
                Environment environment =new Environment(application, profile);
                List<PropertySource> propertySourceList = environment.getPropertySources();
                Map<String, String> map = new HashMap<>();
                map.put("name", "garine-define");
                PropertySource propertySource = new PropertySource("map", map);
                propertySourceList.add(propertySource);
                return environment;
            }
        };
    }
}
