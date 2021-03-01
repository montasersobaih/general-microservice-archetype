package ${package}.service.query;

import com.core.springboot.model.QueryParam;
import com.core.springboot.utils.ConverterUtils;
import ${package}.model.aggregation.GeneralAggregation;
import ${package}.model.response.GeneralResponse;
import ${package}.repository.GeneralRepository;
import ${package}.util.FunctionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class QueryManager {

    private final GeneralRepository repository;

    @Autowired
    public QueryManager(GeneralRepository repository) {
        this.repository = repository;
    }

    public List<GeneralResponse> getAllGenerals(QueryParam param) {
        List<GeneralAggregation> aggregations = repository.getAll(param);

        List<GeneralResponse> transactions = new LinkedList<>();
        for (GeneralAggregation aggregation : aggregations) {
            transactions.add(ConverterUtils.convert(aggregation, GeneralResponse.class));
        }

        return transactions;
    }

    public Page<GeneralResponse> getAllGenerals(QueryParam param, PageRequest pageRequest) {
        Page<GeneralAggregation> aggregations = repository.getAll(param, pageRequest);

        List<GeneralResponse> transactions = new LinkedList<>();
        for (GeneralAggregation aggregation : aggregations.getContent()) {
            transactions.add(ConverterUtils.convert(aggregation, GeneralResponse.class));
        }

        return new PageImpl<>(transactions, aggregations.getPageable(), aggregations.getTotalElements());
    }

    public GeneralResponse getGeneral(QueryParam param) {
        Optional<GeneralAggregation> optional = repository.getGeneral(param);
        return optional.map(FunctionUtil::getGeneralResponse).orElse(null);
    }

    public GeneralResponse getGeneralById(String generalId) {
        Optional<GeneralAggregation> optional = repository.getByGeneralId(generalId);
        return optional.map(FunctionUtil::getGeneralResponse).orElse(null);
    }
}
