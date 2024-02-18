import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setEmail("help@xyz.com");
        contact.setName("XYZ Support");
        contact.setUrl("http://www.xyz.com");
        return new OpenAPI()
                .info(new Info().title("Employee APIs").description("Description here..")
                        .version("1.0").contact(contact)
                        .license(new License().name("2015-2021 XYZ LLC All Rights Reserved")));
    }
}