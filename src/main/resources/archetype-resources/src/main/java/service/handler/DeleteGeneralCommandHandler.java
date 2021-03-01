package ${package}.service.handler;

import com.core.springboot.command.CommandHandler;
import com.core.springboot.model.QueryParam;
import com.core.springboot.utils.ConverterUtils;
import ${package}.model.command.DeleteGeneralCommand;
import ${package}.model.param.QueryParamImpl;
import ${package}.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteGeneralCommandHandler implements CommandHandler<DeleteGeneralCommand, Boolean> {

    private final GeneralRepository repository;

    @Autowired
    public DeleteGeneralCommandHandler(GeneralRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean handle(DeleteGeneralCommand command) {
        QueryParam param = ConverterUtils.convert(command, QueryParamImpl.class);
        return repository.deleteAll(param);
    }
}
