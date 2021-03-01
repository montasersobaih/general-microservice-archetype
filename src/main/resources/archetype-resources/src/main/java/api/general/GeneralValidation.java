package ${package}.api.general;

import ${package}.model.command.AddNewGeneralCommand;
import ${package}.model.command.UpdateGeneralCommand;
import ${package}.model.dto.GeneralsDTO;
import ${package}.model.dto.NewGeneralDTO;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

public interface GeneralValidation {

    ResponseEntity<GeneralsDTO> getAllGenerals(
            LocalDate dateFrom,
            LocalDate dateTo,
            Integer offset,
            Integer limit
    );

    ResponseEntity<GeneralsDTO> getGeneralById(
            String generalId
    );

    ResponseEntity<NewGeneralDTO> addNewGeneral(
            AddNewGeneralCommand command
    );

    ResponseEntity<NewGeneralDTO> updateGeneral(
            String generalId,
            UpdateGeneralCommand command
    );

    ResponseEntity<Void> deleteAllGenerals(
            LocalDate dateFrom,
            LocalDate dateTo
    );

    ResponseEntity<Void> deleteGeneral(
            String generalId
    );
}
