package by.naumenko.config;


import by.naumenko.config.handler.LoginFailHandler;
import by.naumenko.config.handler.LoginSuccessHandler;
import by.naumenko.config.handler.LogoutSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userService;
    private final LoginSuccessHandler loginSuccessHandler;
    private final LoginFailHandler loginFailHandler;
    private final LogoutSuccessHandler logoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                    .antMatchers("/admin").hasAuthority("ADMIN")
                    .antMatchers("/user").hasAnyAuthority("ADMIN", "USER")
                    .antMatchers("/hello").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailHandler)
                    .usernameParameter("login")
                    .passwordParameter("password");


//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                // указываем URL при удачном логауте
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/login?logout")
//                .logoutSuccessHandler(logoutSuccessHandler)
//                //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)
//                .and().csrf().disable();

//        http
//                // делаем страницу регистрации недоступной для авторизированных пользователей
//                .authorizeRequests()
//                //страницы аутентификаци доступна всем
//                .antMatchers("/login").anonymous()
//                // защищенные URL
//                .antMatchers("/hello").access("hasAnyRole('ADMIN')").anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

//    @Autowired
//    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService);
//    }
}
