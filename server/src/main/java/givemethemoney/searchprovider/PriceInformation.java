package givemethemoney.searchprovider;

public class PriceInformation {
  Long priceInCent;

  public PriceInformation(Long priceInCent) {
    this.priceInCent = priceInCent;
  }

  public Long getPriceInCent() {
    return priceInCent;
  }

  @Override
  public String toString() {
    return "PriceInformation{" +
      "priceInCent=" + priceInCent +
      '}';
  }
}
