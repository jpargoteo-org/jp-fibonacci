package es.jpargoteo.fibonacci.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FibonacciOpenAPIDocumentationConfig {

    @Bean
    public OpenAPI configure(@Value("$application-version") String version) {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title("Fibonacci's API")
                        .description("This is the " + version + " version of the Fibonacci service offered by Bahia Software so you can calculate beautiful spirals thanks to the golden ratio.")
                        .termsOfService("https://bahiasoftware.es/web/aviso_legal.php")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .contact(new Contact()
                                .email("jpargoteo@gmail.com")));
    }

    class SwaggerUiConfiguration implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.
                    addResourceHandler("/swagger-ui/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                    .resourceChain(false);
        }

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/swagger-ui/").setViewName("forward:/swagger-ui/index.html");
        }
    }

}
