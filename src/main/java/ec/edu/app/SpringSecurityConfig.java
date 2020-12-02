package ec.edu.app;

import ec.edu.app.auth.filter.JWTAuthentificationFilter;
import ec.edu.app.auth.filter.JWTAuthorizationFilter;
import ec.edu.app.auth.handler.LoginSucessHandler;
import ec.edu.app.auth.service.JWTService;
import ec.edu.app.services.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSucessHandler sucessHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Autowired
    private JpaUserDetailsService userDetailsService;

    @Autowired
    private JWTService jwtService;

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {

        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }


    //Proteger rutas con ACL, de acceso a ciertos usuarios
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/image/**", "/listar**").permitAll()
//                .antMatchers("/ver/**").hasAnyRole("USER")
//                .antMatchers("/uploads/**").hasAnyRole("USER")
//                .antMatchers("/form/**").hasAnyRole("ADMIN")
//                .antMatchers("/eliminar/**").hasAnyRole("ADMIN")
//                .antMatchers("/factura/**").hasAnyRole("ADMIN")
                .anyRequest().authenticated()
//                .and().formLogin().successHandler(sucessHandler)
//                .loginPage("/login")
//                .permitAll()
//                .and().logout().permitAll()
//                .and().exceptionHandling().accessDeniedPage("/error_403")
                .and()
                .addFilter(new JWTAuthentificationFilter(authenticationManager(), jwtService))
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService))
                .csrf().disable() //quitar esto en caso de usar sessiones
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //quitar esto en caso de usar sessiones
    }
}





//    insert into users(username, password, enable) values ('fernando', '$2a$10$HXM0fFcKNo4vh72LA.QHC.lknuGMTrzkYDH90aOG6ikqeRyvjDuse', 1);
//        insert into users(username, password, enable) values ('admin', '$2a$10$c1HYDyrWk72YpC.JUYQqle0o5EFdt2avXbBbcKIaApgcmI7a5Ewta', 1);
//
//        insert into authorities (user_id, authority) values (1, 'ROLE_USER');
//        insert into authorities (user_id, authority) values (2, 'ROLE_USER');
//        insert into authorities (user_id, authority) values (2, 'ROLE_ADMIN');