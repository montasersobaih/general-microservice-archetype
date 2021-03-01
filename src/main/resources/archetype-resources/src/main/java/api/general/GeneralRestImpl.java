package ${package}.api.general;

import com.core.springboot.command.CommandExecutor;
import com.core.springboot.model.Pagination;
import com.core.springboot.model.QueryParam;
import com.core.springboot.model.dto.HateoasDTO;
import com.core.springboot.model.dto.MetadataDTO;
import com.core.springboot.utils.HateoasUtils;
import com.core.springboot.utils.MetadataUtils;
import ${package}.model.command.AddNewGeneralCommand;
import ${package}.model.command.DeleteGeneralCommand;
import ${package}.model.command.UpdateGeneralCommand;
import ${package}.model.dto.GeneralsDTO;
import ${package}.model.dto.NewGeneralDTO;
import ${package}.model.param.QueryParamImpl;
import ${package}.model.param.UpdateRecordParam;
import ${package}.model.response.GeneralResponse;
import ${package}.service.query.QueryManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class GeneralRestImpl implements GeneralRest {

    private final CommandExecutor executor;

    private final QueryManager queryManager;

    private final HttpServletRequest servletRequest;

    @Autowired
    public GeneralRestImpl(CommandExecutor executor, QueryManager queryManager, HttpServletRequest servletRequest) {
        this.executor = executor;
        this.queryManager = queryManager;
        this.servletRequest = servletRequest;
    }

    @Override
    public ResponseEntity<GeneralsDTO> getAllGenerals(
            LocalDate dateFrom,
            LocalDate dateTo,
            Integer offset,
            Integer limit
    ) {
        QueryParam queryParam = new QueryParamImpl();

        PageRequest pageRequest = Pagination.createPageRequest(offset, limit);

        Object[] params;
        Pagination pagination = null;
        List<GeneralResponse> result;
        if (Objects.isNull(pageRequest)) {
            result = queryManager.getAllGenerals(queryParam);
            params = new Object[]{dateFrom, dateTo};
        } else {
            Page<GeneralResponse> page = queryManager.getAllGenerals(queryParam, pageRequest);

            boolean hasNextPage = Pagination.hasNextPage(offset, limit);
            pagination = new Pagination(page.getTotalPages(), page.getTotalElements(), hasNextPage);

            params = new Object[]{dateFrom, dateTo, offset, limit};

            result = page.getContent();
        }

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        metadata.setPagination(pagination);

        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), params);

        GeneralsDTO dto = new GeneralsDTO(metadata, links, result);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
    }

    @Override
    public ResponseEntity<GeneralsDTO> getGeneralById(String generalId) {
        GeneralResponse result = queryManager.getGeneralById(generalId);

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), generalId);

        GeneralsDTO dto = new GeneralsDTO(metadata, links, result);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
    }

    @Override
    public ResponseEntity<NewGeneralDTO> addNewGeneral(AddNewGeneralCommand command) {
        String generalId = executor.execute(command);

        MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
        Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), generalId);

        NewGeneralDTO dto = new NewGeneralDTO(metadata, links, generalId);
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
    }

    @Override
    public ResponseEntity<NewGeneralDTO> updateGeneral(String generalId, UpdateGeneralCommand command) {
        UpdateRecordParam param = executor.execute(command);

        if (param.isNewRecord()) {
            MetadataDTO metadata = MetadataUtils.getParameters(servletRequest);
            Collection<HateoasDTO> links = HateoasUtils.preparingHateoas(getClass(), generalId, command);

            NewGeneralDTO dto = new NewGeneralDTO(metadata, links, param.getId());

            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON_UTF8).body(dto);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteAllGenerals(LocalDate dateFrom, LocalDate dateTo) {
        DeleteGeneralCommand command = new DeleteGeneralCommand();

        boolean isDone = executor.execute(command);
        return new ResponseEntity<>(isDone ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> deleteGeneral(String generalId) {
        DeleteGeneralCommand command = new DeleteGeneralCommand();

        boolean isDone = executor.execute(command);
        return new ResponseEntity<>(isDone ? HttpStatus.OK : HttpStatus.NO_CONTENT);
    }
}
