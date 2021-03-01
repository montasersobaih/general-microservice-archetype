package ${package}.model.dto;

import com.core.springboot.model.dto.GeneralDTO;
import com.core.springboot.model.dto.HateoasDTO;
import com.core.springboot.model.dto.MetadataDTO;
import ${package}.model.response.GeneralResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeneralsDTO extends GeneralDTO {

    private GeneralsData data;

    public GeneralsDTO(MetadataDTO metadata, Collection<HateoasDTO> links, List<GeneralResponse> data) {
        super(metadata, links);
        this.data = new GeneralsData(data);
    }

    public GeneralsDTO(MetadataDTO metadata, Collection<HateoasDTO> links, GeneralResponse data) {
        super(metadata, links);
        this.data = new GeneralsData(data);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class GeneralsData {

        private List<GeneralResponse> generals;

        GeneralsData(GeneralResponse general) {
            this.generals = new ArrayList<>();
            if (Objects.nonNull(general)) {
                this.generals.add(general);
            }
        }
    }
}
