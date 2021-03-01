package ${package}.configuration;

import com.core.springboot.command.CommandExecutor;
import ${package}.api.general.GeneralRest;
import ${package}.api.general.GeneralRestImpl;
import ${package}.api.health.HealthRest;
import ${package}.api.health.HealthRestImpl;
import ${package}.service.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class RestEndpointsConfig {

    @Bean
    public HealthRest healthRest() {
        return new HealthRestImpl();
    }

    @Bean
    @Autowired
    public GeneralRest transactionRest(
            CommandExecutor executor, QueryManager manager, HttpServletRequest servletRequest) {
        return new GeneralRestImpl(executor, manager, servletRequest);
    }
}
