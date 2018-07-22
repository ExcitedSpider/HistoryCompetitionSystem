package qe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import qe.filter.AdminAuthFilter;

import javax.servlet.FilterRegistration;


@SpringBootApplication
@EnableCaching
@MapperScan("qe.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public FilterRegistrationBean authFilterResitration(){
        FilterRegistrationBean registration = new FilterRegistrationBean(new AdminAuthFilter());
        registration.addUrlPatterns("/admin/*");
        return registration;
    }

}
