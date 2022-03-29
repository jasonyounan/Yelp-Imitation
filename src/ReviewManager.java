// Name: Jason Younan
// Description: Holds all information of each restaurant inputed by user and allows methods to access the information

import java.io.Serializable;
import java.util.ArrayList;

// Implement Serializable to allow the instance to be saved and read from a file
public class ReviewManager implements Serializable {
	// The serialVersionUID is used to verify compatibility of senders and
	// receivers. See the document for more details:
	// https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/io/Serializable.html
	private static final long serialVersionUID = 205L; // SerializableUID
	ArrayList<Restaurant> reviewList;

	public ReviewManager() {
		reviewList = new ArrayList<>();
	}

	// Check if restaurant exists
	public int restaurantExists(String restaurantName, String location) {
		// Iterate over reviewList
		for (int i = 0; i < reviewList.size(); i++) {
			// Get restaurant at index
			Restaurant restaurant = reviewList.get(i);

			// Check if restaurantName and location arguments are the same as this
			// restaurant's name and location
			if (restaurant.getRestaurantName().equals(restaurantName) && restaurant.getLocation().equals(location)) {
				// Return the current restaurant index
				return i;
			}
		}
		// If no restaurants exists arguments, return
		return -1;
	}

	// Check if cuisine exists
	public ArrayList<Integer> cuisineExists(String cuisineName) {
		// Initialize a new ArrayList that will contain all restaurant indices with the
		// given cuisineName
		ArrayList<Integer> cuisineList = new ArrayList<Integer>();

		// Iterate over reviewList
		for (int i = 0; i < reviewList.size(); i++) {
			// Get restaurant at index
			Restaurant restaurant = reviewList.get(i);

			// Check if cuisineName argument is the same as this
			// restaurant's cuisineName
			if (restaurant.getCuisine().getName().equals(cuisineName)) {
				// Add restaurant's index to cuisineList
				cuisineList.add(i);
			}
		}

		// If there are no cuisines in cuisineList
		if (cuisineList.size() == 0) {
			// Add -1 to signify no restaurants found with cuisineName
			cuisineList.add(-1);
		}

		// Return cuisineList
		return cuisineList;
	}

	// Get restaurant by id
	public Restaurant getRestaurant(int id) {
		// Return the restaurant with the given id index
		return reviewList.get(id);
	}

	// Remove a review
	public boolean removeReview(String restaurantName, String location) {
		// Iterate over reviewList
		for (int i = 0; i < reviewList.size(); i++) {
			// Get restaurant at index
			Restaurant restaurant = reviewList.get(i);

			// Check if restaurantName and location arguments are the same as this
			// restaurant's name and location
			if (restaurant.getRestaurantName().equals(restaurantName) && restaurant.getLocation().equals(location)) {
				// Remove restaurant from reviewList
				reviewList.remove(i);
				// Return true
				return true;
			}
		}

		// Return false
		return false;
	}

	// Sort by rating
	public void sortByRating() {
		// Sort reviewList using ReviewRatingComparator
		Sorts.sort(reviewList, new ReviewRatingComparator());
	}

	// Sort by cuisine
	public void sortByCuisine() {
		// Sort reviewList using ReviewCuisineComparator
		Sorts.sort(reviewList, new ReviewCuisineComparator());
	}

	// List all reviews
	public String listReviews() {
		// Check if there are no reviews
		if (reviewList.size() == 0) {
			// Return none available
			return "\nNo Reviews available\n\n";
		}

		// Initialize string that will contain all restaurant reviews
		String allReviews = "";

		// Iterate over reviewList
		for (int i = 0; i < reviewList.size(); i++) {
			// Get restaurant at index
			Restaurant restaurant = reviewList.get(i);

			// Add restaurant's toString method to allReviews String
			allReviews += restaurant.toString();
		}

		// Return allReviews
		return allReviews;
	}

	// Close review manager
	public void closeReviewManager() {
		// Clear reviewList
		reviewList.clear();
	}

	/**
	 * Add a Restaurant object to the reviewList and return true if such an object
	 * is added successfully. Otherwise, return false. Two restaurants are
	 * considered duplicated if they have exactly the same restaurant name and
	 * cuisine name.
	 * 
	 * @param restaurantName the name of the restaurant
	 * @param stars          the number of stars for the restaurant
	 * @param review         the restaurant review
	 * @param priceRange     the integer price range
	 * @param cuisineName    the Cuisine's name
	 * @param location       the restaurant location (street address)
	 * @param signatureDish  most famous dish
	 * @return true if the operation is successful; false otherwise
	 */
	public boolean addReview(String restaurantName, int stars, String review, String priceRange, String cuisineName,
			String location, String signatureDish) {
		if (restaurantExists(restaurantName, location) == -1) {
			int price = priceRange.length();
			Cuisine newCuisine = new Cuisine(signatureDish, cuisineName);
			Restaurant newRestaurant = new Restaurant(restaurantName, stars, review, price, location, newCuisine);
			reviewList.add(newRestaurant);
			return true;
		}
		return false;
	}
}