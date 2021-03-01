package ${package}.repository;

import com.core.springboot.model.QueryParam;
import com.core.springboot.utils.FieldExtractor;
import com.core.springboot.utils.LocalDateUtil;
import com.core.springboot.utils.MongoUtil;
import ${package}.model.aggregation.GeneralAggregation;
import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GeneralRepository {

    String COLLECTION_NAME = "collectionName";

    List<GeneralAggregation> getAll(
            QueryParam param
    );

    Page<GeneralAggregation> getAll(
            QueryParam param,
            PageRequest pageRequest
    );

    Optional<GeneralAggregation> save(
            GeneralAggregation aggregation
    );

    Optional<GeneralAggregation> getGeneral(
            QueryParam param
    );

    Optional<GeneralAggregation> getByGeneralId(
            String generalId
    );

    boolean deleteAll(
            QueryParam param
    );

    default Query generalQuery(QueryParam param, boolean ignoreNulls) {
        Query query = new Query();
        Criteria dateCriteria = new Criteria("creationDate");

        Map<String, Object> queryParams = param.getFields(ignoreNulls);
        for (Map.Entry<String, Object> queryParam : queryParams.entrySet()) {
            String paramName = queryParam.getKey();
            Object paramValue = queryParam.getValue();
            switch (paramName) {
                case "field1":
                    Map<String, Object> transactionParams = FieldExtractor.getFields(paramValue, ignoreNulls);
                    for (Map.Entry<String, Object> transactionParam : transactionParams.entrySet()) {
                        String transactionParamKey = String.join(".", paramName, transactionParam.getKey());
                        Object transactionParamValue = transactionParam.getValue();
                        query.addCriteria(Criteria.where(transactionParamKey).is(transactionParamValue));
                    }
                    break;
                case "dateFrom":
                    dateCriteria = dateCriteria.gte(LocalDateUtil.toDateTime(paramValue));
                    break;
                case "dateTo":
                    dateCriteria = dateCriteria.lte(LocalDateUtil.toDateTime(paramValue));
                    break;
                default:
                    query.addCriteria(Criteria.where(paramName).is(paramValue));
                    break;
            }
        }

        Document document = dateCriteria.getCriteriaObject();
        if (MongoUtil.haveValue(document, dateCriteria.getKey())) {
            query.addCriteria(dateCriteria);
        }

        return query;
    }
}
