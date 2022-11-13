package doerr.alex.fantasyworldapi;

import doerr.alex.fantasyworldapi.BLL.PersonBLL;
import doerr.alex.fantasyworldapi.BLL.PlanetBLL;
import doerr.alex.fantasyworldapi.BLL.StarshipBLL;
import doerr.alex.fantasyworldapi.model.Person;
import doerr.alex.fantasyworldapi.model.Planet;
import doerr.alex.fantasyworldapi.model.Starship;
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
                //registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }
        };
    }

}
