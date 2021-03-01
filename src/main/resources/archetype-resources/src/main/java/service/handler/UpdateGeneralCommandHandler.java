package ${package}.service.handler;

import com.core.springboot.command.CommandHandler;
import com.core.springboot.utils.ConverterUtils;
import com.core.springboot.utils.ObjectUtil;
import ${package}.model.aggregation.GeneralAggregation;
import ${package}.model.command.UpdateGeneralCommand;
import ${package}.model.param.UpdateRecordParam;
import ${package}.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class UpdateGeneralCommandHandler implements CommandHandler<UpdateGeneralCommand, UpdateRecordParam> {

    private final GeneralRepository repository;

    @Autowired
    public UpdateGeneralCommandHandler(GeneralRepository repository) {
        this.repository = repository;
    }

    @Override
    public UpdateRecordParam handle(UpdateGeneralCommand command) {
        Optional<GeneralAggregation> optional = repository.getByGeneralId(null);

        GeneralAggregation aggregation;
        if (optional.isPresent()) {
            aggregation = optional.get();

            String repoId = aggregation.getId();
            ObjectUtil.copy(command, aggregation, false);
            aggregation.setId(repoId);

            aggregation.setLastUpdatedBy(getClass().getSimpleName());
            aggregation.setLastUpdatedDate(LocalDateTime.now());
        } else {
            aggregation = ConverterUtils.convert(command, GeneralAggregation.class);
            aggregation.setCreatedBy(getClass().getSimpleName());
            aggregation.setCreationDate(LocalDateTime.now());
        }

        Optional<GeneralAggregation> resultOptional = repository.save(aggregation);
        String generalId = resultOptional.map(GeneralAggregation::getId).orElse(null);
//        String profileId = resultOptional.map(GeneralAggregation::getProfileId).orElse(null);

        return new UpdateRecordParam(!optional.isPresent(), generalId);
    }
}
