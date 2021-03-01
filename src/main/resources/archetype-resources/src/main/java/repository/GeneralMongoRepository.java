package ${package}.repository;

import com.core.springboot.model.QueryParam;
import com.core.springboot.utils.ConverterUtils;
import com.core.springboot.utils.MongoUtil;
import ${package}.exception.GeneralSaveException;
import ${package}.model.aggregation.GeneralAggregation;
import ${package}.model.entity.GeneralEntity;
import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class GeneralMongoRepository implements GeneralRepository {

    private final MongoOperations mongoOperations;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public GeneralMongoRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public List<GeneralAggregation> getAll(QueryParam param) {
        List<GeneralAggregation> generals = new LinkedList<>();

        try {
            Query query = generalQuery(param, true);

            log.info("Get all generals.");
            List<GeneralEntity> results = mongoOperations.find(query, GeneralEntity.class);

            results.forEach(entity -> {
                GeneralAggregation aggregation = ConverterUtils.convert(entity, GeneralAggregation.class);
                generals.add(aggregation);
            });
        } catch (Exception ex) {
            log.error("Error happened during get all generals.\t", ex);
        }

        return generals;
    }

    @Override
    public Page<GeneralAggregation> getAll(QueryParam param, PageRequest pageRequest) {
        List<GeneralAggregation> generals = new LinkedList<>();

        int pageNumber = pageRequest.getPageNumber();
        int elementsPerPage = pageRequest.getPageSize();

        Query query = generalQuery(param, true);
        try {
            long totalElements = mongoOperations.count(query, GeneralEntity.class);

            query.with(Sort.by(Sort.Order.desc("creationDate")));
            query.skip(pageNumber * elementsPerPage);
            query.limit(elementsPerPage);

            log.info("Get all generals.");
            List<GeneralEntity> results = mongoOperations.find(query, GeneralEntity.class);

            results.forEach(entity -> {
                GeneralAggregation aggregation = ConverterUtils.convert(entity, GeneralAggregation.class);
                generals.add(aggregation);
            });

            Pageable pageable = PageRequest.of(pageNumber, elementsPerPage, Sort.by(Sort.Order.desc("creationDate")));
            return new PageImpl<>(generals, pageable, totalElements);
        } catch (Exception ex) {
            log.error("Error happened during get all generals.\t", ex);
        }

        return new PageImpl<>(generals);
    }

    @Override
    public Optional<GeneralAggregation> save(GeneralAggregation aggregation) {
        GeneralEntity entity = ConverterUtils.convert(aggregation, GeneralEntity.class);

        Optional<GeneralAggregation> optional = Optional.empty();
        try {
            log.info("Save new general.");
//            entity = mongoOperations.save(entity);
            if (Objects.nonNull(entity)) {
                optional = Optional.of(ConverterUtils.convert(entity, GeneralAggregation.class));
            }
        } catch (Exception ex) {
            log.error("Error happened during save general", ex);
            throw new GeneralSaveException();
        }
        return optional;
    }

    @Override
    public Optional<GeneralAggregation> getGeneral(QueryParam param) {
        Optional<GeneralAggregation> optional = Optional.empty();

        try {
            log.info("Get general.");
            Query query = MongoUtil.createQuery(param, true);
            if (MongoUtil.isNotEmptyQuery(query)) {
                GeneralEntity entity = mongoOperations.findOne(query, GeneralEntity.class);
                if (Objects.nonNull(entity)) {
                    optional = Optional.of(ConverterUtils.convert(entity, GeneralAggregation.class));
                }
            }
        } catch (Exception ex) {
            log.error("Error happened during get general", ex);
        }

        return optional;
    }

    @Override
    public Optional<GeneralAggregation> getByGeneralId(String generalId) {
        Optional<GeneralAggregation> optional = Optional.empty();

        try {
            log.info("Get general by id.");
            Query query = new Query(Criteria.where("generalId").is(generalId));
            GeneralEntity entity = mongoOperations.findOne(query, GeneralEntity.class);
            if (Objects.nonNull(entity)) {
                optional = Optional.of(ConverterUtils.convert(entity, GeneralAggregation.class));
            }
        } catch (Exception ex) {
            log.error("Error happened during get general by ID", ex);
        }

        return optional;
    }

    @Override
    public boolean deleteAll(QueryParam param) {
        boolean isSucceeded = false;

        try {
            log.info("Delete general/s");
            Query query = generalQuery(param, true);
            DeleteResult result = mongoOperations.remove(query, GeneralEntity.class);
            isSucceeded = result.getDeletedCount() != 0L;
        } catch (Exception ex) {
            log.error("Error happened during delete subscriptions", ex);
        }

        return isSucceeded;
    }
}
