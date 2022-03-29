// Name: Jason Younan
// Description: This class gets and returns the restraunt's information 

import java.io.Serializable;

// Implement Serializable to allow the instance to be saved and read from a file
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 205L; // SerializableUID
	private String restaurantName;
	private int stars;
	private String review;
	private int priceRange;
	private String location;
	private Cuisine cuisine;

	// Initialize instance of Restaurant with given parameters
	public Restaurant(String restaurantName, int stars, String review, int priceRange, String location,
			Cuisine cuisine) {
		this.restaurantName = restaurantName;
		this.stars = stars;
		this.review = review;
		this.priceRange = priceRange;
		this.location = location;
		this.cuisine = cuisine;
	}

	// Get restaurant name
	public String getRestaurantName() {
		return this.restaurantName;
	}

	// Get stars
	public int getStars() {
		return this.stars;
	}

	// Get price range
	public int getPriceRange() {
		return this.priceRange;
	}

	// Get location
	public String getLocation() {
		return this.location;
	}

	// Get review
	public String getReview() {
		return this.review;
	}

	// Get cuisine
	public Cuisine getCuisine() {
		return this.cuisine;
	}

	// Get restaurant String
	public String toString() {
		// Initialize starString and priceString
		String starString = "*";
		String priceString = "$";

		// Start at 1 because starString already contains one *
		// Loop over according to the number of stars
		for (int i = 1; i < this.stars; i++) {
			// Add * to starString
			starString += "*";
		}

		// Start at 1 because priceString already contains one $
		// Loop over according to the number of priceRange
		for (int i = 1; i < this.priceRange; i++) {
			// Add $ to priceString
			priceString += "$";
		}

		// Return the String containing starString, priceString, and the cuisine
		// toString() method
		return restaurantName + " restaurant\n" + starString + "\t\t" + priceString + "\n" + cuisine.toString()
				+ "Location: " + location + "\n" + "Review:\t" + review + "\n\n";
	}
}
