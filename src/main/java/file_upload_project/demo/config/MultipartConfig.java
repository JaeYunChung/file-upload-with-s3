package file_upload_project.demo.config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@Configuration
public class MultipartConfig {
  private final static long MAX_UPLOAD_SIZE = 10485760;

  @Bean
  public MultipartResolver multipartResolver(){
    return new StandardServletMultipartResolver();
  }
  @Bean
  public MultipartConfigElement multipartConfigElement(){
    MultipartConfigFactory factory = new MultipartConfigFactory();
    factory.setMaxRequestSize((DataSize.ofBytes(MAX_UPLOAD_SIZE)));
    factory.setMaxFileSize(DataSize.ofBytes(MAX_UPLOAD_SIZE));
    return factory.createMultipartConfig();
  }
}
