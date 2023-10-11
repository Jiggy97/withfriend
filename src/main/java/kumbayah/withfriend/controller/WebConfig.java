package kumbayah.withfriend.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("https://api.iamport.kr/")  // 허용할 도메인
//                .allowedMethods("GET")  // 허용할 HTTP 메서드
//                .allowedHeaders("Content-Type", "Authorization") // 허용할 헤더
//                .allowCredentials(true); // 클라이언트 쿠키 및 인증 정보 전달
    }
}
