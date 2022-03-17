package com.messenger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private static abstract class URLPathOrPattern {
        private static final String LOGIN_URL_PATH_OR_PATTERN = "/login";
    }

    private static abstract class ViewName {
        private static final String LOGIN_VIEW_NAME = "login";
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController(URLPathOrPattern.LOGIN_URL_PATH_OR_PATTERN).setViewName(ViewName.LOGIN_VIEW_NAME);
    }
}