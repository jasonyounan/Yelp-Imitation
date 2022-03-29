// Name: Jason Younan
// Description: Sorts through the restaurant rate information and puts them in order  

import java.util.Comparator;

public class ReviewRatingComparator implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant o1, Restaurant o2) {
		// Get both restaurants stars
		int o1Stars = o1.getStars();
		int o2Stars = o2.getStars();

		// If their stars are not the same, return the difference
		if (o1Stars != o2Stars) {
			return o1Stars - o2Stars;
		}

		// Get both restaurants names and lexicographically compare them
		int restaurantComparison = o1.getRestaurantName().compareTo(o2.getRestaurantName());
		// If first restaurant name is less than second restaurant name
		if (restaurantComparison < 0) {
			return -1;
		}
		// If first restaurant name is greater than second restaurant name
		if (restaurantComparison > 0) {
			return 1;
		}

		// Get both restaurants locations and lexicographically compare them
		int locationComparison = o1.getLocation().compareTo(o2.getLocation());
		// If first restaurant location is less than second restaurant location
		if (locationComparison < 0) {
			return -1;
		}
		// If first restaurant location is greater than second restaurant location
		if (locationComparison > 0) {
			return 1;
		}

		// Get both restaurants reviews and lexicographically compare them
		int reviewComparison = o1.getReview().compareTo(o2.getReview());
		// If first restaurant review is greater than second restaurant review
		if (reviewComparison > 0) {
			return 1;
		}

		// Return -1 by default, no swap required
		return -1;
	}
}
