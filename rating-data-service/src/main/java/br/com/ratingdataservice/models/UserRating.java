package br.com.ratingdataservice.models;

import java.util.Arrays;
import java.util.List;

public class UserRating {
	
	private String userId;
	private List<Rating> userRating;
	
	public void initData(String userId) {
		this.userId = userId;
		this.setUserRating(Arrays.asList(new Rating("100", 3),
										 new Rating("200", 5)));
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Rating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<Rating> userRating) {
		this.userRating = userRating;
	}
	
}
