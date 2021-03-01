package ${package}.service.handler;

import com.core.springboot.command.CommandHandler;
import com.core.springboot.enumeration.LocaleCountriesIso;
import com.core.springboot.utils.ConverterUtils;
import ${package}.model.aggregation.CountryNotSupportedException;
import ${package}.model.aggregation.GeneralAggregation;
import ${package}.model.command.AddNewGeneralCommand;
import ${package}.repository.GeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AddNewGeneralCommandHandler implements CommandHandler<AddNewGeneralCommand, String> {

    private final GeneralRepository repository;

    @Autowired
    public AddNewGeneralCommandHandler(GeneralRepository repository) {
        this.repository = repository;
    }

    @Override
    public String handle(AddNewGeneralCommand command) {
        if (LocaleCountriesIso.isNotSupported(null)) {
            throw new CountryNotSupportedException();
        }

        GeneralAggregation aggregation = ConverterUtils.convert(command, GeneralAggregation.class);
//        aggregation.setProfileId(UUID.randomUUID().toString());
//        aggregation.setCreatedBy(getClass().getSimpleName());
//        aggregation.setCreationDate(LocalDateTime.now());

        Optional<GeneralAggregation> opSave = repository.save(aggregation);
        return opSave.map(GeneralAggregation::getId).orElse(null);
//        return opSave.map(GeneralAggregation::getProfileId).orElse(null);
    }
}
