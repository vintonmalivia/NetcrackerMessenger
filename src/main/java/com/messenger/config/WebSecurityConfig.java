package com.messenger.config;

import com.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolution;

import java.util.Map;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static abstract class ConfigurationData{
        private static final String NOT_FULLY_AUTHENTICATED_USER_ACCESS = "/registration";
        private static final String ADMIN_ACCESS = "/admin/**";
        private static final String ALL_USERS_ACCESS = "/";
        private static final String ALL_USERS_ACCESS_RESOURCES = "/resources/**";
        private static final String LOGIN_PAGE = "/login";
        private static final String LOGIN_SUCCESS_URL = "/";
        private static final String LOGOUT_SUCCESS_URL = "/";

    }

    private static abstract class Roles {
        private static final String ADMIN = "ADMIN";
    }

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()

                //Доступ только для не зарегистрированных пользователей
                .antMatchers(ConfigurationData.NOT_FULLY_AUTHENTICATED_USER_ACCESS).not().fullyAuthenticated()

                //Доступ только для пользователей с ролью Администратор
                .antMatchers(ConfigurationData.ADMIN_ACCESS).hasRole(Roles.ADMIN)

                //Доступ разрешен всем пользователей
                .antMatchers(ConfigurationData.ALL_USERS_ACCESS, ConfigurationData.ALL_USERS_ACCESS_RESOURCES).permitAll()

                //Все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()

                //Настройка для входа в систему
                .formLogin()
                .loginPage(ConfigurationData.LOGIN_PAGE)

                //Перенарпавление на главную страницу после успешного входа
                .defaultSuccessUrl(ConfigurationData.LOGIN_SUCCESS_URL)
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl(ConfigurationData.LOGOUT_SUCCESS_URL);
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

}