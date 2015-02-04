package givemethemoney.searchprovider;

public interface SearchProvider {
  PriceInformation getPriceFor(String query);
}
