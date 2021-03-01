package ${package}.api.health;

import ${package}.model.response.HealthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.http.ResponseEntity.status;

public class HealthRestImpl implements HealthRest {

    @Override
    public ResponseEntity<HealthResponse> healthz() {
        return status(HttpStatus.OK).contentType(APPLICATION_JSON_UTF8).body(new HealthResponse("OK"));
    }
}
