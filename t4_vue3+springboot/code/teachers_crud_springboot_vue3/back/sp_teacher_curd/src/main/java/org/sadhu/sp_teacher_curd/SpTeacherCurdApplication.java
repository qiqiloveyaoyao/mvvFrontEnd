package org.sadhu.sp_teacher_curd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpTeacherCurdApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpTeacherCurdApplication.class, args);
    }

    // 删除这个重复的 corsConfigurer 方法
    // @Bean
    // public WebMvcConfigurer corsConfigurer() {
    //     return new WebMvcConfigurer() {
    //         @Override
    //         public void addCorsMappings(CorsRegistry registry) {
    //             registry.addMapping("/**")
    //                     .allowedOrigins("*")
    //                     .allowedMethods("GET", "POST", "PUT", "DELETE")
    //                     .allowedHeaders("*");
    //         }
    //     };
    // }
}
