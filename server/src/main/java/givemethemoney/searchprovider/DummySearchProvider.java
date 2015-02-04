package givemethemoney.searchprovider;

import org.springframework.stereotype.Component;

@Component
public class DummySearchProvider implements SearchProvider {
  @Override
  public PriceInformation getPriceFor(String query) {
    return new PriceInformation(42l);
  }
}
