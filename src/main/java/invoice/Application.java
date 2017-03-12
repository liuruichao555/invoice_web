package invoice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableRedisHttpSession
@ServletComponentScan
public class Application {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @RequestMapping("/")
    public String greeting() {
        return "Hello World!";
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
    @Configuration
    public class WebMvcConfigurer extends WebMvcConfigurerAdapter {

        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new HandlerInterceptorAdapter() {
                @Override
                public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                         Object handler) throws Exception {
                    if(request.getSession().getAttribute("user") == null){
                        logger.info("用户没有登录！");
                        response.sendRedirect("/login");
                        return false;
                    }
                    return true;
                }
            }).addPathPatterns("/invoice/*");
        }
    }
}
