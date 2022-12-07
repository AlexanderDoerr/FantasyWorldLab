package doerr.alex.fantasyworldapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FantasyWorldApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FantasyWorldApiApplication.class, args);
//        PersonBLL.createPerson(new Person("Larry", "Joker"));
    }
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer()
        {
            @Override
            public void addCorsMappings(CorsRegistry registry)
            {
                //This allows reguests from localHost: 8081
                registry.addMapping("/**").allowedOrigins("http://localhost:8081").allowedMethods("*");

                //This allows requests from any origin
//                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

}
