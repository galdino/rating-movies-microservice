package br.com.moviecatalogservice.models;

import java.util.List;

public class UserRating {
	
	public UserRating() {
	}
	
	List<Rating> userRating;

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
}
