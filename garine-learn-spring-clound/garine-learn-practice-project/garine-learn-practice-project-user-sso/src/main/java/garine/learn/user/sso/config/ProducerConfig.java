package garine.learn.user.sso.config;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

@Configuration
public class ProducerConfig {

    @Bean
    public Producer producer(){
        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.border.color", "105,179,90");
        properties.put("kaptcha.textproducer.font.size", 45);
        properties.put("kaptcha.textproducer.font.color", "blue");
        properties.put("kaptcha.image.width", 125);
        properties.put("kaptcha.image.height", 45);
        properties.put("kaptcha.textproducer.char.length", 4);
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
