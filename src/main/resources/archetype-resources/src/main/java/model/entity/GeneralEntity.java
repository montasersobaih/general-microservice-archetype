package ${package}.model.entity;

import ${package}.model.schema.GeneralSchema;
import ${package}.repository.GeneralRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = GeneralRepository.COLLECTION_NAME)
public class GeneralEntity extends GeneralSchema {

}
