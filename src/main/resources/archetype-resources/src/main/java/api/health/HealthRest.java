package ${package}.api.health;

import ${package}.model.response.HealthResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = "/healthz")
public interface HealthRest {

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<HealthResponse> healthz();
}
