package givemethemoney.api;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import givemethemoney.GiveMeTheMoneyApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GiveMeTheMoneyApp.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApiResourceIntegrationTest {

  @Value("${local.server.port}")
  int port;

  @Before
  public void setUp() {
    RestAssured.port = port;
  }

  @Test
  public void shouldGetPricesForQuery() {
    Response response = when().
      get("/api/price?query");
  }
}