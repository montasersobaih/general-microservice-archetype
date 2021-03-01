package ${package}.model.dto;

import com.core.springboot.model.dto.GeneralDTO;
import com.core.springboot.model.dto.HateoasDTO;
import com.core.springboot.model.dto.MetadataDTO;
import ${package}.model.response.NewGeneralResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class NewGeneralDTO extends GeneralDTO {

    private NewGeneralResponse data;

    public NewGeneralDTO(MetadataDTO metadata, Collection<HateoasDTO> links, String data) {
        super(metadata, links);
        this.data = new NewGeneralResponse(data);
    }
}
