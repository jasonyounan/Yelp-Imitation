// Name: Jason Younan
// Description: Create a input switch to allow users to access different review manager methods

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Assignment8 {
	public static void main(String[] args) {
		// Menu options
		char inputOpt = ' ';
		String inputLine;
		// Restaurant and Cuisine information
		String restaurantName, cuisineName;
		String review = null, location, signatureDish, priceRange;
		int rating;
		// Output information
		String outFilename, inFilename;
		String outMsg, inMsg;
		// Restaurant manager
		ReviewManager reviewManager = new ReviewManager();
		// Operation result
		boolean opResult;

		try {
			printMenu();
			InputStreamReader isr = new InputStreamReader(System.in);
			BufferedReader stdin = new BufferedReader(isr);
			do {
				System.out.print("\nWhat action would you like to perform?\n");
				inputLine = stdin.readLine().trim();
				if (inputLine.isEmpty()) {
					continue;
				}
				inputOpt = inputLine.charAt(0);
				inputOpt = Character.toUpperCase(inputOpt);
				switch (inputOpt) {
				case 'A': // Add a new Restaurant Review
					System.out.print("Please enter the restaurant information:\n");
					System.out.print("Enter the restaurant name:\n");
					restaurantName = stdin.readLine().trim();
					System.out.print("Enter the review:\n");
					review = stdin.readLine().trim();
					System.out.print("Enter the price range:\n");
					priceRange = stdin.readLine().trim();
					System.out.print("Enter the rating:\n");
					rating = Integer.parseInt(stdin.readLine().trim());
					System.out.print("Enter the cuisine name:\n");
					cuisineName = stdin.readLine().trim();
					System.out.print("Enter the location:\n");
					location = stdin.readLine().trim();
					System.out.print("Enter the signature dish\n");
					signatureDish = stdin.readLine().trim();

					/*********************************************************************
					 * Complete the code by calling the addReview method.
					 *
					 * If the review has been added successfully, show "Restaurant added\n" on
					 * screen, otherwise "Restaurant NOT added\n"
					 **********************************************************************/

					// Add a review and store the result in a boolean
					opResult = reviewManager.addReview(restaurantName, rating, review, priceRange, cuisineName,
							location, signatureDish);

					if (opResult == false) {
						// Restaurant was not added
						System.out.print("Restaurant NOT added\n");
					} else {
						// Restaurant was added
						System.out.print("Restaurant added\n");
					}
					break; // Break from case

				case 'D': // Search a Restaurant
					System.out.print("Please enter the restaurant name to search:\n");
					restaurantName = stdin.readLine().trim();
					System.out.print("Please enter the restaurant's location':\n");
					location = stdin.readLine().trim();

					/*********************************************************************
					 * Complete the code. If a restaurant review exists, print
					 *
					 * "Restaurant found. Here's the review:\n"
					 * 
					 * Otherwise, print "Restaurant not found. Please try again\ n"
					 **********************************************************************/

					// Get the restaurant's index from the ArrayList
					int restaurantIndex = reviewManager.restaurantExists(restaurantName, location);

					if (restaurantIndex == -1) {
						// If index equals -1, the restaurant does not exist in the ArrayList
						System.out.print("Restaurant not found. Please try again\n");
					} else {
						// Restaurant exists in the ArrayList
						// Use reviewManager to get the restaurant by its index
						// Print the restaurant's review
						System.out.print("Restaurant found. Here's the review:\n");
						System.out.print(reviewManager.getRestaurant(restaurantIndex).getReview() + "\n");
					}
					break; // Break from case

				case 'E': // Search a cuisine
					System.out.print("Please enter the cuisine name to search:\n");
					cuisineName = stdin.readLine().trim();

					/*******************************************************************************
					 * Complete the code. If a cuisine is found, show on the screen how many
					 * restaurants match that cuisine by printing
					 *
					 * "%s Restaurants matching %s cuisine were found:\n" followed by the reviews.
					 * 
					 * Otherwise, print "Cuisine: %s was NOT found\n"
					 ******************************************************************************/

					// Get all restaurants with the given cuisine name
					ArrayList<Integer> cuisines = reviewManager.cuisineExists(cuisineName);

					if (cuisines.get(0) == -1) {
						// If the ArrayList's first index is equal to -1, no restaurants were found
						System.out.print("Cuisine: " + cuisineName + " was NOT found\n");
					} else {
						// Restaurants were found the cuisine name
						// Print the size cuisine's ArrayList (number of restaurants containing given
						// cuisine name)
						System.out.print(
								cuisines.size() + " Restaurants matching " + cuisineName + " cuisine were found:\n");
						// Iterate over cuisine ArrayList
						// The ArrayList contains the index of all restaurants with the same cuisineName
						for (int i = 0; i < cuisines.size(); i++) {
							// Use reviewManager to get the restaurant by its index
							// Print the restaurant using toString method
							System.out.print(reviewManager.getRestaurant(cuisines.get(i)).toString());
						}
						System.out.print("\n");
					}
					break; // Break from case

				case 'L': // List restaurant's reviews
					// Use reviewManager's listReview method to print all reviews
					System.out.print("\n" + reviewManager.listReviews() + "\n");
					break; // Break from case

				/**********************************************************************************
				 * Complete the code by adding two cases:
				 *
				 * case 'N': sorts the restaurant reviews by rating and prints "sorted by
				 * rating\n"
				 * 
				 * case 'P': sorts the restaurant reviews by cuisine name and prints "sorted by
				 * cuisine\n"
				 *********************************************************************************/

				case 'N': // List restaurant's reviews
					// Use reviewManager to sort all restaurants by their rating
					reviewManager.sortByRating();
					System.out.print("sorted by rating\n");
					break; // Break from case

				case 'P': // List restaurant's reviews
					// Use reviewManager to sort all restaurants by their cuisine
					reviewManager.sortByCuisine();
					System.out.print("sorted by cuisine\n");
					break; // Break from case

				case 'Q': // Quit
					break;

				case 'R': // Remove a review
					System.out.print("Please enter the restaurant name of the review to remove:\n");
					restaurantName = stdin.readLine().trim();
					System.out.print("Please enter the location to remove:\n");
					location = stdin.readLine().trim();

					/*******************************************************************************
					 * Complete the code. If a review for a certain restaurant at a given location
					 * is found, remove the review and print that it was removed. Otherwise print
					 * that it was NOT removed.
					 *******************************************************************************/

					// Get the restaurant's index from the ArrayList
					int restaurantId = reviewManager.restaurantExists(restaurantName, location);

					if (restaurantId == -1) {
						// If index equals -1, the restaurant does not exist in the ArrayList
						System.out.print(restaurantName + ", " + location + " was NOT removed\n");
					} else {
						// Restaurant exists in the ArrayList
						// Use reviewManager to remove the restaurant's review
						reviewManager.removeReview(restaurantName, location);
						System.out.print(restaurantName + ", " + location + " was removed\n");
					}
					break; // Break from case

				case 'T': // Close reviewList
					reviewManager.closeReviewManager();
					System.out.print("Restaurant management system was reset\n");
					break; // Break from case

				case 'U': // Write restaurant names and reviews to a text file
					System.out.print("Please enter a file name that we will write to:\n");
					outFilename = stdin.readLine().trim();
					System.out.print("Please enter the name of the restaurant:\n");
					restaurantName = stdin.readLine().trim();
					System.out.println("Please enter a review to save locally:\n");
					review = stdin.readLine().trim();
					outMsg = restaurantName + "\n" + review + "\n";

					/**********************************************************************************
					 * Add a try and catch block to write the string outMsg into the user-specified
					 * file. Then, print in the screen that the file " is written\n"
					 * 
					 * In case of an IO Exception, print "Write string inside the file error\n"
					 *********************************************************************************/

					try {
						// Initialize a new FileWriter instance using the output file name
						FileWriter writer = new FileWriter(outFilename);
						// Write the output message
						writer.write(outMsg);
						// Close the writer
						writer.close();
						System.out.print(outFilename + " is written\n");
					} catch (IOException exception) {
						// Catch IOException
						System.out.print("Write string inside the file error\n");
					}
					break; // Break from case

				case 'V': // Read strings from a text file
					System.out.print("Please enter a file name which we will read from:\n");
					inFilename = stdin.readLine().trim();

					try {
						// Initialize a new FileReader instance using the input file name
						// Initialize a new BufferedReader instance using the FileReader instance
						BufferedReader reader = new BufferedReader(new FileReader(inFilename));

						System.out.print(inFilename + " was read\nThe contents of the file are:\n");
						// Set inMsg to the next line
						// While inMsg is not null, continue
						// Print inMsg
						while ((inMsg = reader.readLine()) != null) {
							System.out.print(inMsg + "\n");
						}
						// Close the reader
						reader.close();
					} catch (FileNotFoundException exception) {
						// Catch FileNotFoundException
						System.out.print(inFilename + " was not found\n");
					} catch (IOException exception) {
						// Catch IOException
						System.out.print("Read string from file error\n");
					}
					break; // Break from case

				/**********************************************************************************
				 * Add a try and catch block to read from the above text file. Confirm that the
				 * file " was read\n" and then print "The contents of the file are:\n" followed
				 * by the contents
				 * 
				 * In case of an IO Exception, print "Read string from file error\n"
				 * 
				 * In case of a file not found exception, print that the file " was not found\n"
				 *********************************************************************************/

				case 'W': // Serialize ReviewManager to a data file
					System.out.print("Please enter a file name to write:\n");
					outFilename = stdin.readLine().trim();

					try {
						// Initialize a new FileOutputStream instance using the output file name
						// Initialize a new ObjectOutputStream instance using the FileOutputStream
						// instance
						ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outFilename));
						// Write the object
						outputStream.writeObject(reviewManager);
						// Close the outputStream
						outputStream.close();
					} catch (NotSerializableException exception) {
						// Catch NotSerializableException
						System.out.print("Not serializable exception\n");
					} catch (IOException exception) {
						// Catch IOException
						System.out.print("Data file written exception\n");
					}
					break; // Break from case

				/****************************************************************************
				 * Add a try and catch block to serialize ReviewManager to a data file. Catch
				 * two exceptions and print the corresponding messages on the screen:
				 * 
				 * "Not serializable exception\n"
				 * 
				 * "Data file written exception\n"
				 ****************************************************************************/

				case 'X': // Deserialize ReviewManager from a data file
					System.out.print("Please enter a file name which we will read from:\n");
					inFilename = stdin.readLine().trim();

					/*****************************************************************************
					 * Add a try and catch block to deserialize ReviewManager from a data file.
					 * Catch three exceptions and print the corresponding messages on the screen:
					 * 
					 * "Class not found exception\n"
					 * 
					 * "Not serializable exception\n"
					 * 
					 * "Data file read exception\n"
					 ****************************************************************************/

					try {
						// Initialize a new FileInputStream instance using the input file name
						// Initialize a new ObjectInputStream instance using FileInputStream instance
						ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inFilename));
						// Read the object
						// Cast the object to ReviewManager type
						// Set reviewManager to that object
						reviewManager = (ReviewManager) inputStream.readObject();
						// Close the input stream
						inputStream.close();
						System.out.print(inFilename + " was read\n");
					} catch (ClassNotFoundException exception) {
						System.out.print("Class not found exception\n");
					} catch (NotSerializableException exception) {
						System.out.print("Not serializable exception\n");
					} catch (IOException exception) {
						System.out.print("Data file written exception\n");
					}
					break; // Break from case

				case '?': // Display help
					printMenu();
					break;

				default:
					System.out.print("Unknown action\n");
					break;
				}
			} while (inputOpt != 'Q' || inputLine.length() != 1);
		} catch (IOException exception) {
			System.out.print("IO Exception\n");
		}
	}

	public static void printMenu() {
		System.out.println("Welcome to Kelp! ");
		System.out.println("Find or post reviews for your favorite (and not so favorite) restaurants.");
		System.out.print("Choice\t\tAction\n" + "------\t\t------\n" + "A\t\tAdd a review\n"
				+ "D\t\tSearch for a restaurant\n" + "E\t\tSearch for a cuisine\n" + "L\t\tList all reviews\n"
				+ "N\t\tSort by stars\n" + "P\t\tSort by cuisine\n" + "Q\t\tQuit\n" + "R\t\tRemove a review\n"
				+ "U\t\tAdd personal review to a local file\n" + "V\t\tRetrieve personal review from a local file\n"
				+ "W\t\tSave reviews to a file\n" + "X\t\tUpload reviews from a file\n"
				+ "T\t\t(admin) reset database\n" + "?\t\tDisplay Help\n");
	}
}
