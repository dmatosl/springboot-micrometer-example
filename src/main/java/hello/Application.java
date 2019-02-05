package hello;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value ;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;

@SpringBootApplication
public class Application {

    @Value("${application.name}")
    private String applicationName ;

    @Value("${build.version}")
    private String applicationVersion ; 

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {

        return registry -> {
            registry.config().commonTags("app_name", this.applicationName , "app_version", this.applicationVersion);
        };
    }
   
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }
    }

}
