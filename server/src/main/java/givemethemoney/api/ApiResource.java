package givemethemoney.api;

import givemethemoney.searchprovider.SearchProvider;
import givemethemoney.searchprovider.SearchProviders;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.stream.Collectors.toList;

@Service
@Path("price")
public class ApiResource {

  private SearchProviders searchProviders;

  @Autowired
  public ApiResource(SearchProviders searchProviders) {
    this.searchProviders = searchProviders;
  }

  @GET
  @Produces("application/json")
  public List<String> getPrice(@QueryParam("query") final String query) {
    return getPricesFromStream(query);
  }

  List<String> getPricesFromStream(String query) {
    return searchProviders.getSearchProviders()
      .parallelStream()
      .map(searchProvider -> {
        LoggerFactory.getLogger(getClass()).info("getting price from: " + searchProvider);
        return searchProvider.getPriceFor(query).getPriceInCent().toString();
      })
      .collect(toList());
  }

  private List<String> getPricesFromFutures(String query) {
    List<String> results = new ArrayList<>();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    List<Future<String>> futures = new ArrayList<>();

    for (SearchProvider searchProvider : searchProviders.getSearchProviders()) {
      Callable<String> searchProviderCallable = new Callable<String>() {
        @Override
        public String call() throws Exception {
          LoggerFactory.getLogger(getClass()).info("getting price from " + searchProvider.getClass().getSimpleName());
          return searchProvider.getPriceFor(query).getPriceInCent().toString();
        }
      };
      futures.add(executorService.submit(searchProviderCallable));
    }

    for (Future<String> future : futures) {
      try {
        String result = future.get();
        results.add(result);
      } catch (Exception e) {
        e.getCause();
      }
    }

    return results;
  }
}
