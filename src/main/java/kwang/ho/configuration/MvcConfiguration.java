package kwang.ho.configuration;

import kwang.ho.interceptor.LoggerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor())
                .excludePathPatterns("/css/**", "/fonts/**", "/plugin/**", "/scripts/**");
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setDefaultEncoding("UTF-8"); // 파일 인코딩 설정
        multipartResolver.setMaxUploadSizePerFile(10 * 1024 * 1024); // 파일당 업로드 크기 제한 (5MB)
        return multipartResolver;
    }

    /* 로컬저장소 이미지 웹으로 보여주기 위한 경로 지정 */
    /* http://localhost:8080/upload/220915/~~.PNG 등*/
    @Value("${resource.path}")
    private String resourcePath;

    @Value("${upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler(uploadPath).addResourceLocations(resourcePath);
    }

}
