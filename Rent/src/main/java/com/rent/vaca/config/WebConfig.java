package com.rent.vaca.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation") // 경고 제거 (IDE에서 뜰 수 있음)
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/img/acco/**")
        	.addResourceLocations("file:///C:/Users/MYCOM/git/team/Rent/src/main/webapp/resources/img/acco/");
        registry.addResourceHandler("/resources/img/room/**")
        	.addResourceLocations("file:///C:/Users/MYCOM/git/team/Rent/src/main/webapp/resources/img/room/");
        registry.addResourceHandler("/resources/img/biz/**")
        .addResourceLocations("file:///C:/Users/MYCOM/git/team/Rent/src/main/webapp/resources/img/biz/");

    }
}