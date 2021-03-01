package ${package}.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public interface Submission {

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime getCreationDate();

    void setCreationDate(LocalDateTime creationDate);

    String getLastUpdatedBy();

    void setLastUpdatedBy(String lastUpdatedBy);

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime getLastUpdatedDate();

    void setLastUpdatedDate(LocalDateTime lastUpdatedDate);

    boolean isObsoletedFlag();

    void setObsoletedFlag(boolean obsoletedFlag);

    boolean isExpired();

    void setExpired(boolean expired);
}
