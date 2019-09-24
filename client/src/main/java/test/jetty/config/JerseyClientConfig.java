package test.jetty.config;

import static java.util.concurrent.TimeUnit.SECONDS;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyClientConfig {

    @Bean
    public WebTarget api(Client client) {
        return client.target("http://localhost:6700");
    }

    @Bean
    public Client client(PoolingHttpClientConnectionManager connectionManager) {
        ClientConfig config = new ClientConfig();
        config.property(ApacheClientProperties.CONNECTION_MANAGER, connectionManager);
        config.connectorProvider(new ApacheConnectorProvider());
        config.register(JacksonFeature.class);
        RequestConfig.Builder rcBuilder = RequestConfig.custom();
        config.property(ApacheClientProperties.REQUEST_CONFIG, rcBuilder.build());
        return ClientBuilder.newClient(config);
    }

    @Bean
    public PoolingHttpClientConnectionManager connectionManager() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(10, SECONDS);
        connectionManager.setMaxTotal(50);
        connectionManager.setDefaultMaxPerRoute(10);
        connectionManager.setValidateAfterInactivity(-1);
        return connectionManager;
    }
}
