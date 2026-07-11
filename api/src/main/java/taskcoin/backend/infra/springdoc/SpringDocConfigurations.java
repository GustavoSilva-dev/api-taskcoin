package taskcoin.backend.infra.springdoc;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("TaskCoin API")
                        .version("1.0")
                        .description("TaskCoin é uma API RESTful desenvolvida em Java 21 com Spring Boot 4.0.6 que funciona como um sistema de gamificação para crianças. A plataforma permite que responsáveis criem tarefas para seus filhos, que podem ganhar pontos (TaskCoins) ao completar essas tarefas. O sistema inclui um mecanismo de níveis baseado no desempenho das crianças e uma mecânica de ofensiva, que capta os dias sequenciais de realização de tarefas.")
                );
    }
}
