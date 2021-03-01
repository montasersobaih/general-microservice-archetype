package ${package}.configuration;

import ${package}.channel.InGeneralChannel;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(InGeneralChannel.class)
public class EventsBindingConfig {
}
