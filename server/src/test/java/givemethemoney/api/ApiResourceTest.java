package givemethemoney.api;

import givemethemoney.searchprovider.PriceInformation;
import givemethemoney.searchprovider.SearchProvider;
import givemethemoney.searchprovider.SearchProviders;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiResourceTest {
  @Test
  public void shouldGetPricesFromAllSearchProviders() {
    // given:
    String query = "query";

    SearchProviders searchProviders = mock(SearchProviders.class);

    SearchProvider provider = mock(SearchProvider.class);
    when(provider.getPriceFor(query)).thenReturn(new PriceInformation(1l));

    SearchProvider anotherProvider = mock(SearchProvider.class);
    when(anotherProvider.getPriceFor(query)).thenReturn(new PriceInformation(2l));

    List<SearchProvider> searchProviderList = Arrays.asList(provider, anotherProvider);
    when(searchProviders.getSearchProviders()).thenReturn(searchProviderList);

    // when:
    List<String> prices = new ApiResource(searchProviders).getPrice(query);

    // then:
    assertTrue(prices.equals(Arrays.asList("1", "2")));
  }
}