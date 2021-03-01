package ${package}.service.listener;

import com.core.springboot.model.QueryParam;
import com.core.springboot.service.handler.KafkaStreamListener;
import com.core.springboot.utils.ConverterUtils;
import com.core.springboot.utils.KafkaPublisher;
import com.core.springboot.utils.ObjectUtil;
import ${package}.channel.ChannelsNames;
import ${package}.channel.InGeneralChannel;
import ${package}.model.param.KafkaQueryParamImpl;
import ${package}.model.response.GeneralResponse;
import ${package}.service.query.QueryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GeneralListener implements KafkaStreamListener<Object> {

    private final QueryManager queryManager;

    private final InGeneralChannel generalChannel;

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    public GeneralListener(QueryManager queryManager, InGeneralChannel generalChannel) {
        this.queryManager = queryManager;
        this.generalChannel = generalChannel;
    }

    @Override
    @StreamListener(ChannelsNames.IN_GENERAL_CHANNEL)
    public void listener(Object o) {
        log.info("Receive new event");
        QueryParam param = ConverterUtils.convert(o, KafkaQueryParamImpl.class);

        GeneralResponse response = queryManager.getGeneral(param);

        Object eventFromShared = new Object();
        if (Objects.nonNull(response)) {
            eventFromShared = ConverterUtils.convert(response, Object.class);
//            eventFromShared.setPrimaryName(ConverterUtils.convert(response.getPrimaryName(), Object.class));
        }

        ObjectUtil.copy(o, eventFromShared, true);
//        profile.setSource("//TODO");

        KafkaPublisher.send(generalChannel, eventFromShared);
    }
}
