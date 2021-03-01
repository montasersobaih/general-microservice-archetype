package ${package}.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ${package}.model.schema.GeneralSchema;

public class GeneralResponse extends GeneralSchema {

    @JsonIgnore
    @Override
    public String getId() {
        return super.getId();
    }
}
