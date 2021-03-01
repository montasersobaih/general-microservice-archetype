package ${package}.api.general;

import ${package}.model.command.AddNewGeneralCommand;
import ${package}.model.command.UpdateGeneralCommand;
import ${package}.model.dto.GeneralsDTO;
import ${package}.model.dto.NewGeneralDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/generals")
public interface GeneralRest extends GeneralValidation {

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    ResponseEntity<GeneralsDTO> getAllGenerals(
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    );

    @GetMapping(
            path = "/{general-id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @Override
    ResponseEntity<GeneralsDTO> getGeneralById(
            @PathVariable("general-id") String generalId
    );

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @Override
    ResponseEntity<NewGeneralDTO> addNewGeneral(
            @RequestBody AddNewGeneralCommand command
    );

    @PutMapping(
            path = "/{general-id}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @Override
    ResponseEntity<NewGeneralDTO> updateGeneral(
            @PathVariable("general-id") String generalId,
            @RequestBody UpdateGeneralCommand command
    );

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Override
    ResponseEntity<Void> deleteAllGenerals(
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo
    );

    @DeleteMapping(
            path = "/{general-id}",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @Override
    ResponseEntity<Void> deleteGeneral(
            @PathVariable("general-id") String generalId
    );
}
