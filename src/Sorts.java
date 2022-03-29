// Name: Jason Younan
// Description: Used to sort a list of Restaurant objects base on different criteria, such as the restaurant’s rating and cuisine.

import java.util.ArrayList;
import java.util.Comparator;

public class Sorts {
	// Create static method so that an object does not need to be instantiated
	public static void sort(ArrayList<Restaurant> reviewList, Comparator<Restaurant> xComparator) {
		// Iterate over reviewList x^2 times
		for (int j = 0; j < reviewList.size(); j++) {
			for (int i = 0; i < reviewList.size(); i++) {
				// Get first restaurant
				Restaurant restaurant = reviewList.get(i);

				// If index + 1 is less than reviewList size
				// Prevent ArrayList out of bounds error
				if (i + 1 < reviewList.size()) {
					// Get second restaurant
					Restaurant restaurantTwo = reviewList.get(i + 1);

					// Compare the two restaurants using Comparator interface method
					int sortValue = xComparator.compare(restaurant, restaurantTwo);

					// If sortValue is greater than 0, the restaurants should be swapped
					if (sortValue > 0) {
						// Swap restaurants
						reviewList.set(i, restaurantTwo);
						reviewList.set(i + 1, restaurant);
					}
				}
			}
		}
	}
}
