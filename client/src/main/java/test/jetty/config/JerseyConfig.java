package test.jetty.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.jetty.rest.TestEndpoint;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(TestEndpoint.class);
    }

    @Bean
    public ObjectMapper restObjectMapper() {
        ObjectMapper restObjectMapper = new ObjectMapper();
        restObjectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return restObjectMapper;
    }
}
