package ${package}.channel;

import com.core.springboot.channel.KafkaChannel;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface InGeneralChannel extends KafkaChannel {

    @Override
    @Input(ChannelsNames.IN_GENERAL_CHANNEL)
    SubscribableChannel subscribableChannel();
}
