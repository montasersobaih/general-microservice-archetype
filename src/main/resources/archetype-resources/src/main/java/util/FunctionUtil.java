package ${package}.util;

import com.core.springboot.utils.ConverterUtils;
import ${package}.model.aggregation.GeneralAggregation;
import ${package}.model.response.GeneralResponse;

public interface FunctionUtil {

    static GeneralResponse getGeneralResponse(GeneralAggregation aggregation) {
        return ConverterUtils.convert(aggregation, GeneralResponse.class);
    }
}
