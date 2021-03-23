package br.com.moviecatalogservice.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.moviecatalogservice.models.CatalogItem;
import br.com.moviecatalogservice.models.UserRating;
import br.com.moviecatalogservice.services.MovieInfo;
import br.com.moviecatalogservice.services.UserRatingInfo;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@Autowired
	MovieInfo movieInfo;
	
	@Autowired
	UserRatingInfo userRatingInfo; 
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
		
//		WebClient.Builder builder = WebClient.builder();
		
		UserRating ratings = userRatingInfo.getUserRating(userId);
		
		return ratings.getUserRating().stream()
									  .map(rating -> movieInfo.getCatalogItem(rating))
									  .collect(Collectors.toList());
		
	}
	
//	public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
//        return Arrays.asList(new CatalogItem("No movie", "No description", 0));
//    }

}
