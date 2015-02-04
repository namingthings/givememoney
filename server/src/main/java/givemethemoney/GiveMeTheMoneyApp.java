package givemethemoney;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@SpringBootApplication
public class GiveMeTheMoneyApp {

  public static void main(String[] args) {
    SpringApplication.run(GiveMeTheMoneyApp.class);
  }

}
