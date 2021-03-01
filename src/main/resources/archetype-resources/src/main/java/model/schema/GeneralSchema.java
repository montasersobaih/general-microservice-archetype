package ${package}.model.schema;

import ${package}.model.SubmissionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GeneralSchema extends SubmissionInfo {

    @Id
    @GeneratedValue
    private String id;
}
