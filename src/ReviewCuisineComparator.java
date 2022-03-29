// Name: Jason Younan
// Description: Sorts through the restaurant cuisine information and puts them in order 

import java.util.Comparator;

public class ReviewCuisineComparator implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant o1, Restaurant o2) {
		// Get both restaurants cuisine names and lexicographically compare them
		int cuisineComparison = o1.getCuisine().getName().compareTo(o2.getCuisine().getName());
		// If first restaurant cuisine name is less than second restaurant cuisine name
		if (cuisineComparison < 0) {
			return -1;
		}
		// If first restaurant cuisine name is greater than second restaurant cuisine
		// name
		if (cuisineComparison > 0) {
			return 1;
		}

		// Get both restaurants price range
		int o1PriceRange = o1.getPriceRange();
		int o2PriceRange = o2.getPriceRange();

		// If first restaurant price range is less than second restaurant price range
		if (o1PriceRange < o2PriceRange) {
			return -1;
		}
		// If first restaurant price range is greater than second restaurant price range
		if (o1PriceRange > o2PriceRange) {
			return 1;
		}

		// Get both restaurants names and lexicographically compare them
		int restaurantComparison = o1.getRestaurantName().compareTo(o2.getRestaurantName());
		// If first restaurant cuisine name is less than second restaurant cuisine name
		if (restaurantComparison < 0) {
			return -1;
		}
		// If first restaurant cuisine name is greater than second restaurant cuisine
		// name
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
