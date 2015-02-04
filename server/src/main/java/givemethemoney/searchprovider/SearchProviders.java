package givemethemoney.searchprovider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class SearchProviders {

  @Autowired
  Collection<SearchProvider> searchProviders;

  public Collection<SearchProvider> getSearchProviders() {
    return searchProviders;
  }
}
