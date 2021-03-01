package ${package}.repository;

import ${package}.model.entity.GeneralEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GeneralDAO extends MongoRepository<GeneralEntity, String> {
}
