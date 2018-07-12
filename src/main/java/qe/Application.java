package qe;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import qe.filter.AdminAuthFilter;

import javax.servlet.FilterRegistration;


@SpringBootApplication
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
