package givemethemoney.searchprovider;

import jodd.http.HttpRequest;
import jodd.jerry.Jerry;
import org.springframework.stereotype.Component;

@Component
public class RebuySearchProvider implements SearchProvider {
  @Override
  public PriceInformation getPriceFor(String query) {
    String htmlSearchResult = HttpRequest.get("https://www.rebuy.de/verkaufen/suche")
      .query("query", query).send().body();
    Jerry searchResultPage = Jerry.jerry(htmlSearchResult);
    String priceAttribute = searchResultPage.$("li.product").first().attr("data-price");
    return new PriceInformation(Long.parseLong(priceAttribute));
  }
}
