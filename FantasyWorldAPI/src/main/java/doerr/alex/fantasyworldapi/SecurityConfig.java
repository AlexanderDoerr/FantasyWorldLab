package doerr.alex.fantasyworldapi;

import doerr.alex.fantasyworldapi.BLL.UserBLL;
import org.apache.coyote.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserBLL ub = new UserBLL();


    public class NoPopupBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
                throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password(passwordEncoder().encode("test"))
//                .authorities("USER");
        auth.userDetailsService(inMemoryUserDetailsManager());
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager()
    {
        List<UserDetails> userDetailsList = new ArrayList<>();
//        userDetailsList.add(User.withUsername("admin").password(passwordEncoder().encode("test"))
//                .roles("ADMIN").build());

        for (doerr.alex.fantasyworldapi.model.User user : ub.selectUsers()) {
            userDetailsList.add(User.withUsername(user.getUsername()).password(passwordEncoder().encode(user.getPassword()))
                    .roles(user.getRole()).build());
        }

        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login/logincheck").permitAll() //Allow access to index.html
                .antMatchers("/index.html").permitAll() //Allow access to index.html
                .antMatchers(HttpMethod.GET, "/person").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/person").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.GET, "/person/{id}").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/person/{id}").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.GET, "/planet").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/planet").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.GET, "/planet/{id}").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/planet/{id}").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.GET, "/starship").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/starship").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.GET, "/starship/{id}").permitAll()// all allowed to this endpoint
                .antMatchers(HttpMethod.OPTIONS, "/starship/{id}").permitAll()// all allowed to this endpoint
                .antMatchers("/user/**").hasRole("ADMIN")
                .anyRequest().authenticated() //All other requests must be authenticated
                .and().csrf().disable()// Disable cross site request forgery checking
                .sessionManagement().disable() //Disable session management
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint())
                .and().cors();

        //.antMatchers("/path/*") //Single path with 1 sub folder, ex) /path/{id}
        //.antMatchers("/path/*", RequestMethod.GET) //specify the http protocol
        //.antMatchers("/path/**") //recursively applies to all sub folders, ex)
        //.antMatchers(HttpMethod.GET, "/api/ships/*", "/api/ports/*").permitAll() //multiple paths
        //.antMatchers(HttpMethod.GET, "/api/login").authenticated()
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    };

    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ...
                .securityContext((securityContext) -> securityContext
                        .securityContextRepository(new RequestAttributeSecurityContextRepository())
                );
        return http.build();
    }
}

