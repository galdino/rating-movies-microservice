package br.com.moviecatalogservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import br.com.moviecatalogservice.models.CatalogItem;
import br.com.moviecatalogservice.models.Movie;
import br.com.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "getFallbackCatalogItem",
					commandProperties = {
		                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
		                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
		                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
		                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
		            })
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
//						  Movie movie = webClientBuilder.build()
//						  				  .get()
//						  				  .uri("http://localhost:8082/movies/" + rating.getMovieId())
//						  				  .retrieve()
//						  				  .bodyToMono(Movie.class)
//						  				  .block();
		  return new CatalogItem(movie.getTitle(), movie.getOverview(), rating.getRating());
	}
	
	public CatalogItem getFallbackCatalogItem(Rating rating) {
		return new CatalogItem("Movie title not found", "", rating.getRating());
	}

}
