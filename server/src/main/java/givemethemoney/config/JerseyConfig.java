package givemethemoney.config;

import givemethemoney.api.ApiResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@ApplicationPath("/api")
@Component
public class JerseyConfig extends ResourceConfig {
  public JerseyConfig() {
    register(ApiResource.class);
  }
}
